package com.example.jetpackcomposeodevi

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jetpackcomposeodevi.ui.theme.JetpackComposeOdeviTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JetpackComposeOdeviTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    KartvizitEkrani(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}
@Composable
fun KartvizitEkrani(modifier: Modifier){
    val context= LocalContext.current
    Column(modifier= Modifier
        .fillMaxSize()
        .background(Color.LightGray)
        .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {
        //profil fotografı
        Image(painter = painterResource(id=R.drawable.profile),
            contentDescription = null,
            modifier = Modifier
                .size(120.dp)
                .clip(CircleShape))
        Spacer(modifier=Modifier.height(16.dp))
        //ad soyad
        Text(
            text = "Sude Ates",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            fontFamily = FontFamily.SansSerif
        )
        Spacer(modifier= Modifier.height(8.dp))
        //Meslek
        Text(
            text="Bilgisayar Mühendisi",
            fontSize = 18.sp,
            fontFamily = FontFamily.SansSerif,
            fontStyle= FontStyle.Italic,
            color=Color.DarkGray
        )
        Spacer(modifier= Modifier.height(24.dp))
        //İletisim Bilgileri
        //eposta
        Row(verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center){
            Text(
                text = "Eposta:",
                fontWeight=FontWeight.Bold,
                color=Color.Black
            )
            Text(
                text = "cartcurt@gmail.com",
                color=Color.DarkGray
            )
        }
        Spacer(modifier=Modifier.height(8.dp))
        //telefon
        Row(verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center){
            Text(
                text = "Telefon:",
                fontWeight=FontWeight.Bold,
                color=Color.Black
            )
            Text(
                text = "+90 578 123 45 67",
                color=Color.DarkGray
            )
        }
        Spacer(modifier=Modifier.height(32.dp))
        //Button
        Button(onClick={
            Toast.makeText(context,"Mesajiniz iletildi",Toast.LENGTH_SHORT).show()
        },
            shape= RoundedCornerShape(16.dp),
            colors = ButtonDefaults.buttonColors(Color(0xFF1565C0))
            ){
            Text(text="İletisime gec",
                color=Color.White,
                fontSize=16.sp)
            }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    JetpackComposeOdeviTheme {
        KartvizitEkrani(modifier = Modifier)
    }
}