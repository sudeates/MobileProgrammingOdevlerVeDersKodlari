package com.example.layoutapplication

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class LinearActivity : AppCompatActivity() {
    private lateinit var btnLogin: Button
    private lateinit var etUserName: EditText
    private lateinit var etPassword: EditText
    private val trueUN = "yasin"
    private val truePass = "12345"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_linear)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        btnLogin = findViewById(R.id.btn_Login2)
        etPassword = findViewById(R.id.et_Password)
        etUserName = findViewById(R.id.et_UserName)

        btnLogin.setOnClickListener {
            val userName=etUserName.text.toString()
            val password=etPassword.text.toString()
            if(userName==trueUN && password==truePass)
                Toast.makeText(applicationContext,"You have logined",
                    Toast.LENGTH_SHORT).show()
            else
                Toast.makeText(applicationContext,"Your password or username is wrong",
                    Toast.LENGTH_SHORT).show()
        }

    }
}