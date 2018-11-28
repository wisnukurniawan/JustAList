package com.wisnu.justalist.util.extension

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.drawable.Drawable
import android.net.ConnectivityManager
import android.os.Build
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.inputmethod.InputMethodManager
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.appcompat.view.ContextThemeWrapper
import androidx.core.app.ActivityCompat
import androidx.core.app.TaskStackBuilder
import androidx.core.content.ContextCompat
import com.wisnu.justalist.R

/**
 * Created by wisnu on 11/28/18
 */

/**
 * Get layout inflater using context that is wrapped by app theme style. Always use this layout
 * inflater to avoid crash in some devices. Return null if context passed is null.
 */
@SuppressLint("RestrictedApi")
fun Context?.getLayoutInflater(): LayoutInflater? {
    if (this == null) {
        return null
    }

    val contextWrapped = ContextThemeWrapper(this, R.style.AppTheme)
    return LayoutInflater.from(contextWrapped)
}

/**
 * Check if device is connected to the internet.
 */
fun Context.isNetworkAvailable(): Boolean {
    val activeNetworkInfo = getSystemService<ConnectivityManager>(Context.CONNECTIVITY_SERVICE)?.activeNetworkInfo
    return activeNetworkInfo?.isConnected ?: false
}

fun Context.isConnectedInternet(): Boolean {
    val cm = this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val activeNetwork = cm.activeNetworkInfo
    return activeNetwork != null && activeNetwork.isConnected
}

/**
 * Show soft input keyboard.
 */
fun Context.showKeyboard() {
    val inputMethodManager = getSystemService<InputMethodManager>(Context.INPUT_METHOD_SERVICE)
    inputMethodManager?.toggleSoftInput(InputMethodManager.SHOW_IMPLICIT, InputMethodManager.HIDE_IMPLICIT_ONLY)
}

inline fun <reified T> Context.getSystemService(systemService: String): T? {
    return getSystemService(systemService) as T
}

/**
 * Safely get color from resources
 */
fun Context?.getColor(@ColorRes id: Int): Int {
    this?.let {
        return ContextCompat.getColor(it, id)
    }

    return 0
}

/**
 * Safely get drawable from resources
 */
fun Context?.getDrawable(@DrawableRes id: Int): Drawable? {
    this?.let {
        return ContextCompat.getDrawable(it, id)
    }

    return null
}

fun Context.dpToPx(dp: Int): Int =
    TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp.toFloat(), resources.displayMetrics).toInt()

fun Context.pxToDp(px: Int): Int =
    TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX, px.toFloat(), resources.displayMetrics).toInt()

fun Context.gotoActivityWithBackStack(from: Class<out Activity>, to: Class<out Activity>): TaskStackBuilder {
    return TaskStackBuilder
        .create(this)
        .addNextIntent(Intent(this, from))
        .addNextIntent(
            Intent(
                this,
                to
            ).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_SINGLE_TOP)
        )
}

fun Context.hasPermission(permissions: Array<String>): Boolean {
    if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        permissions
            .filter { ActivityCompat.checkSelfPermission(this, it) != PackageManager.PERMISSION_GRANTED }
            .forEach { return false }
    }
    return true
}