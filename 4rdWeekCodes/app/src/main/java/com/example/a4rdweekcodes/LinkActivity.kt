package com.example.a4rdweekcodes

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class LinkActivity : AppCompatActivity() {
    private lateinit var tvLink: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_link)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        tvLink=findViewById(R.id.tv_Link)

        tvLink.setOnClickListener {
            val link= Intent(Intent.ACTION_VIEW)
            link.data= Uri.parse("https://www.karabuk.edu.tr")
            startActivity(link)
        }
    }
}