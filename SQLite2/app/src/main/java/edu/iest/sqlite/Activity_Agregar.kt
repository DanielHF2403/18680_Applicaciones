package edu.iest.sqlite

import android.content.ContentValues
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.*
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import edu.iest.sqlite.db.ManejadorBaseDatos

class Activity_Agregar : AppCompatActivity(), AdapterView.OnItemSelectedListener {

    private  lateinit var fabAgregar: FloatingActionButton
    private  lateinit var etAnime: EditText
    private  lateinit var etCaps: EditText
    private  lateinit var spTemporada: Spinner
    private val temporadas = arrayOf("Primavera", "Verano", "Otoño", "Invierno")
    private var TemporadaSeleccionada: String = ""
    private  lateinit var tvAnime: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_agregar)

        inicializarVistas()

        val adapter = ArrayAdapter(this,android.R.layout.simple_spinner_item, temporadas)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        spTemporada.adapter = adapter
        spTemporada.onItemSelectedListener = this
        fabAgregar.setOnClickListener{
            insertarJuego( etAnime.text.toString(),  etCaps.text.toString().toInt(),TemporadaSeleccionada)
        }
    }

    val columnaID = "id"
    val columnaNombreAnime = "nombre"
    val columnaCapitulos = "caps"
    val columnaTemporada = "temporada"
    var id: Int = 0
    private fun insertarJuego(nombreAnime: String, caps: Int, temporada: String){
        if(!TextUtils.isEmpty(temporada)) {
            val baseDatos = ManejadorBaseDatos(this)
            //  val columnas = arrayOf(columnaID, columnaNombreJuego, columnaPrecio, columnaConsola)
            val contenido = ContentValues()
            contenido.put(columnaNombreAnime, nombreAnime)
            contenido.put(columnaCapitulos, caps)
            contenido.put(columnaTemporada, temporada)
            //guardar imagen
            id = baseDatos.insertar(contenido).toInt()
            if (id > 0) {
                Toast.makeText(this, "Anime " + nombreAnime + " agregado", Toast.LENGTH_LONG).show()
                finish()
            } else
                Toast.makeText(this, "No se pudo agregar el anime :(", Toast.LENGTH_LONG).show()
            baseDatos.cerrarConexion()
        }else{
            Snackbar.make(tvAnime,"Favor seleccionar una temporada", 0).show()
        }
    }

    private fun inicializarVistas(){
        etAnime = findViewById(R.id.etAnime)
        fabAgregar = findViewById(R.id.fabAgregar)
        etCaps = findViewById(R.id.etCaps)
        spTemporada = findViewById(R.id.spTemporada)
        tvAnime = findViewById(R.id.tvAnime)
    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, position: Int, id: Long) {
        TemporadaSeleccionada = temporadas[position]
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {

    }
}