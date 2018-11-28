package com.wisnu.justalist.feature.home.data

import com.wisnu.justalist.base.BaseRepository
import com.wisnu.justalist.base.data.KeyValueStore
import com.wisnu.justalist.data.local.source.SourceKeyValueStore
import com.wisnu.justalist.data.local.source.model.Source

/**
 * Created by wisnu on 11/27/18
 */
class HomeRepository(override val keyValueStore: KeyValueStore,
                     private val sourceKeyValueStore: SourceKeyValueStore): BaseRepository(keyValueStore) {

    private fun loadSource(source: Source) {
        if (source.active) {

        }
    }
}