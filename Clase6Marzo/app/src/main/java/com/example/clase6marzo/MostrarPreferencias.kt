package com.example.clase6marzo

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class MostrarPreferencias : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mostrar_preferencias)
        var tvnombre = findViewById<TextView>(R.id.tvNombre)
        var tvedad = findViewById<TextView>(R.id.tvEdad)
        var tvaltura = findViewById<TextView>(R.id.tvAltura)
        var tvmonedero = findViewById<TextView>(R.id.tvMonedero)
        var bnback = findViewById<Button>(R.id.bnBack)

        val sharedPreference = getSharedPreferences("PERSISTENCIA", MODE_PRIVATE) ?: return
        val nombre = sharedPreference.getString("nombre", "")
        val edad = sharedPreference.getInt("edad", 0).toString()
        val altura = sharedPreference.getFloat("altura", 0.0f).toString()
        val monedero = sharedPreference.getFloat("monedero", 0.0f).toString()
        tvnombre.text = nombre
        tvedad.text = edad
        tvaltura.text = altura
        tvmonedero.text = monedero

        bnback.setOnClickListener{
            val s = Intent(this, ListaGamesActivity::class.java)
            startActivity(s)
            finish()
        }


    }
}