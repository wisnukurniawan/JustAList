package com.wisnu.justalist.di

import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import com.wisnu.justalist.utils.Constants
import org.koin.dsl.module.module

/**
 * Created by wisnu on 11/28/18
 */
val appModule = module(createOnStart = true) {

    // Gson default
    single(name = Constants.DI_GSON_DEFAULT) {
        GsonBuilder()
            .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .create()
    }
}