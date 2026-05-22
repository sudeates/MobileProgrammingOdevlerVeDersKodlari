package com.example.sqliteodev

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class UpdateContactActivity : AppCompatActivity() {
    private lateinit var etUpdateName: EditText
    private lateinit var etUpdatePhone: EditText
    private lateinit var btnUpdateContact: Button
    private var contactId = -1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_update_contact)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        etUpdateName = findViewById(R.id.etName)
        etUpdatePhone = findViewById(R.id.etPhone)
        btnUpdateContact = findViewById(R.id.btnUpdate)

        contactId=intent.getIntExtra("CONTACT_ID",-1)
        etUpdatePhone.setText(intent.getStringExtra("CONTACT_PHONE"))
        etUpdatePhone.setText(intent.getStringExtra("CONTACT_NAME"))

        btnUpdateContact.setOnClickListener {
            val name = etUpdateName.text.toString()
            val phone = etUpdatePhone.text.toString()

            if (name.isNotEmpty() && phone.isNotEmpty()) {
                val db = DataBaseHelper(this)
                if (db.updateContact(contactId, name, phone)) {
                    Toast.makeText(this, "Contact updated successfully", Toast.LENGTH_SHORT).show()
                    finish()
                } else {
                    Toast.makeText(this, "An error occurred...", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
            }
        }
    }
}