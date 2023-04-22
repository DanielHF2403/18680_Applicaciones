package com.example.clase6marzo.adapters

import android.app.Activity
import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.clase6marzo.ListaGamesActivity
import com.example.clase6marzo.R
import com.example.clase6marzo.models.Videojuego

class VideojuegoAdapter(videojuego: ArrayList<Videojuego>, contexto: Context)
    :RecyclerView.Adapter<VideojuegoAdapter.ContenedorDeVista>() {
    var inner_videojuegos: ArrayList<Videojuego> = videojuego
    var inner_context: Context = contexto

    inner class ContenedorDeVista(view: View): RecyclerView.ViewHolder(view), View.OnClickListener {
        //Aqui haremos el inflate del layout
        val tvNombre : TextView
        val tvPrecio : TextView
        val tvConsola : TextView
        val tvClasificacion : TextView
        val ivFoto : ImageView
        val bnCompra : Button
        init { // Define click listener for the ViewHolder's View.
            tvNombre = view.findViewById(R.id.tvPoke)
            tvPrecio = view.findViewById(R.id.tvPrecio)
            tvConsola = view.findViewById(R.id.tvConsola)
            tvClasificacion = view.findViewById(R.id.tvClasificacion)
            ivFoto = view.findViewById(R.id.ivPoke)
            bnCompra = view.findViewById(R.id.bnComprar)

            bnCompra.setOnClickListener(this)

        }

        override fun onClick(p0: View?) {
            if (adapterPosition >= 0){

                val sharedPref = inner_context.getSharedPreferences("PERSISTENCIA", MODE_PRIVATE)

                val edad = sharedPref.getInt("edad", 0)

                val juegos: Videojuego = inner_videojuegos.get(adapterPosition)
                if ((juegos.rate == "Mature" && edad <= 16 )|| (juegos.rate == "Teen" && edad <=11)){
                    Toast.makeText(inner_context, "No puedes comprar el juego ${juegos.nombre}.", Toast.LENGTH_LONG).show()
                } else {
                    Toast.makeText(inner_context, "Gracias por comprar el juego ${juegos.nombre}", Toast.LENGTH_LONG).show()
                }
            }
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
        holder.tvClasificacion.text = videojuego.rate
        holder.tvPrecio.text = videojuego.precio.toString()
        holder.ivFoto.setImageResource(videojuego.imagen)

    }
}