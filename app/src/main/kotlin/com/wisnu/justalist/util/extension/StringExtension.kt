package com.wisnu.justalist.util.extension

import android.util.Patterns
import java.net.URLEncoder

/**
 * Created by wisnu on 11/28/18
 */


private const val NON_ALPHANUMERIC_PATTERN = "[^A-Za-z0-9 ]"
private const val UTF_8_ENCODE = "utf-8"

/**
 * Remove non alpha numeric characters from a string.
 */
fun String.removeNonAlphaNumeric(): String {
    val regex = Regex(NON_ALPHANUMERIC_PATTERN)
    return this.replace(regex, "")
}

/**
 * Encode a string using UTF-8.
 */
fun String.utf8Encode(): String {
    return URLEncoder.encode(this, UTF_8_ENCODE)
}

fun String.isValidPhoneNumber(): Boolean {
    return Patterns.PHONE.matcher(this).matches()
}

fun String.isValidEmail(): Boolean {
    return Patterns.EMAIL_ADDRESS.matcher(this).matches()
}

fun String.replaceMultipleSpacesWithSingleSpace(): String = this.replace(Regex(" +"), " ")