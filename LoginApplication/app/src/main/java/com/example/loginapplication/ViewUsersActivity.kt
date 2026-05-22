package com.example.loginapplication

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ViewUsersActivity : AppCompatActivity() {
    private lateinit var lvUserList: ListView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_view_users)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        lvUserList = findViewById<ListView>(R.id.lv_UserList)
        val database = DataBaseHelper(this)
        val userList: List<String> = database.viewAllUsers()
        val adapter = ArrayAdapter<String>(
            this,
            android.R.layout.simple_list_item_1,
            userList
        )
        lvUserList.adapter = adapter
    }
}
