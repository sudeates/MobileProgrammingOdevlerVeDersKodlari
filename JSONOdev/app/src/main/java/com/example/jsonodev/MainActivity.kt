package com.example.jsonapplication

import android.os.Bundle
import android.util.Log
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Spinner
import android.widget.Toast
import androidx.activity.R
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONException
import org.json.JSONObject

class MainActivity : AppCompatActivity() {

    // Spinner: öğretim elemanlarını listelemek için kullanılır
    private lateinit var spTeachers: Spinner

    // ListView: seçilen hocanın derslerini listelemek için kullanılır
    private lateinit var lvCourses: ListView

    // Öğretim elemanı isimlerini tutan dizi — Spinner'a bağlanacak
    private var teacherNames: Array<String> = emptyArray()

    // Öğretim elemanlarının sicil numaralarını tutan dizi — ders filtrelemede kullanılır
    private var teacherIds: Array<Int> = emptyArray()

    // Tüm ders bilgilerini (kod, ad, kredi, öğretmen sicil) tutan iç sınıf
    data class Course(val kodu: String, val adi: String, val kredisi: Int, val ogretmenSicil: Int)

    // Tüm dersleri tutan liste
    private var allCourses: ArrayList<Course> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // XML'deki view'lar kod tarafına bağlanır
        spTeachers = findViewById<Spinner>(R.id.sp_Teachers)
        lvCourses = findViewById<ListView>(R.id.lv_Courses)

        // Kullanıcı listeden bir derse tıkladığında kod, ad ve kredi Toast ile gösterilir
        lvCourses.setOnItemClickListener { parent, view, position, id ->
            // Tıklanan ders metni "KOD - ADI (X Kredi)" formatındadır, doğrudan gösterilir
            val selectedCourse = parent.getItemAtPosition(position).toString()
            Toast.makeText(applicationContext, selectedCourse, Toast.LENGTH_SHORT).show()
        }

        // JSON verisinin çekileceği URL
        val url = "https://raw.githubusercontent.com/yasinor/Mobil_Ders/refs/heads/main/school.json"

        // Volley istek kuyruğu oluşturulur
        val queue = Volley.newRequestQueue(this)

        // GET isteği oluşturulur
        val request = StringRequest(
            Request.Method.GET,
            url,
            { response ->
                // İstek başarılı olursa bu blok çalışır
                try {
                    // Ham JSON metni JSONObject'e dönüştürülür
                    val jsonObject = JSONObject(response)

                    // "OgretimElemanlari" anahtarı altındaki dizi alınır
                    val teachers = jsonObject.getJSONArray("OgretimElemanlari")
                    val numberOfTeachers = teachers.length()

                    // Diziler, eleman sayısına göre boyutlandırılır
                    teacherNames = Array(numberOfTeachers) { "" }
                    teacherIds = Array(numberOfTeachers) { 0 }

                    // Her öğretim elemanı için döngü
                    for (i in 0 until numberOfTeachers) {
                        val teacher = teachers.getJSONObject(i)
                        teacherNames[i] = teacher.getString("adi")     // Öğretim elemanının adı
                        teacherIds[i] = teacher.getInt("sicil")        // Sicil numarası
                    }

                    // "Dersler" anahtarı altındaki dizi alınır
                    val dersler = jsonObject.getJSONArray("Dersler")
                    val numberOfCourses = dersler.length()

                    // Tüm dersler allCourses listesine eklenir
                    for (i in 0 until numberOfCourses) {
                        val ders = dersler.getJSONObject(i)
                        val kodu = ders.getString("Kodu")
                        val adi = ders.getString("Adi")
                        val kredisi = ders.getInt("Kredisi")
                        val ogretmenSicil = ders.getInt("OgretmenSicil")
                        allCourses.add(Course(kodu, adi, kredisi, ogretmenSicil))
                    }

                    // ArrayAdapter: teacherNames dizisini Spinner'a bağlar
                    val spinnerAdapter = ArrayAdapter(
                        applicationContext,
                        android.R.layout.simple_spinner_item,
                        teacherNames
                    )
                    // Spinner açıldığında görünecek dropdown item stili
                    spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                    spTeachers.adapter = spinnerAdapter

                    // Spinner'dan bir öğretim elemanı seçildiğinde dersleri filtrele
                    spTeachers.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                        override fun onItemSelected(parent: AdapterView<*>, view: android.view.View?, position: Int, id: Long) {
                            // Seçilen hocanın sicil numarası alınır
                            val selectedSicil = teacherIds[position]

                            // Seçilen hocanın dersleri filtrelenir
                            val filteredCourses = ArrayList<String>()
                            for (course in allCourses) {
                                if (course.ogretmenSicil == selectedSicil) {
                                    // ListView'de gösterilecek metin formatı: "KOD - ADI (X Kredi)"
                                    filteredCourses.add("${course.kodu} - ${course.adi} (${course.kredisi} Kredi)")
                                }
                            }

                            // Filtrelenmiş dersler ListView'e bağlanır
                            val courseAdapter = ArrayAdapter(
                                applicationContext,
                                android.R.layout.simple_list_item_1,
                                filteredCourses
                            )
                            lvCourses.adapter = courseAdapter
                        }

                        override fun onNothingSelected(parent: AdapterView<*>) {
                            // Hiçbir şey seçilmediğinde yapılacak işlem yok
                        }
                    }

                } catch (e: JSONException) {
                    // JSON ayrıştırma sırasında hata oluşursa burası çalışır
                    e.printStackTrace()
                    Toast.makeText(applicationContext, e.message, Toast.LENGTH_SHORT).show()
                }
            },
            { error ->
                // HTTP isteği başarısız olursa burası çalışır
                val message = error.message ?: "Bir hata oluştu"
                Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT).show()
                Log.e("Error", error.message ?: "Bilinmeyen hata")
            }
        )

        // Hazırlanan istek kuyruğa eklenir ve gönderilir
        queue.add(request)
    }
}