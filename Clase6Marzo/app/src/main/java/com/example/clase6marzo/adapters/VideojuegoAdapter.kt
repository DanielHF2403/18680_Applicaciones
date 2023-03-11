package com.example.clase6marzo.adapters

import android.content.Context
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.clase6marzo.R
import com.example.clase6marzo.models.Videojuego

class VideojuegoAdapter(videojuego: ArrayList<Videojuego>, contexto: Context){
    var inner_videojuegos: ArrayList<Videojuego> = videojuego
    var inner_context: Context = contexto

    inner class ContenedorDeVista(view: View): RecyclerView.ViewHolder(view){
        //Aqui haremos el inflate del layout
        val tvNombre : TextView
        val tvPrecio : TextView
        val tvConsola : TextView
        val ivFoto : ImageView
        val bnCompra : Button
        init { // Define click listener for the ViewHolder's View.
            tvNombre = view.findViewById(R.id.tvPoke)
            tvPrecio = view.findViewById(R.id.tvPrecio)
            tvConsola = view.findViewById(R.id.tvConsola)
            ivFoto = view.findViewById(R.id.ivPoke)
            bnCompra = view.findViewById(R.id.bnComprar)

        }
    }
}