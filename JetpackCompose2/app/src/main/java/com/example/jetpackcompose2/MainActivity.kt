package com.example.jetpackcompose2

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jetpackcompose2.ui.theme.JetpackCompose2Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JetpackCompose2Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    //MainScreen(modifier = Modifier.padding(innerPadding))
                    SimpleLogin(modifier=Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun MainScreen(modifier: Modifier= Modifier){
    // STEP 1 - Düz değişken kullanımı
    // Sorun: counter değişse bile ekran yenilenmez, Composable tekrar çağrılmaz
    // var counter = 0

    // STEP 2 - Composable'ın yeniden çağrılması gerektiği fark edildi
    // Sorun: Kotlin değişkeni değişince Compose bunu "fark etmiyor", ekran hâlâ güncellenmiyor
    // var counter = 0

    // STEP 3 - mutableStateOf eklendi, counter.value ile erişim
    // Artık değer değişince Compose bunu fark eder ve ekranı yeniden çizer (recomposition)
    // Ama her seferinde remember olmadan değer sıfırlanır
    // var counter = remember { mutableStateOf(0) }
    // counter.value++ şeklinde kullanım gerekiyordu

    // STEP 4 (aktif olan) - "by" delegesi ile daha sade kullanım
    // remember: Composable yeniden çizilse bile değeri hafızada tutar
    // mutableStateOf: değer değişince Compose'u haberdar eder → ekran güncellenir
    // "by" sayesinde counter.value yerine direkt counter yazabiliyoruz
    var counter by remember { mutableStateOf(0)}
    // Lambda fonksiyonlar: butona tıklanınca ne olacağını tanımlar
    var increaseCounter:()-> Unit={
        Log.i("MainScreen", "Counter value is: $counter")
        counter++
    }
    var decraseCounter:()-> Unit={
        Log.i("MainScreen", "Counter value is: $counter")
        counter--
    }
    Column(modifier= Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,){
        Row(modifier= Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically,){
            Button(onClick = decraseCounter) {
                Text(text="Decrease")
            }
            Text(text = "$counter")
            Button(onClick = increaseCounter) {
                Text(text="Increase")
            }
        }
    }
}

@Composable
fun SimpleLogin(modifier: Modifier= Modifier){
    val validUsername="admin"
    val validPassword="password123"

    var username by remember { mutableStateOf("") }
    var password by remember {mutableStateOf("")}
    var loginMessage by remember { mutableStateOf("") }
    var isSuccess by remember { mutableStateOf(false) }
    Column(modifier= Modifier
        .fillMaxSize()
        .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ){
        Text(
            text = "Giris Yap",
            fontSize= 32.sp,
            style= MaterialTheme.typography.headlineLarge,
            modifier= Modifier.padding(bottom = 32.dp)
        )
        OutlinedTextField(
            value=username,
            onValueChange ={username=it},
            label={Text("Kullanıcı Adı")},
            keyboardOptions =KeyboardOptions(keyboardType = KeyboardType.Text),
            modifier= Modifier.fillMaxWidth(),
            singleLine = true
        )
        Spacer(modifier= Modifier.height(24.dp))
        TextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Şifre") },
            visualTransformation = PasswordVisualTransformation(), // Şifreyi gizler
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )
        Spacer(modifier= Modifier.height(24.dp))
        Button(onClick = {
            if (username==validUsername && password==validPassword){
                loginMessage = "Giriş Başarılı! Hoş geldiniz."
                isSuccess = true
            }else{
                loginMessage = "Hatalı kullanıcı adı veya şifre!"
                isSuccess = false
            }
        }, modifier= Modifier
            .fillMaxWidth()
            .height(50.dp)) {
            Text("Login")
        }
        if(loginMessage.isNotEmpty()){
            Text(
                text = loginMessage,
                // Üçlü ifade: başarılıysa yeşil, değilse kırmızı
                color = if (isSuccess) Color(0xFF4CAF50) else Color.Red,
                modifier = Modifier.padding(top = 16.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    JetpackCompose2Theme {
        SimpleLogin()
    }
}