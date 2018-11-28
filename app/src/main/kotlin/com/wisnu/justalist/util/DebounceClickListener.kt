package com.wisnu.justalist.util

import android.os.Handler
import android.view.View

/**
 * Created by wisnu on 11/28/18
 */
abstract class DebounceClickListener : View.OnClickListener {

    companion object {
        private var enabled = true
        private val ENABLE_AGAIN = Runnable { enabled = true }
        private val handler = Handler()
    }

    override fun onClick(view: View) {
        if (enabled) {
            enabled = false
            handler.postDelayed(ENABLE_AGAIN, 500)
            doClick(view)
        }
    }

    open fun doClick(view: View) {
        throw NotImplementedError("not implemented")
    }

}