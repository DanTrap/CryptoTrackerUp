package ru.mobileup.template.features.coins.presentation.list

import java.math.BigDecimal
import java.math.RoundingMode
import kotlin.math.abs
import kotlin.math.log10

internal fun Double.formatPricePercentage(): String {
    val rounded = roundToSignificantDigits()
    return buildString {
        if (rounded >= 0) append("+ ")
        append(rounded)
        if (rounded < 0) insert(1, ' ')
        append("%")
    }
}

internal fun Double.formatCurrentPrice(): String =
    roundToSignificantDigits().removeTrailingZeros()

internal fun Double.removeTrailingZeros(): String =
    toBigDecimal().stripTrailingZeros().toPlainString()

internal fun Double.roundToSignificantDigits(): Double = if (this == 0.0) {
    this
} else {
    val absValue = abs(this)
    if (absValue >= 1) {
        BigDecimal(this).setScale(2, RoundingMode.HALF_UP).toDouble()
    } else {
        val scale = -log10(absValue).toInt() + 1
        BigDecimal(this).setScale(scale, RoundingMode.HALF_UP).toDouble()
    }
}
