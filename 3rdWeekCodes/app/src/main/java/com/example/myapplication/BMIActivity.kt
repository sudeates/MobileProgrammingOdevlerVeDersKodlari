package com.example.myapplication

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlin.math.pow

class BMIActivity : AppCompatActivity() {
    lateinit var etHeight: EditText
    lateinit var etWeight: EditText
    lateinit var btnCalculateBMI: Button
    lateinit var tvBMI: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bmiactivity)
        etHeight=findViewById(R.id.et_Height)
        etWeight=findViewById(R.id.et_Weight)
        btnCalculateBMI=findViewById(R.id.btn_CalculateBMI)
        tvBMI=findViewById(R.id.tv_BMI)

        btnCalculateBMI.setOnClickListener {
            val heightText=etHeight.text.toString()
            val weightText=etWeight.text.toString()
            if(heightText.isNotEmpty() && weightText.isNotEmpty()){
                val height=heightText.toDouble()
                val weight=weightText.toDouble()
                val BMI=weight/(height.pow(2))
                when{
                    BMI < 18.5-> tvBMI.text="Your BMI is $BMI,You are Underweight"
                    BMI >= 18.5 && BMI < 24.9 -> tvBMI.text="You are Optimum range, Your BMI is $BMI"
                    BMI >= 24.9 && BMI < 29.9 -> tvBMI.text="You are Overweight, Your BMI is $BMI"
                    BMI >= 29.9 -> tvBMI.text="You are Obez, Your BMI is $BMI"
                }
            }
        }
    }
}