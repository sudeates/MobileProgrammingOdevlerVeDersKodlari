package com.example.layoutapplication

import android.content.Intent
import android.os.Bundle

import android.view.View
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity(), View.OnClickListener {
    lateinit var btnLinear:Button
    lateinit var btnRelative:Button
    lateinit var btnFrame:Button
    lateinit var btnGrid:Button
    lateinit var btnTable:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        btnLinear=findViewById(R.id.btn_Linear)
        btnRelative=findViewById(R.id.btn_Relative)
        btnFrame=findViewById(R.id.btn_Frame)
        btnGrid=findViewById(R.id.btn_Grid)
        btnTable=findViewById(R.id.btn_Table)

        btnLinear.setOnClickListener(this)
        btnRelative.setOnClickListener(this)
        btnFrame.setOnClickListener(this)
        btnGrid.setOnClickListener(this)
        btnTable.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        var intent:Intent
        when(v.id){
            R.id.btn_Linear->{
                intent=Intent(this,LinearActivity::class.java)
                startActivity(intent)
            }
            R.id.btn_Relative->{
                intent=Intent(this,RelativeActivity::class.java)
                startActivity(intent)
            }
            R.id.btn_Frame->{
                intent=Intent(this,FrameActivity::class.java)
                startActivity(intent)
            }
            R.id.btn_Grid->{
                intent=Intent(this,GridActivity::class.java)
                startActivity(intent)
            }
            R.id.btn_Table->{
                intent=Intent(this,TableActivity::class.java)
                startActivity(intent)
            }
        }
    }
}