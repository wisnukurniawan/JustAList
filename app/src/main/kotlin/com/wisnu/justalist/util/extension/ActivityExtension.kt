package com.wisnu.justalist.util.extension

import android.app.Activity
import android.os.Build
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager

/**
 * Created by wisnu on 11/28/18
 */
fun Activity.hideKeyboard() {
    val inputManager = this.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    val view = this.window.decorView
    inputManager.hideSoftInputFromWindow(view.windowToken, 0)
}

fun Activity.blockUi() {
    this.window.setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE, WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
}

fun Activity.unBlockUi() {
    this.window.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
}

fun Activity.setStatusBackgroundColor(bgColor: Int) {
    window.statusBarColor = bgColor
}

fun Activity.setLightStatusBar(light: Boolean = true) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        window.decorView.systemUiVisibility = if (light) View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR else 0
    } else {
        window.decorView.systemUiVisibility = 0
    }
}