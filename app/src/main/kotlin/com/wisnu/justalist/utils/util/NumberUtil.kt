package com.wisnu.justalist.utils.util

import java.text.DecimalFormat
import java.text.NumberFormat
import java.util.*

/**
 * Created by wisnu on 11/28/18
 */

object NumberUtil {
    private const val ZERO_DOUBLE = 0.0

    private val currencyFormat: NumberFormat by lazy {
        val numberFormat = NumberFormat.getCurrencyInstance(Locale("in"))
        val decimalFormatSymbols = (numberFormat as DecimalFormat).decimalFormatSymbols
        decimalFormatSymbols.currencySymbol = "Rp"
        decimalFormatSymbols.minusSign = '-'
        numberFormat.decimalFormatSymbols = decimalFormatSymbols
        numberFormat.maximumFractionDigits = 0
        numberFormat
    }

    fun getCurrencyNumber(number: Double): String {
        val formatted = currencyFormat.format(number)
        return formatted.replace(",00", "")
    }

    fun getFormattedNumber(number: Float): String {
        return getCurrencyNumber(number.toDouble()).replaceFirst("Rp", "")
    }

    fun getCurrencyNumber(number: String): String {
        if (number == "-") return number

        val formatted = currencyFormat.format(number.toDouble())
        return formatted.replace(",00", "")
    }

    fun getStringFromCurrencyText(currencyText: String): String =
        getNumberFromCurrencyText(currencyText).toString()

    private fun getNumberFromCurrencyText(currencyText: String): Number =
        currencyFormat.parse(currencyText)

    fun getZeroIndexIfNoPosition(index: Int): Int = if (index == -1) 0 else index

    fun isZero(value: Double): Boolean = value.compareTo(ZERO_DOUBLE) == 0
}