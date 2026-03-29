package com.example.myapplication

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.util.Calendar

class AgeActivity : AppCompatActivity() {
    lateinit var btnCalculateAge: Button
    lateinit var etBirthYear: EditText
    lateinit var tvAge: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_age)

        btnCalculateAge=findViewById(R.id.btn_CalculateAge)
        etBirthYear=findViewById(R.id.et_BirthYear)
        tvAge=findViewById(R.id.tv_Age)

        btnCalculateAge.setOnClickListener {
            val birthYearText=etBirthYear.text.toString()
            if(birthYearText.isNotEmpty()){
                val birthYear=birthYearText.toInt()
                val currentYear= Calendar.getInstance().get(Calendar.YEAR)
                val age=currentYear-birthYear
                tvAge.text="Your are= $age"
            }
        }
    }
}