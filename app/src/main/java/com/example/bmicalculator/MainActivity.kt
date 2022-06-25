package com.example.bmicalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val weightText = findViewById<EditText>(R.id.etWeight)
        val heightText = findViewById<EditText>(R.id.etHeight)
        val btnCalculate = findViewById<Button>(R.id.btnCalculate)

        btnCalculate.setOnClickListener{
            val weight = weightText.text.toString()
            val height = heightText.text.toString()
            if(validateInput(weight,height)){
            val bmi = weight.toFloat() / ((height.toFloat() / 100) * (height.toFloat() / 100))
            // get results with two decimal places
            val bmi2digits = String.format("%.2f", bmi).toFloat()
            displayResults(bmi2digits)
            }
        }
    }

    private fun validateInput(weight:String?,height:String?):Boolean{
        when{
            weight.isNullOrBlank() -> {
                Toast.makeText(this,"Weight is empty",Toast.LENGTH_SHORT).show()
                return false
            }
            height.isNullOrBlank() -> {
                Toast.makeText(this,"Height is empty",Toast.LENGTH_SHORT).show()
                return false
            }
            else -> {
                return  true
            }
        }
    }

    private  fun displayResults(bmi:Float){
        val resultIndex = findViewById<TextView>(R.id.tvIndex)
        val resultDescription = findViewById<TextView>(R.id.tvResults)
        val info = findViewById<TextView>(R.id.tvRange)

        resultIndex.text = bmi.toString()
        info.text = "(Normal range is 18.5 - 24.9)"

        var resultText = ""
        var color = 0

        when{
            bmi < 18.50 ->{
                resultText = "UnderWeight"
                color = R.color.under_weight
            }
            bmi in 18.50..24.99 ->{
                resultText = "Normal"
                color = R.color.normal
            }

            bmi in 25.00..29.99 ->{
                resultText = "OverWeight"
                color = R.color.over_weight
            }
            bmi > 29.99 -> {
                resultText = "Obese"
                color = R.color.obese
            }
        }
        resultDescription.setTextColor(ContextCompat.getColor(this,color))
        resultDescription.text = resultText
    }
}

