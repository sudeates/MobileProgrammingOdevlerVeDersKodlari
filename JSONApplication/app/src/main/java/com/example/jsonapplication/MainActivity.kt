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

    // ListView: şirket isimlerini ve detaylarını listelemek için kullanılır
    private lateinit var lvCompanies: ListView

    // ImageView: seçilen şirketin logosunu göstermek için kullanılır
    private lateinit var ivLogo: ImageView

    // Şirket bilgilerini (isim + detay) tutan dizi — ListView'e bağlanacak
    private var companyInfo: Array<String> = emptyArray()

    // Her şirkete ait logo URL'lerini tutan dizi — Picasso ile resim yüklemek için kullanılır
    private var imageURLs: Array<String> = emptyArray()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge() // Ekranın tüm alanını kullanmak için edge-to-edge modu açılır
        setContentView(R.layout.activity_main)

        // Sistem çubuklarının (status bar, navigation bar) üstüne içerik gelmemesi için padding ayarlanır
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // XML'deki view'lar kod tarafına bağlanır
        lvCompanies = findViewById<ListView>(R.id.lv_Companies)
        ivLogo = findViewById<ImageView>(R.id.iv_Logo)

        // Kullanıcı listeden bir şirkete tıkladığında o şirketin logosunu göster
        lvCompanies.setOnItemClickListener { parent, view, position, id ->
            // Tıklanan pozisyondaki URL alınır
            val imageUrl = imageURLs[position]
            // Picasso kütüphanesi ile URL'den resim indirilip ImageView'a yüklenir
            Picasso.get().load(imageUrl).into(ivLogo)
        }

        // JSON verisinin çekileceği URL
        val url = "https://raw.githubusercontent.com/yasinor/Mobil_Ders/refs/heads/main/recyle.json"

        // Volley istek kuyruğu oluşturulur — tüm HTTP istekleri bu kuyruk üzerinden yönetilir
        val queue = Volley.newRequestQueue(this)

        // GET isteği oluşturulur: URL'ye gidip string (JSON metni) olarak cevap alınır
        val request = StringRequest(
            Request.Method.GET,
            url,
            { response ->
                // İstek başarılı olursa bu blok çalışır, response = ham JSON metni
                try {
                    // Ham JSON metni JSONObject'e dönüştürülür
                    val jsonObject = JSONObject(response)

                    // "Companies" anahtarı altındaki dizi alınır
                    // Key büyük/küçük harf duyarlıdır: "Companies" ile "companies" farklıdır
                    val companies = jsonObject.getJSONArray("Companies")

                    // Dizideki eleman sayısı alınır
                    val numberOfCompanies = companies.length()

                    // Diziler, eleman sayısına göre yeniden boyutlandırılır
                    companyInfo = Array(numberOfCompanies) { "" }
                    imageURLs = Array(numberOfCompanies) { "" }

                    // Her şirket için döngü: JSON'dan veriler okunup dizilere yazılır
                    for (i in 0 until numberOfCompanies) {
                        val company = companies.getJSONObject(i) // i. şirket objesi alınır
                        val heading = company.getString("Heading") // Şirket adı
                        val detail = company.getString("Detail")   // Şirket detayı
                        companyInfo[i] = "$heading - $detail"      // ListView'de gösterilecek metin
                        imageURLs[i] = company.getString("ImageURL") // Logo URL'si
                    }

                    // ArrayAdapter: companyInfo dizisini ListView'e bağlar
                    val adapter = ArrayAdapter(
                        applicationContext,
                        android.R.layout.simple_list_item_1, // Hazır tek satırlık liste item layout'u
                        companyInfo
                    )
                    lvCompanies.adapter = adapter // Adapter ListView'e atanır, liste ekranda görünür

                } catch (e: JSONException) {
                    // JSON ayrıştırma sırasında hata oluşursa burası çalışır
                    e.printStackTrace() // Hata detayı Logcat'e yazdırılır
                    Toast.makeText(applicationContext, e.message, Toast.LENGTH_SHORT).show()
                }
            },
            { error ->
                // HTTP isteği başarısız olursa (internet yok, sunucu hatası vb.) burası çalışır
                val message = error.message ?: "Bir hata oluştu"
                Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT).show()
                Log.e("Error", error.message ?: "Bilinmeyen hata") // Logcat'e hata yazılır
            }
        )

        // Hazırlanan istek kuyruğa eklenir ve gönderilir
        queue.add(request)
    }
}