package com.example.background_erase.base

import android.content.Context
import com.example.background_erase.R
import java.text.SimpleDateFormat
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId
import java.util.Date
import java.util.Locale

object DateFormatter {
    private fun isToday(timestamp: Long): Boolean {
        val date = Instant.ofEpochMilli(timestamp)
            .atZone(ZoneId.systemDefault())
            .toLocalDate()

        return date == LocalDate.now()
    }

    private fun isYesterday(timestamp: Long): Boolean {
        val date = Instant.ofEpochMilli(timestamp)
            .atZone(ZoneId.systemDefault())
            .toLocalDate()

        return date == LocalDate.now().minusDays(1)
    }

    fun transactionTime(
        timestamp: Long,
        context: Context
    ): String {
        val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())

        val str = when {
            isToday(timestamp) -> context.getString(R.string.today)
            isYesterday(timestamp) -> context.getString(R.string.yesterday)
            else ->
                sdf.format(Date(timestamp))
        }
        return str
    }
}