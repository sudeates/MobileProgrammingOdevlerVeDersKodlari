package com.example.sqliteodev

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class NewContactActivity : AppCompatActivity() {
    private lateinit var etNewName: EditText
    private lateinit var etNewPhone: EditText
    private lateinit var btnSaveContact: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_new_contact)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        etNewName = findViewById(R.id.etName)
        etNewPhone = findViewById(R.id.etPhone)
        btnSaveContact = findViewById(R.id.btnSave)

        btnSaveContact.setOnClickListener {
            val name = etNewName.text.toString()
            val phone = etNewPhone.text.toString()
            if(name.isNotEmpty() && phone.isNotEmpty()){
                val db=DataBaseHelper(this)
                if(db.insertContact(name,phone)){
                    Toast.makeText(this, "Contact saved successfully", Toast.LENGTH_SHORT).show()
                    finish()
                }
                else{
                    Toast.makeText(this, "An error occurred...", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
            }
        }
    }
}