package com.brayancode.calculadora

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import org.mariuszgromada.math.mxparser.Expression
import java.text.DecimalFormat

class MainActivity : AppCompatActivity() {

    // Se utiliza el modificador de acceso lateinit para indicar que dichas
    // variables seran inicializadas más tarde
    // variable para almacenar la expresión matematica introducida por el usuario
    private lateinit var inputTextView :TextView

    // variable para almacenar el resultado de la expresión matematica
    private lateinit var resultTextView: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // se inicializan las variables con el metodo findViewById que permite
        // encontrar una vista con el ID
        inputTextView = findViewById(R.id.inputTextView)
        resultTextView = findViewById(R.id.resultTextView)
    }

    fun pressDigit(view: View){

        val expression:String = inputTextView.text.toString()
        when (view.id) {
            R.id.button0 -> inputTextView.text = expression + "0"
            R.id.button00 -> inputTextView.text = expression + "00"
            R.id.button1 -> inputTextView.text = expression + "1"
            R.id.button2 -> inputTextView.text = expression + "2"
            R.id.button3 -> inputTextView.text = expression + "3"
            R.id.button4 -> inputTextView.text = expression + "4"
            R.id.button5 -> inputTextView.text = expression + "5"
            R.id.button6 -> inputTextView.text = expression + "6"
            R.id.button7 -> inputTextView.text = expression + "7"
            R.id.button8 -> inputTextView.text = expression + "8"
            R.id.button9 -> inputTextView.text = expression + "9"
            R.id.buttonDecimal -> inputTextView.text = expression + "."
            R.id.buttonAdd -> inputTextView.text = expression + "+"
            R.id.buttonSubtract -> inputTextView.text = expression+"-"
            R.id.buttonMultiply -> inputTextView.text = expression+"x"
            R.id.buttonDivide -> inputTextView.text = expression+"÷"
            R.id.buttonPorcentage -> inputTextView.text = expression + "%"
        }


    }

    private fun getInputExpression(): String {
        var expression = inputTextView.text.replace(Regex("÷"), "/")
        expression = expression.replace(Regex("x"), "*")
        return expression
    }

    fun removeDigit(view: View){
        val expression: String = inputTextView.text.toString()
        if(expression.isNotEmpty()){
                inputTextView.text = expression.substring(0, expression.length - 1)
        }
        if(expression.length<2){
            resultTextView.text=""
        }

    }

    fun clearAll(view: View){
        resultTextView.text =""
        inputTextView.text = ""

    }

    fun updateResult(view: View) {
        try {
            val expression = getInputExpression()
            if(expression.isNotBlank()) {
                val result = Expression(expression).calculate()
                if (result.isNaN()) {
                    // Show Error Message
                    resultTextView.text = "Error"
//                resultTextView.setTextColor(ContextCompat.getColor(this, R.color.red))
                } else {
                    // Show Result
                    resultTextView.text = "=" + DecimalFormat("0.######").format(result).toString()
//                resultTextView.setTextColor(ContextCompat.getColor(this, R.color.green))
                }
            }
        } catch (e: Exception) {
            // Show Error Message
            resultTextView.text = "Error"
//            output.setTextColor(ContextCompat.getColor(this, R.color.red))
        }
    }



}