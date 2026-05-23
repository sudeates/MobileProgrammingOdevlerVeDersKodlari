package com.example.lazycolumn

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lazycolumn.ui.theme.LazyColumnTheme

// Sabit bir dil listesi tanımlanıyor - bunlar ekranda listelenecek
// Bu liste Composable dışında tanımlandığı için tüm uygulama boyunca
// tek bir kopya olarak var olur, her recomposition'da yeniden oluşmaz
val languageList = listOf(
    "Kotlin", "Java", "Python", "JavaScript", "TypeScript",
    "C++", "C#", "C", "Go", "Rust", "Swift", "PHP", "Ruby",
    "Scala", "Haskell", "Elixir", "Dart", "Lua", "Perl", "R",
    "Julia", "Groovy", "Objective-C", "F#", "SQL", "Bash",
    "PowerShell", "MATLAB", "Fortran", "Assembly"
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LazyColumnTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    CategorizedLazyColumn(
                        categories = categorizedNames,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
private fun DisplayList(languages:List<String>,modifier:Modifier=Modifier){
    // ADIM 1: Column + forEach
    // Sorun: Tüm liste aynı anda belleğe yüklenir
    // 1000 eleman varsa hepsini oluşturur → performans sorunu
//    Column(modifier=modifier.fillMaxSize()) {
//        languages.forEach {
//            Text(text="$it", color=Color.Red, fontSize = 26.sp)
//        }
//    }
    // ADIM 2: LazyColumn + items
    // LazyColumn sadece ekranda GÖRÜNEN elemanları oluşturur
    // Ekran dışına çıkan eleman bellekten atılır → çok daha verimli
//    LazyColumn(modifier=modifier.fillMaxSize()){
//        items(languages){
//            Text(text="$it", color=Color.Red, fontSize = 26.sp)
//        }
//    }
    // ADIM 3 (aktif): LazyRow - yatay kayan liste
    // LazyColumn gibi çalışır ama yatay kaydırma yapar
    // items(languages): listedeki her eleman için bir Text oluşturur
//    LazyColumn(modifier=modifier.fillMaxSize()) {
//        items(languages){
//            Text(text="$it", color=Color.Red, fontSize = 26.sp)
//        }
//    }
}
// Category: İsimleri alfabetik gruplara ayırmak için veri modeli
// data class otomatik olarak equals, hashCode, toString üretir
data class Category(val firstLetterofName:Char,
                    val nameList:List<String>)
// cotegorizedNames: names map'ini Category listesine dönüştürür
// names → Map<Char, List<String>> formatında, başka bir dosyada tanımlı
// örn: 'A' -> ["Ahmet", "Ayşe"], 'B' -> ["Burak"] gibi
// map { } → her key-value çiftini bir Category nesnesine çevirir
// Bu da Composable dışında tanımlandığı için tek sefer hesaplanır
private val categorizedNames=names.map{
    Category(
        firstLetterofName=it.key,
        nameList=it.value
    )
}
// CategoryHeader: Her alfabetik grubun başlık satırı
// stickyHeader ile kullanılınca liste kaydırılırken başlık yukarıda sabit kalır

@Composable
private fun CategoryHeader(
    firstCharacter:Char,
    modifier: Modifier= Modifier
){
    Text(
        text=firstCharacter.toString(),
        fontSize=16.sp,
        fontWeight= FontWeight.Bold,
        modifier=modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.primaryContainer)
            .padding(16.dp)
    )
}

// -------------------------------------------------------------------
// CategoryItem: Listedeki her bir isim satırı
// İkon + boşluk + isim yan yana gösteriliyor (Row kullanımı)
// -------------------------------------------------------------------
@Composable
private fun CategoryItem(
    name:String,
    modifier: Modifier=Modifier
){
    Row(modifier=modifier
        .fillMaxWidth()
        .background(MaterialTheme.colorScheme.background)
        .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically){
        Icon(
            imageVector=Icons.Default.Person,
            contentDescription = null,
            modifier=Modifier.size(20.dp),
            tint=MaterialTheme.colorScheme.primary
        )
        Spacer(modifier=Modifier.width(12.dp))
        Text(
            text=name,
            fontSize =14.sp
        )
    }
}
// -------------------------------------------------------------------
// CategorizedLazyColumn: Ana liste bileşeni
// Alfabetik grupları stickyHeader ile gösteren LazyColumn
// -------------------------------------------------------------------
@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun CategorizedLazyColumn(
    categories:List<Category>,
    modifier: Modifier=Modifier
){
    LazyColumn(modifier=modifier){
        categories.forEach { category->
            // stickyHeader: Liste yukarı kaydırıldığında bu başlık
            // ekranın üstüne yapışık kalır, sonraki başlık gelince değişir
            // Normal Column'da bu özellik yoktur, sadece LazyColumn'da vardır
            stickyHeader{
                CategoryHeader(category.firstLetterofName)
            }
            // items(): Bu gruba ait isimleri teker teker ekrana basar
            // Sadece görünen satırlar oluşturulur → bellek dostu
            items(category.nameList){
                name-> CategoryItem(name)
            }
        }
    }
}
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    LazyColumnTheme {
        CategorizedLazyColumn(categorizedNames)
    }
}
