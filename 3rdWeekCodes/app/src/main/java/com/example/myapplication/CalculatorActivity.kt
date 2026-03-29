package com.example.myapplication

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class CalculatorActivity : AppCompatActivity(), View.OnClickListener{
    private lateinit var etFirst: EditText
    private lateinit var etSecond: EditText
    private lateinit var tvResult: TextView
    private lateinit var btnAdd: Button
    private lateinit var btnSubtract: Button
    private lateinit var btnMultiply: Button
    private lateinit var btnDivision: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calculator)
        etFirst=findViewById(R.id.et_First)
        etSecond=findViewById(R.id.et_Second)
        tvResult=findViewById(R.id.tv_Result)
        btnAdd=findViewById(R.id.btn_Add)
        btnSubtract=findViewById(R.id.btn_Subtract)
        btnMultiply=findViewById(R.id.btn_Multiply)
        btnDivision=findViewById(R.id.btn_Division)

        btnAdd.setOnClickListener(this)
        btnSubtract.setOnClickListener(this)
        btnMultiply.setOnClickListener(this)
        btnDivision.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        val firstNumberText= etFirst.text.toString()
        val secondNumberText= etSecond.text.toString()
        if(firstNumberText.isNotEmpty() && secondNumberText.isNotEmpty()){
            val firstNumber=firstNumberText.toDouble()
            val secondNumber=secondNumberText.toDouble()
            var result=0.0
            when(v.id){
                R.id.btn_Add->{
                    result=firstNumber+secondNumber
                }
                R.id.btn_Subtract->{
                    result=firstNumber-secondNumber
                }
                R.id.btn_Multiply->{
                    result=firstNumber*secondNumber
                }
                R.id.btn_Division->{
                    if(secondNumber==0.0){
                        tvResult.text="cannot divide by zero"
                    }else{
                        result=firstNumber/secondNumber
                    }
                }
            }
            tvResult.text="Result is $result"
        }
    }
}