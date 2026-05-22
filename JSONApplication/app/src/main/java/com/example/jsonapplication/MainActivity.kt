package com.example.jsonapplication

import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.ListView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.squareup.picasso.Picasso
import org.json.JSONException
import org.json.JSONObject

//implementation("com.android.volley:volley:1.2.1")
//implementation("com.squareup.picasso:picasso:2.8") bunları dependencies kısmına ekle
//<uses-permission android:name="android.permission.INTERNET"></uses-permission> AndroidManifest.xml dosyasına ekle
class MainActivity : AppCompatActivity() {
    private lateinit var lvCompanies: ListView
    private lateinit var ivLogo: ImageView
    private var companyInfo:Array<String> = emptyArray()
    private var imageURLs: Array<String> = emptyArray()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        lvCompanies=findViewById<ListView>(R.id.lv_Companies)
        ivLogo=findViewById<ImageView>(R.id.iv_Logo)

        lvCompanies.setOnItemClickListener{parent, view, position, id ->
            val imageURL=imageURLs[position]
            Picasso.get().load(imageURLs[position]).into(ivLogo)
        }
        var url="https://raw.githubusercontent.com/yasinor/Mobil_Ders/refs/heads/main/recyle.json"
        val queue=Volley.newRequestQueue(this)
        val request= StringRequest(
            Request.Method.GET,
            url,
            {response->
                try {
                    val jsonObject = JSONObject(response)
                    val companies = jsonObject.getJSONArray("Companies")
                    val numberOfCompanies = companies.length()

                    companyInfo = Array(numberOfCompanies) { "" }
                    imageURLs = Array(numberOfCompanies) { "" }
                    for (i in 0 until numberOfCompanies) {
                        val company = companies.getJSONObject(i)
                        val heading = company.getString("Heading")
                        val detail = company.getString("Detail")
                        companyInfo[i] = "$heading-$detail"
                        imageURLs[i] = company.getString("ImageURL")
                    }
                    val adapter=ArrayAdapter(
                        applicationContext,
                        android.R.layout.simple_list_item_1,
                        companyInfo
                    )
                    lvCompanies.adapter=adapter
                }catch (e: JSONException){
                    e.printStackTrace()
                    Toast.makeText(applicationContext, e.message, Toast.LENGTH_SHORT).show()
                }
            }, { error ->
                Toast.makeText(applicationContext, error.message ?: "İstek Hatası", Toast.LENGTH_SHORT).show()
                Log.e("error", error.message ?: "Bilinmeyen Hata")
            }
            )
        queue.add(request)
    }
}