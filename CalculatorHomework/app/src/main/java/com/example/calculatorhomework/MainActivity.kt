package com.example.calculatorhomework

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var editText: EditText
    private lateinit var btnPlus: Button
    private lateinit var btnMinus: Button
    private lateinit var btnDivide: Button
    private lateinit var btnMultiply: Button
    private lateinit var btnEquals: Button
    private var ilkSayi: Double = 0.0
    private var secilenOperator: String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        editText=findViewById(R.id.editText)
        btnPlus=findViewById(R.id.btnPLus)
        btnMinus=findViewById(R.id.btnMinus)
        btnDivide=findViewById(R.id.btnDivider)
        btnMultiply=findViewById(R.id.btnMultiplater)
        btnEquals=findViewById(R.id.btnEquals)

        val buttonList= listOf(R.id.btn0,R.id.btn1,R.id.btn2,R.id.btn3,R.id.btn4,R.id.btn5,R.id.btn6,R.id.btn7,R.id.btn8,R.id.btn9)
        for(id in buttonList){
            val button=findViewById<Button>(id)
            button.setOnClickListener{
                editText.append(button.text)
            }
        }
        btnEquals.setOnClickListener(this)
        btnPlus.setOnClickListener(this)
        btnMultiply.setOnClickListener(this)
        btnDivide.setOnClickListener(this)
        btnMinus.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        val ekrandakiSayiText=editText.text.toString()
        when(v.id){
            R.id.btnPLus, R.id.btnMinus, R.id.btnDivider, R.id.btnMultiplater ->{
                ilkSayi=ekrandakiSayiText.toDouble()
                secilenOperator=(v as Button).text.toString()
                editText.text.clear()
            }
            R.id.btnEquals ->{
                if(ekrandakiSayiText.isNotEmpty()){
                    val ikinciSayi=ekrandakiSayiText.toDouble()
                    var sonuc=0.0
                    when(secilenOperator){
                        "+" -> sonuc = ilkSayi + ikinciSayi
                        "-" -> sonuc = ilkSayi - ikinciSayi
                        "*" -> sonuc = ilkSayi * ikinciSayi
                        "/" -> if (ikinciSayi != 0.0) sonuc = ilkSayi / ikinciSayi
                    }
                    editText.setText(sonuc.toString())
                    secilenOperator=""
                }
            }
        }
    }
}