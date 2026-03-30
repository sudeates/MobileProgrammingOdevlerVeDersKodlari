package com.example.layoutapplication

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class FrameActivity : AppCompatActivity() {
    private lateinit var btnVisible: Button
    private lateinit var iv1: ImageView
    private lateinit var iv2: ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_frame)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.GridLayout)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        btnVisible = findViewById(R.id.btn_Visible)
        iv1 = findViewById(R.id.iv_1)
        iv2 = findViewById(R.id.iv_2)

        btnVisible.setOnClickListener {
            iv1.visibility=View.VISIBLE
            iv2.visibility=View.VISIBLE
        }

    }
}