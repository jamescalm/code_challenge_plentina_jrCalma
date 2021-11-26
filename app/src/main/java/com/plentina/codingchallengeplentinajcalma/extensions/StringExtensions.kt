package com.plentina.codingchallengeplentinajcalma.extensions

import java.math.BigDecimal
import java.math.RoundingMode

fun Int.toTwoDecString(): String{
    return String.format("%.2f", this@toTwoDecString.toDouble())
}

fun Double.toTwoDecStringDouble(): String{
    return String.format("%.2f", this@toTwoDecStringDouble)
}

fun Double.toWholeNumStringDouble(): String{
    return String.format("%.0f", this@toWholeNumStringDouble)
}