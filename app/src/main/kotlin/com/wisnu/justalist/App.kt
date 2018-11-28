package com.wisnu.justalist

import android.app.Application
import com.wisnu.justalist.di.appModule
import com.wisnu.justalist.di.dataModule
import com.wisnu.justalist.di.repositoryModule
import com.wisnu.justalist.di.viewModelModule
import org.koin.android.ext.android.startKoin

/**
 * Created by wisnu on 11/27/18
 */
class App : Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin()
    }

    private fun initKoin() {
        startKoin(
            this,
            listOf(
                appModule,
                dataModule,
                repositoryModule,
                viewModelModule
            )
        )
    }

}