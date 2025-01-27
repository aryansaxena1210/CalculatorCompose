package com.example.calculatorcompose

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.calculatorcompose.ui.theme.CalculatorOperation

class CalculatorViewModel: ViewModel() {

    var state by mutableStateOf(CalculatorState())
        private set


    fun onAction(action: CalculatorAction) {
        when(action) {
            is CalculatorAction.Number -> enterNumber(action.number)
            is CalculatorAction.Delete -> delete()
            is CalculatorAction.Clear -> state = CalculatorState()
            is CalculatorAction.Operation -> enterOperation(action.operation)
            is CalculatorAction.Decimal -> enterDecimal()
            is CalculatorAction.Calculate -> calculate()
        }
    }

    private fun delete() {
        when {
            state.number2.isNotBlank() -> state = state.copy(
                number2 = state.number2.dropLast(1)
            )
            state.operation != null -> state = state.copy(
                operation = null
            )
            state.number1.isNotBlank() -> state = state.copy(
                number1 = state.number1.dropLast(1)
            )
        }
        println(state)
    }

    private fun calculate() {
        val number1 = state.number1.toDoubleOrNull()
        val number2 = state.number2.toDoubleOrNull()
        if(number1 != null && number2 != null) {
            val tempResult = when(state.operation) {
                is CalculatorOperation.Add -> number1 + number2
                is CalculatorOperation.Subtract -> number1 - number2
                is CalculatorOperation.Multiply -> number1 * number2
                is CalculatorOperation.Divide -> {
                    if(number2!=0.0){
                        number1/number2
                    }
                    else{return}
                }
                null -> return
            }

            val formattedResult = if (tempResult % 1 == 0.0) {
                tempResult.toInt().toString() // Convert to Int and then String
            } else {
                tempResult.toString() // Keep it as a Double in String format
            }


            state = state.copy(
                number1 = formattedResult.take(9),
                number2 = "",
                operation = null
            )
        }
    }

    private fun enterDecimal() {

        if(state.operation == null && !state.number1.contains(".") && state.number1.isNotBlank()) {
            state = state.copy(
                number1 = state.number1 + "."
            )
            return
        } else if(!state.number2.contains(".") && state.number2.isNotBlank()) {
            state = state.copy(
                number2 = state.number2 + "."
            )
        }
        println(state)
    }

    private fun enterOperation(operation: CalculatorOperation) {
        if(state.number1.isNotBlank()){
            state = state.copy(operation = operation)
        }
        println(state)
    }

    private fun enterNumber(number: Int) {
        if(state.operation ==null){
            if(state.number1.length>=MAX_NUM_LENGTH)
                return
            state = state.copy(number1 = state.number1+number)
        }
        else {
            if (state.number2.length >= MAX_NUM_LENGTH) {
                return
            }
            state = state.copy(
                number2 = state.number2 + number
            )
        }
        println(state)
    }

    companion object {
        private const val MAX_NUM_LENGTH = 8
    }

}