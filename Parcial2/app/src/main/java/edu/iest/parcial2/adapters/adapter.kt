package edu.iest.parcial2.adapters

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import edu.iest.parcial2.R
import edu.iest.parcial2.SolicitarPreferencias
import edu.iest.parcial2.Model.Menu

class adapter(menu: ArrayList<Menu>, context: Context):RecyclerView.Adapter<adapter.ContenedorDeVista>(){
    var inner_menu: ArrayList<Menu> = menu
    var inner_context: Context = context

    inner class ContenedorDeVista(view: View):
        RecyclerView.ViewHolder(view), View.OnClickListener {
        val tvTexto : TextView
        val ivImagen : ImageView

        init{
            tvTexto = view.findViewById(R.id.tvTexto)
            ivImagen = view.findViewById(R.id.ivImagen)
            ivImagen.setOnClickListener(this)
        }

        override fun onClick(p0: View?) {
            if(adapterPosition>=0) {
                val menu: Menu = inner_menu.get(adapterPosition)
                if (menu.titulo == "Gatos"){
                }else if (menu.titulo == "Perfil"){
                    val intent = Intent(inner_context, SolicitarPreferencias::class.java)
                    startActivity(inner_context, intent, null)
                }else if (menu.titulo == "Configurar"){
                }else if (menu.titulo == "Cerrar"){
                    (inner_context as Activity).finish()
                }
            }
        }

    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ContenedorDeVista {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.activity_relative, viewGroup, false)
        return ContenedorDeVista(view)
    }

    override fun getItemCount(): Int {
        return inner_menu.size
    }

    override fun onBindViewHolder(holder: ContenedorDeVista, position: Int) {
        val menu: Menu = inner_menu.get(position)

        holder.tvTexto.text = menu.titulo
        holder.ivImagen.setImageResource(menu.imagen)

    }
}