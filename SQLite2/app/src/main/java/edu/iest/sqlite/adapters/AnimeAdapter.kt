package edu.iest.sqlite.adapters

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import edu.iest.sqlite.ActivityListado
import edu.iest.sqlite.R
import edu.iest.sqlite.db.ManejadorBaseDatos
import edu.iest.sqlite.interfaces.AnimeInterface
import edu.iest.sqlite.models.Anime

class AnimeAdapter(contexto: Context, var listadeAnime: ArrayList<Anime>, animeInterface: AnimeInterface) : BaseAdapter() {
    var contexto: Context? = contexto
    var animeInterface: AnimeInterface? = animeInterface
    override fun getCount(): Int {
        return listadeAnime.size
    }

    override fun getItem(p0: Int): Any {
        return listadeAnime[p0]
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {

        //usar vista reciclado para eficientar
        var convertView: View?= p1
        if(convertView == null){
            convertView = View.inflate(contexto, R.layout.row_anime, null)
        }

        val anime = listadeAnime[p0]

        val miVista = convertView!!
        val tvTitle: TextView = miVista.findViewById(R.id.etTitle)
        val tvContent: TextView = miVista.findViewById(R.id.tvContent)
        val img01: ImageView = miVista.findViewById(R.id.img01)
        val img02: ImageView = miVista.findViewById(R.id.img02)
        tvTitle.text = anime.nombre
        tvContent.text = anime.capitulos.toString()
        //borrar
        img02.setOnClickListener(){
            //eliminar
            val baseDatos = ManejadorBaseDatos(this.contexto!!)
            val argumentosWhere = arrayOf(anime.id.toString())
            baseDatos.eliminar("id = ? ", argumentosWhere)
            animeInterface?.AnimeEliminado()
        }

        img01.setOnClickListener(){
            //Editar
            animeInterface?.editarAnime(anime)
        }

        return miVista
    }
}