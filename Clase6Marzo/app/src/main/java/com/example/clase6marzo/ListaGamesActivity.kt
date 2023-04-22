package com.example.clase6marzo

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.clase6marzo.adapters.VideojuegoAdapter
import com.example.clase6marzo.models.FakerVideojuegos
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ListaGamesActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_games)
        val juegos = FakerVideojuegos().getVideogames()
        val recycler = findViewById<RecyclerView>(R.id.RecyclerJuegos)

        val Cantidad_Columnas = 2
        var administradorDeLayouts = LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false)
        recycler.layoutManager = administradorDeLayouts
        recycler.adapter = VideojuegoAdapter(juegos,this)

        val fabPreferencias = findViewById<FloatingActionButton>(R.id.fabPreferencias)

        fabPreferencias.setOnClickListener {
            val s = Intent(this, MostrarPreferencias::class.java)
            startActivity(s)
            finish()
        }

    }
}