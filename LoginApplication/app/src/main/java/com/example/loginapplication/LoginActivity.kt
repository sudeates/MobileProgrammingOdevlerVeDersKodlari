package com.example.loginapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class LoginActivity : AppCompatActivity() {
    private lateinit var etLoginUN: EditText
    private lateinit var etLoginPass: EditText
    private lateinit var btnSignIn: Button
    private lateinit var btnViewUsers: Button
    private lateinit var tvRegister: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        etLoginPass = findViewById(R.id.et_LoginPass)
        etLoginUN=findViewById(R.id.et_LoginUN)
        btnSignIn = findViewById(R.id.btn_SignIn)
        tvRegister = findViewById(R.id.tv_Register)
        btnViewUsers = findViewById(R.id.btn_AllUsers)

        btnSignIn.setOnClickListener {
            val userName=etLoginUN.text.toString()
            val password=etLoginPass.text.toString()
            if(userName.isNotEmpty() && password.isNotEmpty()){
                val databaseHandler=DataBaseHelper(this)
                if(databaseHandler.findUser(userName,password)){
                    val welcomeIntent= Intent(applicationContext,WelcomeActivity::class.java)
                    welcomeIntent.putExtra("USERNAME",userName)
                    startActivity(welcomeIntent)
                }
                else{
                    Toast.makeText(applicationContext,"Username or password is wrong",Toast.LENGTH_SHORT).show()
                }
            }
        }
        tvRegister.setOnClickListener {
            val registerIntent = Intent(applicationContext, RegisterActivity::class.java)
            startActivity(registerIntent)
        }
        btnViewUsers.setOnClickListener {
            val userListIntent = Intent(this, ViewUsersActivity::class.java)
            startActivity(userListIntent)
        }
    }
}