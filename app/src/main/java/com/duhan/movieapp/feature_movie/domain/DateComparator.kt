package com.duhan.movieapp.feature_movie.domain

import java.text.SimpleDateFormat
import java.util.*

private fun convertDate(date: String): Calendar {
    val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    val dateObj = dateFormat.parse(date)
    val calendar = Calendar.getInstance()
    calendar.time = dateObj
    return calendar
}

fun daysBetween(startDate: String, endDate: String): Int {
    val start = convertDate(startDate)
    val end = convertDate(endDate)
    return daysBetween(start, end)
}

fun daysBetween(startDate: String, endDate: Calendar): Int {
    val start = convertDate(startDate)
    return daysBetween(start, endDate)
}

fun daysBetween(startDate: Calendar, endDate: String): Int {
    val end = convertDate(endDate)
    return daysBetween(startDate, end)
}

fun daysBetween(startDate: Calendar, endDate: Calendar): Int {
    val start = startDate.timeInMillis
    val end = endDate.timeInMillis
    val diff = end - start
    return (diff / (24 * 60 * 60 * 1000)).toInt()
}


