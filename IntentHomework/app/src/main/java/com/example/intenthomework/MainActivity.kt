package com.example.intenthomework

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private lateinit var btnGit: Button
    companion object{
        private const val REQUEST_CODE=100
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnGit = findViewById(R.id.btn_Git)
        btnGit.setOnClickListener {
            val intent= Intent(this,SecondActivity::class.java)
            startActivityForResult(intent, REQUEST_CODE)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode==100 && resultCode==RESULT_OK){
            val temizMetin=data?.getStringExtra("TEMİZ_METIN")
            val hataSayisi=data?.getIntExtra("HATA_SAYISI",0)

            Toast.makeText(this,"Temiz: $temizMetin, Hata Sayısı: $hataSayisi",Toast.LENGTH_SHORT).show()
        }
    }
}