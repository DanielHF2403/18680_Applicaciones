package edu.iest.sqlite

import android.annotation.SuppressLint
import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.database.Cursor
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.Menu
import android.widget.ListView
import android.widget.SearchView
import android.widget.Toast
import com.google.android.material.floatingactionbutton.FloatingActionButton
import edu.iest.sqlite.adapters.AnimeAdapter
import edu.iest.sqlite.db.ManejadorBaseDatos
import edu.iest.sqlite.models.Anime
import edu.iest.sqlite.interfaces.AnimeInterface

class ActivityListado : AppCompatActivity() {

    private lateinit var listView: ListView
    private var listaDeAnimes = ArrayList<Anime>()
    private lateinit var fab: FloatingActionButton
    private val ORDENAR_POR_NOMBRE : String  = "nombre"
    val columnas = arrayOf("id", "nombre","caps", "temporada" )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_listado)

        inicializarVistas()
        asignarEventos()
    }
    override fun onResume() {
        super.onResume()
        traerMisAnimes()
    }
    private fun inicializarVistas(){
        listView = findViewById(R.id.listView)
        fab = findViewById(R.id.fab)
    }

    private fun asignarEventos(){
        fab.setOnClickListener{
            val intent = Intent(this, Activity_Agregar::class.java)
            startActivity(intent)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_listado, menu)
        val searchView = menu!!.findItem(R.id.app_bar_search).actionView as SearchView
        val manejador = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        searchView.setSearchableInfo(manejador.getSearchableInfo(componentName))
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                buscarJuego("%" + p0 + "%")
                Toast.makeText(applicationContext, p0, Toast.LENGTH_SHORT).show()
                return false
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                if(TextUtils.isEmpty(p0)){
                    this.onQueryTextSubmit("");
                }
                return false
            }


        })
        return super.onCreateOptionsMenu(menu)
    }


    private fun traerMisAnimes() {
        val baseDatos = ManejadorBaseDatos(this)
        val cursor = baseDatos.traerTodos(columnas, ORDENAR_POR_NOMBRE)
        recorrerResultados( cursor)
        baseDatos.cerrarConexion()
    }

    @SuppressLint("Range")
    private fun buscarJuego(nombre: String) {
        val baseDatos = ManejadorBaseDatos(this)
        val camposATraer = arrayOf(nombre)
        val cursor = baseDatos.seleccionar(columnas,"nombre like ?", camposATraer, ORDENAR_POR_NOMBRE)
        recorrerResultados( cursor)
        baseDatos.cerrarConexion()
    }

    @SuppressLint("Range")
    fun recorrerResultados(cursor : Cursor){
        if(listaDeAnimes.size > 0)
            listaDeAnimes.clear()

        if(cursor.moveToFirst()){
            do{
                val anime_id = cursor.getInt(cursor.getColumnIndex("id"))
                val nombre = cursor.getString(cursor.getColumnIndex("nombre"))
                val caps = cursor.getInt(cursor.getColumnIndex("caps"))
                val temporada = cursor.getString(cursor.getColumnIndex("temporada"))
                val anime: Anime
                anime = Anime(anime_id, nombre, caps, temporada)
                listaDeAnimes.add(anime)
            }while(cursor.moveToNext())
        }
        val adapter = AnimeAdapter(this, listaDeAnimes,this)
        listView.adapter = adapter

    }

    override fun AnimeEliminado() {
        Log.d("PRUEBAS", "AnimeEliminado")
        traerMisAnimes()
    }

    override fun editarAnime(anime: Anime) {
        Log.d("PRUEBAS", "editar Anime "+anime.id)
        val intent = Intent(this, ActivityEditar::class.java)
        intent.putExtra("id",anime.id)
        intent.putExtra("nombre",anime.nombre)
        intent.putExtra("temporada",anime.temporada)
        startActivity(intent)
    }
}
