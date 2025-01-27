package com.example.calculatorcompose

import com.example.calculatorcompose.ui.theme.CalculatorOperation

data class CalculatorState(
    val number1:String = "",
    val number2:String = "",
    val operation: CalculatorOperation? = null
)
