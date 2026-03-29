package com.example.a4rdweekcodes

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MailActivity : AppCompatActivity() {
    private lateinit var etAdress: EditText
    private lateinit var etSubject: EditText
    private lateinit var etText: EditText
    private lateinit var ibMail: ImageButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_mail)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        etAdress = findViewById(R.id.et_MailAdress)
        etSubject = findViewById(R.id.et_MailSubject)
        etText = findViewById(R.id.et_MailText)
        ibMail = findViewById(R.id.ib_Mail)

        ibMail.setOnClickListener {
            val adress = etAdress.text.toString()
            val subject = etSubject.text.toString()
            val text = etText.text.toString()
            if(adress.isNotEmpty() && subject.isNotEmpty() && text.isNotEmpty()){
                val mail= Intent(Intent.ACTION_SEND)
                mail.type="message/rfc822"
                mail.putExtra(Intent.EXTRA_EMAIL, arrayOf(adress))
                mail.putExtra(Intent.EXTRA_SUBJECT,subject)
                mail.putExtra(Intent.EXTRA_TEXT,text)
                startActivity(mail)
            }
        }
    }
}