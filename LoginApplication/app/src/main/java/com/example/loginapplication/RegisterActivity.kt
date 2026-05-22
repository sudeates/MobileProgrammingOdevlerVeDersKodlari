package com.example.loginapplication

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class RegisterActivity : AppCompatActivity() {
    private lateinit var etRegisterUN: EditText
    private lateinit var etRegisterName: EditText
    private lateinit var etRegisterMail: EditText
    private lateinit var etRegisterPass: EditText
    private lateinit var etConfirm: EditText
    private lateinit var btnRegister: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_register)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        etConfirm = findViewById(R.id.et_Confirm)
        etRegisterMail = findViewById(R.id.et_RegisterMail)
        etRegisterName = findViewById(R.id.et_RegisterName)
        etRegisterUN = findViewById(R.id.et_RegisterUN)
        etRegisterPass = findViewById(R.id.et_RegisterPass)
        btnRegister = findViewById(R.id.btn_Register)

        btnRegister.setOnClickListener {
            val userName = etRegisterUN.text.toString()
            val password = etRegisterPass.text.toString()
            val name = etRegisterName.text.toString()
            val email = etRegisterMail.text.toString()
            val confirmPass = etConfirm.text.toString()
            if(userName.isNotEmpty() && name.isNotEmpty() && email.isNotEmpty() &&
                password.isNotEmpty() && confirmPass.isNotEmpty()){
                if(password==confirmPass){
                    val dataBaseHelper= DataBaseHelper(this)
                    if(dataBaseHelper.insertUser(userName,name,email,password))
                        Toast.makeText(this,"Your account created successfully",Toast.LENGTH_SHORT).show()
                    else
                        Toast.makeText(this,"An error occured",Toast.LENGTH_SHORT).show()
                }
                else
                    Toast.makeText(this,"Passwords do not match",Toast.LENGTH_SHORT).show()
            }
        }
    }
}