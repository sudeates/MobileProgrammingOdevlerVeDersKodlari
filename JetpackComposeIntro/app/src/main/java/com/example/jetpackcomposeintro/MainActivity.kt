package com.example.jetpackcomposeintro

import androidx.compose.ui.graphics.Color
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jetpackcomposeintro.ui.theme.JetpackComposeIntroTheme
import org.w3c.dom.Text

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JetpackComposeIntroTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    firstLesson(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun firstLesson(modifier: Modifier){
    val context= LocalContext.current.applicationContext
    val clickMethod={ Toast.makeText(context,"You clicked",Toast.LENGTH_SHORT).show()}
//    Text(text="Hello jetpack compose",
//        color=Color.Red,
//        fontSize = 24.sp,
//        fontFamily = FontFamily.Monospace,
//        fontStyle = FontStyle.Italic,
//        modifier=Modifier.padding(30.dp).background(Color.LightGray).clickable{clickMethod()})
//    Column(modifier= Modifier.fillMaxSize(),
//        verticalArrangement = Arrangement.SpaceAround,
//        horizontalAlignment = Alignment.CenterHorizontally) {
//        Text(text="Text1")
//        Text(text="Text2")
//        Text(text="Text3")
//    }
//    Row(modifier=Modifier.fillMaxSize(),
//        horizontalArrangement = Arrangement.SpaceAround,
//        verticalAlignment = Alignment.CenterVertically) {
//        Text(text="Text1")
//        Text(text="Text2")
//        Text(text="Text3")
//    }
//    Box(modifier=Modifier.fillMaxSize(),
//        contentAlignment = Alignment.Center){
//        Text("Box aligment")
//    }
//    Box(modifier=Modifier.fillMaxSize().background(Color.Yellow).padding(10.dp),
//        contentAlignment = Alignment.Center){
//        Box(modifier=Modifier
//            .height(300.dp)
//            .width(300.dp)
//            .background(Color.Blue)){
//            Text(text="Hello class",
//                modifier=Modifier.align(Alignment.Center),
//                color=Color.White,
//                fontSize = 24.sp)
//        }
//    }
//    Box(modifier=Modifier.fillMaxSize().padding(20.dp)){
//        Text(text="TopStart",modifier=Modifier.align(Alignment.TopStart),color=Color.Red)
//        Text(text="TopCenter",modifier= Modifier.align(Alignment.TopCenter),color=Color.Green)
//        Text(text="TopEnd",modifier=Modifier.align(Alignment.TopEnd),color=Color.Magenta)
//        Text(text="BottomStart",modifier=Modifier.align(Alignment.BottomStart))
//        Text(text="BottomCenter",modifier=Modifier.align(Alignment.BottomCenter))
//        Text(text="BottomEnd",modifier=Modifier.align(Alignment.BottomEnd))
//        Text(text="Center",modifier=Modifier.align(Alignment.Center))
//        Text(text="CenterStart",modifier=Modifier.align(Alignment.CenterStart))
//        Text(text="CenterEnd",modifier= Modifier.align(Alignment.CenterEnd))
//    }
//    Image(painter= painterResource(id=R.drawable.jetpack),
//        contentDescription=null,
//        alignment = Alignment.BottomCenter)
    Column(Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally){
        Button(onClick={ Log.i("MainScreen","Login Succesfull")},
            shape= RoundedCornerShape(10.dp),
            colors= ButtonDefaults.buttonColors(Color.Red)){
            Text(text="Login",color=Color.Black)
        }
        Image(painter=painterResource(id=R.drawable.jetpack),
            contentDescription = null)
        Text(
            text="Hello class",
            color=Color.Blue,
            fontSize= 24.sp
        )
    }
}
@Preview(showBackground = true)
@Composable
fun DefaultPreview(){
    JetpackComposeIntroTheme {
        firstLesson(
            modifier = Modifier
        )
    }
}
