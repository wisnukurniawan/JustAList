package com.wisnu.justalist.util.extension

import android.content.Context
import android.transition.TransitionManager
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver
import android.view.inputmethod.InputMethodManager

/**
 * Created by wisnu on 11/28/18
 */

fun View.makeVisible() {
    this.visibility = View.VISIBLE
}

fun View.makeInvisible() {
    this.visibility = View.INVISIBLE
}

fun View.makeGone() {
    this.visibility = View.GONE
}

fun View.makeVisibleWithAnim() {
    TransitionManager.beginDelayedTransition(this.rootView as ViewGroup)
    this.visibility = View.VISIBLE
}

fun View.makeGoneWithAnim() {
    TransitionManager.beginDelayedTransition(this.rootView as ViewGroup)
    this.visibility = View.GONE
}

fun View.isVisible(): Boolean {
    return this.visibility == View.VISIBLE
}

fun View.select() {
    this.isSelected = true
}

fun View.unselect() {
    this.isSelected = false
}

fun View.enable() {
    isEnabled = true
}

fun View.disable() {
    isEnabled = false
}

inline fun <V : View> V.whenMeasured(crossinline action: V.() -> Unit) {
    viewTreeObserver.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
        override fun onGlobalLayout() {
            action()
            viewTreeObserver.removeOnGlobalLayoutListener(this)
        }
    })
}

fun View.showKeyboard(context: Context) {
    val inputManager = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    inputManager.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0)
}

fun View.showKeyboardImplicit(context: Context) {
    val inputManager = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    inputManager.toggleSoftInput(InputMethodManager.SHOW_IMPLICIT, 0)
}

fun View.hideKeyboard(context: Context?) {
    val inputManager = context?.getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
    inputManager?.hideSoftInputFromWindow(this.windowToken, 0)
}