package com.example.background_erase.base

import java.math.BigDecimal
import java.text.NumberFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

object Utils {

    const val PATTERN_DATE_DEFAULT = "dd-MM-yyyy HH:mm"


    fun getCurrentDate() : String {
        val current = LocalDateTime.now()

        val formatter = DateTimeFormatter.ofPattern(PATTERN_DATE_DEFAULT)

        val formattedDate = current.format(formatter)
        return formattedDate
    }
}

fun BigDecimal.toDefaultLocaleFormat(minDecimal: Int = 0, maxDecimal: Int = 2): String {
    val formatter = NumberFormat.getInstance().apply {
        // Cấu hình số chữ số thập phân
        minimumFractionDigits = minDecimal
        maximumFractionDigits = maxDecimal
    }

    return formatter.format(this)
}