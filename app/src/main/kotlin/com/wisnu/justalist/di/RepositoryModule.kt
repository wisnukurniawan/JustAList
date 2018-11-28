package com.wisnu.justalist.di

import com.wisnu.justalist.feature.home.data.HomeRepository
import com.wisnu.justalist.util.Constants
import org.koin.dsl.module.module

/**
 * Created by wisnu on 11/28/18
 */
val repositoryModule = module {
    single { HomeRepository(get(name = Constants.DI_PREFERENCE_DEFAULT), get()) }
}