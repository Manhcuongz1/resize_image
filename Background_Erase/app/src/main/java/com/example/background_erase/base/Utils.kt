package com.example.background_erase.base

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