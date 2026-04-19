package freelance.demoapp.data.utils

import android.content.Context
import androidx.core.content.ContextCompat
import freelance.demoapp.data.R
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

    fun dateToLong(str: String?, pattern: String): Long {
        if (str == null) return 0L
        return try {
            val formatter = DateTimeFormatter.ofPattern(pattern)
            val dateTime = LocalDateTime.parse(str, formatter)
            dateTime.atZone(java.time.ZoneId.systemDefault()).toInstant().toEpochMilli()
        } catch (_: Exception) {
            0L
        }
    }
}
fun listOfColorExpense(context: Context) : List<Long> {
    val colorRes = listOf(
        R.color.expense_1,
        R.color.expense_2,
        R.color.expense_3,
        R.color.expense_4,
        R.color.expense_5,
        R.color.expense_6,
        R.color.expense_7,
        R.color.expense_8,
        R.color.expense_9,
        R.color.expense_10,
        R.color.expense_11,
        R.color.expense_12,
        R.color.expense_13,
        R.color.expense_14,
        R.color.expense_15,
        R.color.expense_16,
        R.color.expense_17,
        R.color.expense_18,
        R.color.expense_19,
        R.color.expense_20,
        R.color.expense_21,
        R.color.expense_22,
        R.color.expense_23,
        R.color.expense_24,
        R.color.expense_25,
        R.color.expense_26,
        R.color.expense_27,
        R.color.expense_28,
        R.color.expense_29,
        R.color.expense_30,
    )
    return colorRes.map { ContextCompat.getColor(context, it).toUInt().toLong() }
}
fun listOfColorIncome(context: Context) : List<Long> {
    val colorRes = listOf(
        R.color.income_1,
        R.color.income_2,
        R.color.income_3,
        R.color.income_4,
        R.color.income_5,
        R.color.income_6,
        R.color.income_7,
        R.color.income_8,
        R.color.income_9,
        R.color.income_10
    )
    return colorRes.map { ContextCompat.getColor(context, it).toUInt().toLong() }
}
