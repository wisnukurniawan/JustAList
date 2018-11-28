package com.wisnu.justalist.di

import com.wisnu.justalist.feature.home.viewmodel.HomeViewModel
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

/**
 * Created by wisnu on 11/28/18
 */
val viewModelModule = module {
    viewModel { HomeViewModel(get()) }
}