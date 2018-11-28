package com.wisnu.justalist.data.local.source.model

import com.wisnu.justalist.util.Constants

/**
 * Created by wisnu on 11/27/18
 */
open class Source(
    open val query: String,
    open val active: Boolean
)

class DribbleSource(override val query: String,
                    override val active: Boolean): Source(query, active) {
    companion object {
        val DEFAULT = DribbleSource(Constants.DRIBBLE_SEARCH_QUERY_DEFAULT, true)
    }
}