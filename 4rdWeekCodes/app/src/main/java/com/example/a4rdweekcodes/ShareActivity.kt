package com.example.a4rdweekcodes

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ShareActivity : AppCompatActivity() {
    private lateinit var etShare: EditText
    private lateinit var ibShare: ImageButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_share)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        etShare=findViewById(R.id.et_Share)
        ibShare=findViewById(R.id.ib_Share)

        ibShare.setOnClickListener {
            val text=etShare.text.toString()
            if(text.isNotEmpty()){
                val share= Intent(Intent.ACTION_SEND)
                share.type="text/plain"
                share.putExtra(Intent.EXTRA_TEXT,text)
                startActivity(Intent.createChooser(share,"Select a platform"))
            }
        }
    }
}