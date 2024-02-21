package com.jonathansteele.calculatorndk

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jonathansteele.calculatorndk.ui.theme.CalculatorNDKTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CalculatorNDKTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val number1State = remember { mutableStateOf(TextFieldValue()) }
                    val number2State = remember { mutableStateOf(TextFieldValue()) }
                    val number1 = number1State.value.text.toInt()
                    val number2 = number2State.value.text.toInt()
                    CalculatorDisplay(
                        number1 = number1State,
                        number2 = number2State,
                        modifier = Modifier.padding(innerPadding),
                        addButton = {
                            add(number1, number2)
                        },
                        divideButton = {
                            divide(number1, number2)
                        },
                        subButton = {
                            subtract(number1, number2)
                        },
                        multiButton = {
                            multiply(number1, number2)
                        },
                    )
                }
            }
        }
    }

    /**
     * A native method that is implemented by the 'calculatorndk' native library,
     * which is packaged with this application.
     */
    private external fun add(
        x: Int,
        y: Int,
    ): Int

    private external fun subtract(
        x: Int,
        y: Int,
    ): Int

    private external fun multiply(
        x: Int,
        y: Int,
    ): Int

    private external fun divide(
        x: Int,
        y: Int,
    ): Int

    companion object {
        // Used to load the 'calculatorndk' library on application startup.
        init {
            System.loadLibrary("calculatorndk")
        }
    }
}

@Composable
fun CalculatorDisplay(
    number1: MutableState<TextFieldValue>,
    number2: MutableState<TextFieldValue>,
    modifier: Modifier = Modifier,
    addButton: () -> Unit,
    divideButton: () -> Unit,
    subButton: () -> Unit,
    multiButton: () -> Unit,
) {
    Column(
        modifier = modifier.padding(top = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        Text(
            text = "Simple Calculator",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
        )
        TextField(
            value = number1.value,
            onValueChange = { number1.value = it },
            placeholder = { Text(text = "Enter number") },
        )
        TextField(
            value = number2.value,
            onValueChange = { number2.value = it },
            placeholder = { Text(text = "Enter number") },
        )
        Row(
            horizontalArrangement = Arrangement.spacedBy(5.dp),
        ) {
            Button(onClick = { addButton() }) {
                Text(text = "+", fontSize = 30.sp)
            }
            Button(onClick = { subButton() }) {
                Text(text = "-", fontSize = 30.sp)
            }
            Button(onClick = { multiButton() }) {
                Text(text = "x", fontSize = 30.sp)
            }
            Button(onClick = { divideButton() }) {
                Text(text = "÷", fontSize = 30.sp)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CalculatorDisplayPreview() {
    CalculatorNDKTheme {
        val number1 = remember { mutableStateOf(TextFieldValue()) }
        val number2 = remember { mutableStateOf(TextFieldValue()) }
        CalculatorDisplay(
            number1 = number1,
            number2 = number2,
            addButton = {},
            divideButton = {},
            subButton = {},
            multiButton = {},
        )
    }
}
