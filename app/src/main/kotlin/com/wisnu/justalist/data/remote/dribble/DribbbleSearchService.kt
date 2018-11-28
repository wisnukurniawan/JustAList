package com.wisnu.justalist.data.remote.dribble

import androidx.annotation.StringDef
import com.wisnu.justalist.data.remote.dribble.model.Shot
import io.reactivex.Single
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by wisnu on 11/28/18
 */
interface DribbbleSearchService {

    @GET("search")
    fun searchDeferred(
        @Query("q") query: String,
        @Query("page") page: Int?,
        @Query("s") sort: String,
        @Query("per_page") pageSize: Int
    ): Deferred<Response<List<Shot>>>

    @GET("search")
    fun searchSingle(
        @Query("q") query: String,
        @Query("page") page: Int?,
        @Query("s") sort: String,
        @Query("per_page") pageSize: Int
    ): Single<Response<List<Shot>>>

    @kotlin.annotation.Retention(AnnotationRetention.SOURCE)
    @StringDef(
        SORT_POPULAR,
        SORT_RECENT
    )
    annotation class SortOrder

    companion object {
        const val ENDPOINT = "https://dribbble.com/"
        const val SORT_POPULAR = ""
        const val SORT_RECENT = "latest"
        const val PER_PAGE_DEFAULT = 12
    }
}