package com.wisnu.justalist.data.local.source

import com.wisnu.justalist.base.data.KeyValueStore
import com.wisnu.justalist.data.local.SharedPreferenceApi
import com.wisnu.justalist.data.local.source.model.DribbleSource
import com.wisnu.justalist.data.local.source.model.Source

/**
 * Created by wisnu on 11/28/18
 */
class SourceKeyValueStore(private val keyValueStore: KeyValueStore) {

    fun getSources(): List<Source> {
        return keyValueStore.getListObject(
            SharedPreferenceApi.SOURCE_KEY_VALUE,
            getDefaultSources()
        )
    }

    private fun getDefaultSources(): List<Source> {
        return listOf(
            DribbleSource.DEFAULT
        )
    }

}