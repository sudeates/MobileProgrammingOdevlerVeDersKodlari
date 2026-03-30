package com.example.permissionodev

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.telephony.SmsManager
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private lateinit var etPhoneNumber: EditText
    private lateinit var btnCamera: Button
    private lateinit var ivPhoto: ImageView
    private var phoneNumber: String = ""
    companion object {
        private const val CAMERA_CODE = 100
        private const val PERMISSION_CODE = 101
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        etPhoneNumber = findViewById(R.id.et_PhoneNumber)
        btnCamera = findViewById(R.id.btn_Capture)
        ivPhoto = findViewById(R.id.iv_Photo)

        btnCamera.setOnClickListener {
            phoneNumber=etPhoneNumber.text.toString()
            if (phoneNumber.isNotEmpty()) {
                openCameraProcess()
            }
            else{
                Toast.makeText(this,"Telefon Numarası Giriniz",Toast.LENGTH_SHORT).show()
            }
        }
    }
    private fun openCameraProcess(){
        if(ActivityCompat.checkSelfPermission(this,Manifest.permission.CAMERA)== PackageManager.PERMISSION_GRANTED &&
            ActivityCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS)== PackageManager.PERMISSION_GRANTED){
            val cameraIntent= Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivityForResult(cameraIntent, CAMERA_CODE)
        }
        else{
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CAMERA,Manifest.permission.WRITE_EXTERNAL_STORAGE), PERMISSION_CODE)
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(requestCode== PERMISSION_CODE){
         if(grantResults.isNotEmpty() && grantResults[0]==PackageManager.PERMISSION_GRANTED && grantResults[1]==PackageManager.PERMISSION_GRANTED){
             val cameraIntent= Intent(MediaStore.ACTION_IMAGE_CAPTURE)
             startActivityForResult(cameraIntent, CAMERA_CODE)
         }
            else{
             Toast.makeText(applicationContext,"you have denied permission",Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode== CAMERA_CODE && resultCode==RESULT_OK){
            val photo=data?.extras?.get("data") as Bitmap
            ivPhoto.setImageBitmap(photo)

            try{
                val smsManager:SmsManager= SmsManager.getDefault()
                smsManager.sendTextMessage(phoneNumber, null, "Telefondan fotoğraf çekildi", null, null)
                Toast.makeText(applicationContext, "Fotoğraf çekildi ve SMS gönderildi", Toast.LENGTH_SHORT).show()
            } catch (e: Exception) {
                Toast.makeText(applicationContext, "SMS Hatası", Toast.LENGTH_SHORT).show()
            }
        }
    }
}