package com.example.callapplication

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private lateinit var etPhoneNumber: EditText
    private lateinit var ibCall: ImageButton
    private var phoneNumber: String = ""

    companion object {
        private const val CALL_CODE = 20
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etPhoneNumber=findViewById(R.id.et_PhoneNumber)
        ibCall=findViewById(R.id.ib_Call)

        ibCall.setOnClickListener {
            phoneNumber = etPhoneNumber.text.toString()
            if (phoneNumber.isNotEmpty()) {
                Call()
            }
        }
    }

    private fun Call(){
        val callIntent= Intent(Intent.ACTION_CALL)
        callIntent.data = Uri.parse("tel:$phoneNumber")

        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE)== PackageManager.PERMISSION_GRANTED){
            startActivity(callIntent)
        }else{
            ActivityCompat.requestPermissions(this,arrayOf(Manifest.permission.CALL_PHONE),CALL_CODE)
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String?>,
                                            grantResults: IntArray, deviceId: Int ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults, deviceId)
        if(requestCode==CALL_CODE){
            if(grantResults[0]== PackageManager.PERMISSION_GRANTED)
                Call()
            else
                Toast.makeText(applicationContext, "You have denied to call process", Toast.LENGTH_SHORT).show()
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.option_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.item_Camera -> Toast.makeText(this, "Camera is selected", Toast.LENGTH_SHORT).show()
            R.id.item_Share -> Toast.makeText(this, "Share is selected", Toast.LENGTH_SHORT).show()
            R.id.item_Exit -> System.exit(0)
        }
        return true
    }
}