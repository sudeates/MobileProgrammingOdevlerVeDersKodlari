package com.example.sqliteodev

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private lateinit var lvContacts: ListView
    private var nameList = ArrayList<String>()
    private var idList = ArrayList<Int>()
    private var selectedPosition = -1
    companion object {
        const val CALL_PERMISSION_CODE = 101
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        lvContacts = findViewById(R.id.lvContacts)
        loadContacts()

        lvContacts.setOnItemClickListener { _, _, position, _ ->
            selectedPosition = position
            Toast.makeText(this, "${nameList[position]} selected", Toast.LENGTH_SHORT).show()
        }
    }
    override fun onResume() {
        super.onResume()
        loadContacts()
    }
    private fun loadContacts(){
        val db=DataBaseHelper(this)
        nameList=db.getALlNames()
        idList=db.getAllIds()

        val adapter= ArrayAdapter(this,android.R.layout.simple_list_item_1,nameList)
        lvContacts.adapter=adapter
        selectedPosition=-1
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_new_contact -> {
                startActivity(Intent(this, NewContactActivity::class.java))
                return true
            }

            R.id.menu_update -> {
                if (selectedPosition == -1) {
                    Toast.makeText(this, "Please select a contact first", Toast.LENGTH_SHORT).show()
                } else {
                    val selectedId = idList[selectedPosition]
                    val selectedName = nameList[selectedPosition]
                    val db = DataBaseHelper(this)
                    val selectedPhone = db.getPhoneNumber(selectedId)

                    val updateIntent = Intent(this, UpdateContactActivity::class.java)
                    updateIntent.putExtra("CONTACT_ID", selectedId)
                    updateIntent.putExtra("CONTACT_NAME", selectedName)
                    updateIntent.putExtra("CONTACT_PHONE", selectedPhone)
                    startActivity(updateIntent)
                }
                return true
            }

            R.id.menu_delete -> {
                if (selectedPosition == -1) {
                    Toast.makeText(this, "Please select a contact first", Toast.LENGTH_SHORT).show()
                } else {
                    val selectedId = idList[selectedPosition]
                    val selectedName = nameList[selectedPosition]

                    AlertDialog.Builder(this)
                        .setTitle("Delete Contact")
                        .setMessage("Are you sure you want to delete $selectedName?")
                        .setPositiveButton("Yes") { _, _ ->
                            val db = DataBaseHelper(this)
                            if (db.deleteContact(selectedId)) {
                                Toast.makeText(this, "Contact deleted", Toast.LENGTH_SHORT).show()
                                loadContacts()
                            } else {
                                Toast.makeText(this, "An error occurred...", Toast.LENGTH_SHORT).show()
                            }
                        }
                        .setNegativeButton("No", null)
                        .show()
                }
                return true
            }

            R.id.menu_call -> {
                if (selectedPosition == -1) {
                    Toast.makeText(this, "Please select a contact first", Toast.LENGTH_SHORT).show()
                } else {
                    val selectedId = idList[selectedPosition]
                    val db = DataBaseHelper(this)
                    val phone = db.getPhoneNumber(selectedId)
                    makeCall(phone)
                }
                return true
            }

            R.id.menu_exit -> {
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun makeCall(phoneNumber: String) {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE)
            == PackageManager.PERMISSION_GRANTED
        ) {
            val callIntent = Intent(Intent.ACTION_CALL, Uri.parse("tel:$phoneNumber"))
            startActivity(callIntent)
        } else {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.CALL_PHONE),
                CALL_PERMISSION_CODE
            )
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == CALL_PERMISSION_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                if (selectedPosition != -1) {
                    val db = DataBaseHelper(this)
                    val phone = db.getPhoneNumber(idList[selectedPosition])
                    makeCall(phone)
                }
            } else {
                Toast.makeText(this, "Call permission denied", Toast.LENGTH_SHORT).show()
            }
        }
    }
}