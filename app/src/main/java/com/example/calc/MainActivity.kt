package com.example.calc

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.calc.ui.theme.CalcTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.unit.dp

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
fun CalculatorApp() {
    var expression by rememberSaveable { mutableStateOf("") }
    var result by rememberSaveable { mutableStateOf("") }

    Column {
        CalculatorDisplay(expression, result)
        CalculatorButtonsGrid()
    }
}

@Preview(showBackground = true)
@Composable
fun CalculatorAppPreview() {
    CalcTheme {
        CalculatorApp()
    }
}

@Composable
fun CalculatorDisplay(expression: String, result: String) {
    Column {
        Text(text = "Expression: $expression", style = MaterialTheme.typography.headlineMedium)
        Text(text = "Result: $result", style = MaterialTheme.typography.displayMedium)
    }
}

@Preview(showBackground = true)
@Composable
fun CalculatorDisplayPreview() {
    CalcTheme {
        CalculatorDisplay(
            expression = "1+2",
            result = "3"
        )
    }
}



@Composable
fun CalculatorButtonsGrid() {
    val buttons = listOf(
        listOf("1", "2", "3", "+"),
        listOf("4", "5", "6", "-"),
        listOf("7", "8", "9", "×"),
        listOf("0", "=", "÷", "C")
    )

    Column {
        buttons.forEach { row ->
            Row {
                row.forEach { label ->
                    Button(
                        onClick = {  },
                        modifier = Modifier
                            .padding(8.dp)
                            .weight(1f)
                    ) {
                        Text(text = label)
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CalculatorButtonsGridPreview() {
    CalcTheme  {
        CalculatorButtonsGrid()
    }
}
