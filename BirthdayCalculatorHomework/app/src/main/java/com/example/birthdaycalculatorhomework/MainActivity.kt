package com.example.birthdaycalculatorhomework

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.util.Calendar

class MainActivity : AppCompatActivity() {
    private lateinit var etDay: EditText
    private lateinit var etMonth: EditText
    private lateinit var etYear: EditText
    private lateinit var textResult: TextView
    private lateinit var btnCalculate: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        etDay=findViewById(R.id.et_Gun)
        etMonth=findViewById(R.id.et_Ay)
        etYear=findViewById(R.id.et_Year)
        textResult=findViewById(R.id.textResult)
        btnCalculate=findViewById(R.id.btn_Calculate)
        btnCalculate.setOnClickListener {
            val dayText=etDay.text.toString()
            val monthText=etMonth.text.toString()
            val yearText=etYear.text.toString()
            if (dayText.isNotEmpty() && monthText.isNotEmpty() && yearText.isNotEmpty()) {
                val day = dayText.toInt()
                val month = monthText.toInt()
                val year = yearText.toInt()
                val birthdayDays = calculateDays(day, month, year)
                val currentDay= Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
                val currentMonth=Calendar.getInstance().get(Calendar.MONTH)
                val currentYear=Calendar.getInstance().get(Calendar.YEAR)
                val currentDays=calculateDays(currentDay,currentMonth,currentYear)
                val diff=currentDays-birthdayDays
                textResult.text=diff.toString()
            }
        }
    }
    fun ayinGunSayisi(month: Int, year: Int): Int {
        return when (month) {
            1, 3, 5, 7, 8, 10, 12 -> 31
            4, 6, 9, 11 -> 30
            2 -> if (artikYilMi(year)) 29 else 28
            else -> 0
        }
    }
    fun artikYilMi(year: Int): Boolean {
        return year % 4 == 0
    }
    fun calculateDays(day:Int,month:Int,year:Int):Long{
        var toplam = 0L
        for (i in 0 until year) {
            toplam += if (artikYilMi(i)) 366 else 365
        }
        for (i in 1 until month) {
            toplam += ayinGunSayisi(i-1, year)
        }
        toplam += day
        return toplam
    }
}