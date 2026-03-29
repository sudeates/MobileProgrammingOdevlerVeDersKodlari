package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
    //you have to add this function button's onClick attribute
    fun clickButton(v: View){
        val intent: Intent
        when(v.id){
            R.id.btn_Age->{
                intent=Intent(this,AgeActivity::class.java)
                startActivity(intent)
            }
            R.id.btn_BMI->{
                intent=Intent(this,BMIActivity::class.java)
                startActivity(intent)
            }
            R.id.btn_Calculator->{
                intent=Intent(this,CalculatorActivity::class.java)
                startActivity(intent)
            }
        }
    }
}