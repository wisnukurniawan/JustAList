package com.wisnu.justalist.di

import com.google.gson.Gson
import com.wisnu.justalist.base.data.KeyValueStore
import com.wisnu.justalist.data.local.SharedPreferenceApi
import com.wisnu.justalist.data.local.source.SourceKeyValueStore
import com.wisnu.justalist.data.remote.dribble.DribbbleSearchService
import com.wisnu.justalist.util.Constants
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module.module
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.reflect.Type
import java.util.concurrent.TimeUnit

/**
 * Created by wisnu on 11/28/18
 */
val dataModule = module {

    // SharedPreferenceApi default
    single<KeyValueStore>(name = Constants.DI_PREFERENCE_DEFAULT) {
        SharedPreferenceApi(
            androidContext(),
            get(Constants.DI_GSON_DEFAULT)
        )
    }

    // Empty converter
    single(name = Constants.DI_RETROFIT_EMPTY_RESPONSE_CONVERTER) { buildAnyOnEmptyConverter() }

    // Retrofit dribble
    single(name = Constants.DI_RETROFIT_DRIBBLE) {
        buildDribbleRetrofit(
            get(Constants.DI_GSON_DEFAULT),
            get(Constants.DI_RETROFIT_EMPTY_RESPONSE_CONVERTER)
        )
    }

    single { SourceKeyValueStore(get(name = Constants.DI_PREFERENCE_DEFAULT)) }

}

private const val TIMEOUT = 60L

private fun buildAnyOnEmptyConverter(): Converter.Factory {
    return object : Converter.Factory() {
        override fun responseBodyConverter(
            type: Type,
            annotations: Array<Annotation>,
            retrofit: Retrofit
        ): Converter<ResponseBody, Any> {
            val delegate: Converter<ResponseBody, Any> = retrofit.nextResponseBodyConverter(this, type, annotations)
            return Converter { body ->
                if (body.contentLength() == 0L) return@Converter null
                delegate.convert(body)
            }
        }
    }
}

private fun buildDribbleRetrofit(
    gson: Gson,
    emptyConverter: Converter.Factory
): Retrofit {
    return Retrofit
        .Builder()
        .baseUrl(DribbbleSearchService.ENDPOINT)
        .client(buildOkHttpClient())
        .addConverterFactory(GsonConverterFactory.create(gson))
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(emptyConverter)
        .build()
}

private fun buildOkHttpClient(): OkHttpClient {
    val okHttpClient = OkHttpClient
        .Builder()
        .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
        .readTimeout(TIMEOUT, TimeUnit.SECONDS)
        .addInterceptor(buildHttpLoggingInterceptor())

    return okHttpClient.build()
}

private fun buildHttpLoggingInterceptor(): Interceptor {
    return HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }
}


