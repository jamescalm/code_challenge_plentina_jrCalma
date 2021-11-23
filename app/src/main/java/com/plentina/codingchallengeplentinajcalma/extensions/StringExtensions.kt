package com.plentina.codingchallengeplentinajcalma.extensions

import java.math.BigDecimal
import java.math.RoundingMode

fun Int.toTwoDecString(): String{
    return String.format("%.2f", this@toTwoDecString.toDouble())
}

fun Double.toTwoDecStringDouble(): String{
    val roundedDouble = BigDecimal(this@toTwoDecStringDouble).setScale(2, RoundingMode.HALF_EVEN)
    return String.format("%.2f", roundedDouble)
}