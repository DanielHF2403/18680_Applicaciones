package com.example.examenparcial

import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    private var cambioIcon : Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var bnComprar = findViewById<Button>(R.id.bnCompra)
        val sphorarios = findViewById<Spinner>(R.id.sphorarios)
        var img = findViewById<ImageView>(R.id.ivAntMan)
        //var img_2 = false
        img.setOnClickListener {
            var arrayIcon = arrayOf<Int>(R.drawable.antman, R.drawable.antman2)
            if (cambioIcon) {
                img.setImageResource(arrayIcon[0])
            } else {
                img.setImageResource(arrayIcon[1])
            }
            cambioIcon = !cambioIcon
        }

        var tvCapital = findViewById<TextView>(R.id.tvCapital)
        tvCapital.setOnClickListener {
            finish()
        }


        bnComprar.setOnClickListener{

        }

    }
}