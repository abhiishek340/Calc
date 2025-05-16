package com.example.calc

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.calc.ui.theme.CalcTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CalcTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) {
                    CalculatorApp()
                }
            }
        }
    }
}


@Composable
fun CalculatorApp(modifier: Modifier = Modifier) {
    CalculatorDisplay()
}


@Preview(showBackground = true)
@Composable
fun CalculatorAppPreview() {
    CalcTheme {
        CalculatorApp()
    }
}

@Composable
fun CalculatorDisplay(modifier: Modifier = Modifier) {
    Column(modifier = modifier){
        Text(text = "Expression: 1+2", style = MaterialTheme.typography.headlineMedium)
        Text(text = "Result: 3", style = MaterialTheme.typography.displayMedium)
    }
}

@Preview(showBackground = true)
@Composable
fun CalculatorDisplayPreview() {
    CalcTheme {
        CalculatorDisplay()
    }
}
