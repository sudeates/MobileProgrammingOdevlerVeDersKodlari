package com.example.intenthomework

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class SecondActivity : AppCompatActivity() {
    private lateinit var etInput: EditText
    private lateinit var btnGonder: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        etInput = findViewById(R.id.et_Input)
        btnGonder = findViewById(R.id.btn_Gonder)

        btnGonder.setOnClickListener {
            val originalText= etInput.text.toString()
            val cleanedText=originalText.replace(Regex("[^a-zA-Z]"), replacement ="")

            val hataSayisi=originalText.length-cleanedText.length
            val returnIntent= Intent()
            returnIntent.putExtra("TEMIZ_METIN",cleanedText)
            returnIntent.putExtra("HATA_SAYISI",hataSayisi)
            setResult(Activity.RESULT_OK,returnIntent)
            finish()
        }
    }
}