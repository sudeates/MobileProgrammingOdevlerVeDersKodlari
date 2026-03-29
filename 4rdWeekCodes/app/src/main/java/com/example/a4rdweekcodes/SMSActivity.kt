package com.example.a4rdweekcodes

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class SMSActivity : AppCompatActivity() {
    private lateinit var etSMSText: EditText
    private lateinit var etSMSNumber: EditText
    private lateinit var btnSendSMS: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_smsactivity)
        btnSendSMS = findViewById(R.id.btn_SendSMS)
        etSMSText = findViewById(R.id.et_SMSText)
        etSMSNumber = findViewById(R.id.et_SMSNumber)
        btnSendSMS.setOnClickListener {
            val phoneNumber = etSMSNumber.text.toString()
            val text = etSMSText.text.toString()
            val smsIntent= Intent(Intent.ACTION_SENDTO)
            smsIntent.data= Uri.parse("sms to:$phoneNumber")
            smsIntent.putExtra("sms_body",text)
            startActivity(smsIntent)
        }
    }
}