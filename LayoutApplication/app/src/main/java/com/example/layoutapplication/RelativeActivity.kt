package com.example.layoutapplication

import android.graphics.Color
import android.os.Bundle
import android.provider.CalendarContract
import android.view.View
import android.widget.ImageView
import android.widget.RelativeLayout
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class RelativeActivity : AppCompatActivity() {
    private lateinit var relative: RelativeLayout
    private lateinit var ivImage: ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_relative)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        relative = findViewById(R.id.main)
        ivImage = findViewById(R.id.iv_Image)
    }
    fun ButtonClick(view: View){
        val id=view.id
        if (id==R.id.btn_Color){
            relative.setBackgroundColor(Color.BLUE)
        }
        else{
            ivImage.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.android))
        }
    }
}