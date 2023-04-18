package com.example.clase6marzo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class MostrarPreferencias : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mostrar_preferencias)
        var tvnombre = findViewById<TextView>(R.id.tvNombre)
        var tvedad = findViewById<TextView>(R.id.tvEdad)
        var tvaltura = findViewById<TextView>(R.id.tvAltura)
        var tvmonedero = findViewById<TextView>(R.id.tvMonedero)
        val miSharedPreferences = getSharedPreferences("PERSISTENCIA", MODE_PRIVATE)


    }
}