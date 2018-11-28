package com.wisnu.justalist.feature.home.ui

import android.os.Bundle
import com.wisnu.justalist.R
import com.wisnu.justalist.base.BaseActivity
import com.wisnu.justalist.feature.home.viewmodel.HomeViewModel
import org.koin.android.viewmodel.ext.android.viewModel

/**
 * Created by wisnu on 11/27/18
 */
class HomeActivity : BaseActivity() {

    private val viewModel: HomeViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
    }
}
