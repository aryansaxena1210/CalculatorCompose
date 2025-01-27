package com.example.calculatorcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.calculatorcompose.ui.theme.CalculatorComposeTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CalculatorComposeTheme {
                val viewModel= viewModel<CalculatorViewModel>()
//                val state by mutableStateOf(viewModel.state)
                val state = viewModel.state
                val buttonSpacing=8.dp
                Calculator(
                    state = state,
                    modifier = Modifier.fillMaxSize().background(Color.DarkGray).padding(16.dp),
                    buttonSpacing = buttonSpacing,
                    onAction = viewModel::onAction
                )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CalculatorComposeTheme {
        val viewModel= viewModel<CalculatorViewModel>()
//        val state by mutableStateOf(CalculatorState())
        val state = viewModel.state
        val buttonSpacing=8.dp
        Calculator(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Magenta)
                .padding(16.dp),
            state = CalculatorState(),
//            onAction = { action -> println("Action : ${action}")
            onAction = viewModel::onAction
        )
    }
}
