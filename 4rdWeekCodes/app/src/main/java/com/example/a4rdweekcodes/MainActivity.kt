package com.example.a4rdweekcodes

import android.app.Activity
import android.app.ComponentCaller
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity(), View.OnClickListener{
    private lateinit var btnSMS: Button
    private lateinit var btnMail: Button
    private lateinit var btnShare: Button
    private lateinit var btnLink: Button
    private lateinit var btnReturn: Button
    private lateinit var tvName: TextView
    companion object{
        private const val REQUEST_CODE=100
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnSMS = findViewById(R.id.btn_SMS)
        btnShare = findViewById(R.id.btn_Share)
        btnMail = findViewById(R.id.btn_Mail)
        btnReturn = findViewById(R.id.btn_ReturnName)
        btnLink = findViewById(R.id.btn_Link)
        tvName = findViewById(R.id.tv_Name)

        btnLink.setOnClickListener(this)
        btnReturn.setOnClickListener(this)
        btnMail.setOnClickListener(this)
        btnSMS.setOnClickListener(this)
        btnShare.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when(v.id){
            R.id.btn_Link -> {
                val sms = Intent(this, LinkActivity::class.java)
                startActivity(sms)
            }
            R.id.btn_Mail -> {
                val mail = Intent(this, MailActivity::class.java)
                startActivity(mail)
            }
            R.id.btn_Share -> {
                val share = Intent(this, ShareActivity::class.java)
                startActivity(share)
            }
            R.id.btn_Link -> {
                val link = Intent(this, LinkActivity::class.java)
                startActivity(link)
            }
            R.id.btn_ReturnName -> {
                val intent=Intent(this, ReturnActivity::class.java)
                startActivityForResult(intent,100)
            }
        }
    }

    override fun onActivityResult(
        requestCode: Int,
        resultCode: Int,
        returnIntent: Intent?
    ) {
            super.onActivityResult(requestCode, resultCode, returnIntent)
        if(requestCode==100 && resultCode == Activity.RESULT_OK){
            val name= returnIntent?.getStringExtra("NAME")
            tvName.text=name
        }
    }
}