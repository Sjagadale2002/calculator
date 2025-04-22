package com.suyog.myapplication

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var display: TextView
    private var currentInput = ""
    private var lastOperator = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        display = findViewById(R.id.display)

        // Button mappings
        val button0: Button = findViewById(R.id.button0)
        val button1: Button = findViewById(R.id.button1)
        val button2: Button = findViewById(R.id.button2)
        val button3: Button = findViewById(R.id.button3)
        val button4: Button = findViewById(R.id.button4)
        val button5: Button = findViewById(R.id.button5)
        val button6: Button = findViewById(R.id.button6)
        val button7: Button = findViewById(R.id.button7)
        val button8: Button = findViewById(R.id.button8)
        val button9: Button = findViewById(R.id.button9)

        val buttonAdd: Button = findViewById(R.id.buttonAdd)
        val buttonSubtract: Button = findViewById(R.id.buttonSubtract)
        val buttonMultiply: Button = findViewById(R.id.buttonMultiply)
        val buttonDivide: Button = findViewById(R.id.buttonDivide)

        val buttonClear: Button = findViewById(R.id.buttonClear)
        val buttonEquals: Button = findViewById(R.id.buttonEquals)

        // Setting click listeners
        button0.setOnClickListener { appendNumber("0") }
        button1.setOnClickListener { appendNumber("1") }
        button2.setOnClickListener { appendNumber("2") }
        button3.setOnClickListener { appendNumber("3") }
        button4.setOnClickListener { appendNumber("4") }
        button5.setOnClickListener { appendNumber("5") }
        button6.setOnClickListener { appendNumber("6") }
        button7.setOnClickListener { appendNumber("7") }
        button8.setOnClickListener { appendNumber("8") }
        button9.setOnClickListener { appendNumber("9") }

        buttonAdd.setOnClickListener { applyOperator("+") }
        buttonSubtract.setOnClickListener { applyOperator("-") }
        buttonMultiply.setOnClickListener { applyOperator("*") }
        buttonDivide.setOnClickListener { applyOperator("/") }

        buttonClear.setOnClickListener { clearDisplay() }
        buttonEquals.setOnClickListener { calculateResult() }
    }

    private fun appendNumber(number: String) {
        currentInput += number
        display.text = currentInput
    }

    private fun applyOperator(operator: String) {
        if (currentInput.isNotEmpty()) {
            currentInput += " $operator "
            display.text = currentInput
            lastOperator = operator
        }
    }

    private fun clearDisplay() {
        currentInput = ""
        display.text = ""
    }

    private fun calculateResult() {
        try {
            val result = eval(currentInput)
            display.text = result.toString()
            currentInput = result.toString() // Store result for further operations
        } catch (e: Exception) {
            display.text = "Error"
        }
    }

    private fun eval(expression: String): Double {
        val split = expression.split(" ")
        if (split.size < 3) return 0.0

        val operand1 = split[0].toDouble()
        val operator = split[1]
        val operand2 = split[2].toDouble()

        return when (operator) {
            "+" -> operand1 + operand2
            "-" -> operand1 - operand2
            "*" -> operand1 * operand2
            "/" -> operand1 / operand2
            else -> 0.0
        }
    }
}
