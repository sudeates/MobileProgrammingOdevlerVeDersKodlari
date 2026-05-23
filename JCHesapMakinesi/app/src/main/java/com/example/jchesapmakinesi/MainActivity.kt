package com.example.jchesapmakinesi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
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
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jchesapmakinesi.ui.theme.JCHesapMakinesiTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JCHesapMakinesiTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    HesapMakinesi(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun HesapMakinesi(modifier: Modifier=Modifier){
    var sayi1 by remember { mutableStateOf("") }
    var sayi2 by remember {mutableStateOf("") }
    var sonuc by remember {mutableStateOf("Result")}

    val hesapla: (String) -> Unit={ islem ->
        val a=sayi1.toDouble()
        val b=sayi2.toDouble()
        if(a==null && b==null){
            sonuc="error"
        }else{
            sonuc= when(islem){
                "+" -> (a+b).toString()
                "-" -> (a-b).toString()
                "*" -> (a*b).toString()
                "/" -> (a/b).toString()
                else -> ""
            }
        }
    }
    Column(modifier= Modifier
        .fillMaxSize()
        .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text="Hesap Makinesi",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            modifier= Modifier.padding(bottom = 32.dp)
        )
        OutlinedTextField(
            value = sayi1,
            onValueChange = {sayi1=it},
            label = {Text("Sayi 1")},
            keyboardOptions = KeyboardOptions(keyboardType=KeyboardType.Number),
            modifier= Modifier.fillMaxWidth(),
            singleLine = true
        )
        Spacer(modifier=Modifier.height(16.dp))
        OutlinedTextField(
            value = sayi2,
            onValueChange = { sayi2 = it },
            label = { Text("İkinci Sayı") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )
        Spacer(modifier = Modifier.height(24.dp))
        Row(modifier= Modifier
            .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly) {
            Button(onClick = {hesapla("+")},
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1565C0))) {
                Text(text = "+", fontSize = 26.sp)
            }
            Button(onClick = {hesapla("-")},
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1565C0))) {
                Text(text = "-", fontSize = 26.sp)
            }
            Button(onClick = {hesapla("/")},
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1565C0))) {
                Text(text = "/", fontSize = 26.sp)
            }
            Button(onClick = {hesapla("*")},
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1565C0))) {
                Text(text = "*", fontSize = 26.sp)
            }
        }
        Spacer(modifier = Modifier.height(32.dp))
        OutlinedTextField(
            value = sonuc,
            onValueChange = {},
            readOnly = true,
            label = { Text("Sonuç") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )
    }
}



@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    JCHesapMakinesiTheme {
        HesapMakinesi()
    }
}