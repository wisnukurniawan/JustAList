package com.wisnu.justalist.utils.util

import android.util.Log
import org.joda.time.DateTime
import org.joda.time.DateTimeZone
import org.joda.time.LocalDate
import org.joda.time.LocalDateTime
import org.joda.time.format.DateTimeFormat
import org.joda.time.format.ISODateTimeFormat
import java.util.*

/**
 * Created by wisnu on 11/28/18
 */

object DateTimeUtil {

    private val indonesiaLocale = Locale("in")

    private const val ORDER_FORMATTER = "hh:mm a, dd MMM yyyy"
    private val orderTimeFormatter = DateTimeFormat
        .forPattern(ORDER_FORMATTER)
        .withLocale(indonesiaLocale)

    private const val DAY_FORMATTER = "ddMMMyyyy"
    private val dayFormatter = DateTimeFormat
        .forPattern(DAY_FORMATTER)
        .withLocale(indonesiaLocale)

    private const val TRANSACTION_FORMATTER = "dd MMM yyyy, h:mm a"
    private val transactionTimeFormatter = DateTimeFormat
        .forPattern(TRANSACTION_FORMATTER)
        .withLocale(indonesiaLocale)

    private const val COMPLETE_DATE_FORMATTER = "dd MMM yyyy"
    private val completeDateTimeFormatter = DateTimeFormat
        .forPattern(COMPLETE_DATE_FORMATTER)
        .withLocale(indonesiaLocale)

    private const val SHORT_TRANSACTION_TIME_FORMAT_PATTERN = "HH:mm"
    private val shortTransactionTimeFormatter = DateTimeFormat
        .forPattern(SHORT_TRANSACTION_TIME_FORMAT_PATTERN)
        .withLocale(indonesiaLocale)

    private const val TRANSACTION_DATE_HORIZONTAL_FORMAT_PATTERN = "dd MMM, HH:mm"
    private val transactionDateHorizontalFormatter = DateTimeFormat
        .forPattern(TRANSACTION_DATE_HORIZONTAL_FORMAT_PATTERN)
        .withLocale(indonesiaLocale)

    fun generateCurrentTimeInIso8601(): String {
        val dateTime = ISODateTimeFormat.dateTime()
        return dateTime.print(DateTime.now(DateTimeZone.UTC))
    }

    fun generateCurrentDate(): String {
        return completeDateTimeFormatter.print(LocalDateTime.now())
    }

    fun getFormattedDate(isoTime: String): String {
        return completeDateTimeFormatter.print(
            getDateFromIso8601Time(isoTime)
        )
    }

    fun getTimeFormatted(date: Date, pattern: String): String {
        val dateTimeFormatter = DateTimeFormat.forPattern(pattern)
        return dateTimeFormatter.print(DateTime(date))
    }

    fun getIso8601DateFormatted(date: Date): String {
        return ISODateTimeFormat
            .dateTime()
            .print(DateTime(date))
    }

    fun getOrderTimeFormatted(isoTime: String): String =
        if (isoTime.isEmpty()) ""
        else orderTimeFormatter.print(getDateFromIso8601Time(isoTime))

    private fun getDateFromIso8601Time(iso8601time: String): DateTime {
        return if (iso8601time.contains(".")) {
            ISODateTimeFormat
                .dateTime()
                .parseDateTime(iso8601time)
        } else {
            ISODateTimeFormat
                .dateTimeNoMillis()
                .parseDateTime(iso8601time)
        }
    }

    fun getCurrentDayTime(): String = dayFormatter.print(DateTime.now(DateTimeZone.UTC))

    fun getFromAndToDate(startDateIsoTime: String,
                         endDateIsoTime: String): Pair<String, String> {
        val startDate = getDateFromIso8601Time(startDateIsoTime)
        val endDate = getDateFromIso8601Time(endDateIsoTime)
        val startLocalDate = DateTime(
            startDate.year,
            startDate.monthOfYear,
            startDate.dayOfMonth,
            0,
            0
        )
        val endLocalDate = DateTime(
            endDate.year,
            endDate.monthOfYear,
            endDate.dayOfMonth,
            0,
            0
        )

        return Pair(
            ISODateTimeFormat
                .dateTime().print(startLocalDate),
            ISODateTimeFormat
                .dateTime().print(endLocalDate.plusDays(1))
        )
    }

    fun getFromAndToDate(isoTime: String): Pair<String, String> {
        return getFromAndToDate(isoTime, isoTime)
    }

    fun getFromAndToDate(startDateIsoTime: String,
                         endDateIsoTime: String,
                         openTime: String,
                         closeTime: String): Pair<String, String> {
        val startDate = getDateFromIso8601Time(startDateIsoTime)
        val endDate = getDateFromIso8601Time(endDateIsoTime)
        val openedTime = openTime.split(":")
        val openHour = openedTime[0].toIntOrNull() ?: 0
        val openMinute = openedTime[1].toIntOrNull() ?: 0

        val openHourMinutes = openHour * 60 + openMinute

        val closedTime = closeTime.split(":")
        val closeHour = closedTime[0].toIntOrNull() ?: 0
        val closeMinute = closedTime[1].toIntOrNull() ?: 0

        val closeHourMinutes = closeHour * 60 + closeMinute
        var localOpenDate = DateTime(
            startDate.year,
            startDate.monthOfYear,
            startDate.dayOfMonth,
            closeHour,
            closeMinute
        )

        if (closeHourMinutes < openHourMinutes) {
            localOpenDate = localOpenDate.minusDays(0)
        } else {
            localOpenDate = localOpenDate.minusDays(1)
        }

        var localCloseDate = DateTime(
            endDate.year,
            endDate.monthOfYear,
            endDate.dayOfMonth,
            closeHour,
            closeMinute
        )

        if (closeHourMinutes < openHourMinutes) {
            localCloseDate = localCloseDate.plusDays(1)
        } else {
            localCloseDate = localCloseDate.plusDays(0)
        }


        val openIsoTime = ISODateTimeFormat
            .dateTime()
            .print(localOpenDate)

        val closeIsoTime = ISODateTimeFormat
            .dateTime()
            .print(localCloseDate)

        Log.d("DateTimeUtil", "Open Time ISO: " + openIsoTime)
        Log.d("DateTimeUtil", "Open Time ISO: " + closeIsoTime)

        return Pair(
            openIsoTime,
            closeIsoTime
        )
    }

    fun getFromAndToDate(isoTime: String,
                         openTime: String,
                         closeTime: String): Pair<String, String> {
        return getFromAndToDate(isoTime, isoTime, openTime, closeTime)
    }

    fun getTransactionDateFromIso8601Time(isoTime: String): String {
        return if (isoTime.isEmpty()) {
            ""
        } else {
            transactionTimeFormatter.print(getDateFromIso8601Time(isoTime))
        }
    }

    fun isToday(dateIso8601: String): Boolean {
        if (dateIso8601.isBlank()) {
            return false
        }
        return getDateFromIso8601Time(dateIso8601).toLocalDate() == LocalDate()
    }

    fun isYesterday(dateIso8601: String): Boolean {
        if (dateIso8601.isBlank()) {
            return false
        }
        return getDateFromIso8601Time(dateIso8601).toLocalDate() == LocalDate.now().minusDays(1)
    }

    fun generateTwoWeeksAgoIso8601Time(): String {
        val dateTime = ISODateTimeFormat.dateTime()
        return dateTime.print(DateTime.now(DateTimeZone.UTC).minusWeeks(2))
    }

    fun generateYesterdayIso8601Time(): String {
        val dateTime = ISODateTimeFormat.dateTime()
        return dateTime.print(DateTime.now(DateTimeZone.UTC).minusDays(1))
    }

    fun generateDayWithIntervalIso8601Time(isoTime: String, dayInterval: Int): String {
        val dateTime = ISODateTimeFormat.dateTime()
        val time = getDateFromIso8601Time(isoTime)
        return dateTime.print(time.minusDays(dayInterval))
    }

}