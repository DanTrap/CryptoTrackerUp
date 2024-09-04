package ru.mobileup.template.features.coins.data.dto

import android.os.Build
import android.text.Html

@Suppress("DEPRECATION")
internal fun String.formatDescription() = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
    Html.fromHtml(this, Html.FROM_HTML_MODE_LEGACY)
} else {
    Html.fromHtml(this)
}.toString()