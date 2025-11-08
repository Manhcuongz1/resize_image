package com.example.background_erase.model

import android.util.Rational
import android.util.Size

data class Flatform(
    val name: String? = null,
    val logo: String? = null,
    val comparesInfo: List<ComparesInfo>? = null,
    val forceFileSize: Int? = null
) {
    data class ComparesInfo(
        val size: Size? = null,
        val rational: Rational? = null,
    )
}