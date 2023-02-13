package edu.iest.botones

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView

class SegundaActivity : AppCompatActivity() {
    private var cambioIcon : Boolean = false
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_segunda)

        var fraseRecibida = intent.getStringExtra("frase")
        var edad = intent.getIntExtra("edad", 0)
        Log.d("Datos", "Datos recibidos con exito fueron $fraseRecibida y edad $edad")

        var bnAbrirPantalla = findViewById<Button>(R.id.bnPantalla)
        var img = findViewById<ImageView>(R.id.ivIest)
        //var img_2 = false
        img.setOnClickListener {
            var arrayIcon = arrayOf<Int>(R.drawable.iest, R.drawable.a)
            if (cambioIcon) {
                img.setImageResource(arrayIcon[0])
            } else {
                img.setImageResource(arrayIcon[1])
            }
            cambioIcon = !cambioIcon

            /*if (img_2 == false) {
                img.setImageResource(R.drawable.a)
                img_2 = true
            } else {
                img.setImageResource(R.drawable.iest)
                img_2 = false
            }*/

        }
        bnAbrirPantalla.setOnClickListener {
            val i = Intent(this,MainActivity::class.java)
            startActivity(i)
            finish()
        }

        var bnCerrar = findViewById<Button>(R.id.bnCerrar)
        bnCerrar.setOnClickListener {
            finish()
        }
    }
}