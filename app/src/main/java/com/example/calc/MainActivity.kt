package com.example.calc

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
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
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CalcTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = {
                        TopAppBar(
                            title = { Text("Calculator", style = MaterialTheme.typography.headlineMedium) }
                        )
                    }
                ) { innerPadding ->
                    CalculatorApp(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun CalculatorApp(modifier: Modifier = Modifier) {
    var expression by rememberSaveable { mutableStateOf("") }
    var result by rememberSaveable { mutableStateOf("") }

    Column(modifier = modifier.fillMaxSize().padding(16.dp)) {
        CalculatorDisplay(expression, result)
        Spacer(modifier = Modifier.height(16.dp))
        CalculatorButtonsGrid { value ->
            when (value) {
                "=" -> result = calculateExpression(expression)
                "C" -> {
                    expression = ""
                    result = ""
                }
                else -> expression += value
            }
        }
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
fun CalculatorButtonsGrid(onButtonClick: (String) -> Unit) {
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
                        onClick = { onButtonClick(label) },
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
    CalcTheme {
        CalculatorButtonsGrid(onButtonClick = {})
    }
}



fun calculateExpression(expression: String): String {
    return try {
        val cleanExpression = expression.replace("×", "*").replace("÷", "/")
        val result = evaluateExpression(cleanExpression)
        result.toString()
    } catch (e: Exception) {
        "Error"
    }
}

fun evaluateExpression(expression: String): Double {
    val operators = mutableListOf<Char>()
    val numbers = mutableListOf<Double>()
    var currentNumber = ""

    for (char in expression) {
        if (char.isDigit() || char == '.') {
            currentNumber += char
        } else if (char in listOf('+', '-', '*', '/')) {
            if (currentNumber.isNotEmpty()) {
                numbers.add(currentNumber.toDouble())
                currentNumber = ""
            }
            operators.add(char)
        }
    }
    if (currentNumber.isNotEmpty()) {
        numbers.add(currentNumber.toDouble())
    }


    var index = 0
    while (index < operators.size) {
        when (operators[index]) {
            '*' -> {
                numbers[index] = numbers[index] * numbers[index + 1]
                numbers.removeAt(index + 1)
                operators.removeAt(index)
            }
            '/' -> {
                numbers[index] = numbers[index] / numbers[index + 1]
                numbers.removeAt(index + 1)
                operators.removeAt(index)
            }
            else -> index++
        }
    }


    var result = numbers[0]
    for (i in operators.indices) {
        when (operators[i]) {
            '+' -> result += numbers[i + 1]
            '-' -> result -= numbers[i + 1]
        }
    }

    return result
}
