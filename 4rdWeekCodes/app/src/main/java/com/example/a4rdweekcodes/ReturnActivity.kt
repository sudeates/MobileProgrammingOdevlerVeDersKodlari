package com.example.a4rdweekcodes

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ReturnActivity : AppCompatActivity() {
    private lateinit var btnReturn: Button
    private lateinit var etName: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_return)
        etName = findViewById(R.id.et_ReturnName)
        btnReturn = findViewById(R.id.btn_ReturnName)

        btnReturn.setOnClickListener {
            val name=etName.text.toString()
            val returnIntent= Intent()
            returnIntent.putExtra("NAME",name)
            setResult(RESULT_OK,returnIntent)
            finish()
        }
    }
}