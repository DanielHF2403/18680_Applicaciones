package com.example.clase6marzo.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.clase6marzo.R
import com.example.clase6marzo.models.Videojuego

class VideojuegoAdapter(videojuego: ArrayList<Videojuego>, contexto: Context)
    :RecyclerView.Adapter<VideojuegoAdapter.ContenedorDeVista>() {
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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContenedorDeVista {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.activity_segunda_vista, parent,false)
        return ContenedorDeVista(view)
    }

    override fun getItemCount(): Int {
        return inner_videojuegos.size
    }

    override fun onBindViewHolder(holder: ContenedorDeVista, position: Int) {
        val videojuego: Videojuego = inner_videojuegos.get(position)
        holder.tvNombre.text = videojuego.nombre
        holder.tvConsola.text = videojuego.consola
        holder.tvPrecio.text = videojuego.precio.toString()
        holder.ivFoto.setImageResource(videojuego.imagen)
    }
}