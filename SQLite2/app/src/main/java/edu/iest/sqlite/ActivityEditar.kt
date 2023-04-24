package edu.iest.sqlite

import android.annotation.SuppressLint
import android.content.ContentValues
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.MenuItem
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import edu.iest.sqlite.db.ManejadorBaseDatos
import edu.iest.sqlite.models.Anime

class ActivityEditar : AppCompatActivity(), AdapterView.OnItemSelectedListener {
    private  lateinit var bnGuardar: Button
    private  lateinit var etAnime: EditText
    private  lateinit var etCaps: EditText
    private  lateinit var spTemporada: Spinner
    private val temporadas = arrayOf("Primavera", "Verano", "Otoño", "Invierno")
    private var TemporadaSeleccionada: String = ""
    private  lateinit var tvAnime: TextView
    var anime: Anime? = null
    var id: Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editar)

        getSupportActionBar()?.title = "Edición"
        getSupportActionBar()?.setHomeButtonEnabled(true);
        getSupportActionBar()?.setDisplayHomeAsUpEnabled(true);
        inicializarVistas()
        id = intent.getIntExtra("id", 0)
        buscar(id)
        poblarCampos()
    }

    private fun poblarCampos() {
        etAnime.setText(anime?.nombre)
        etCaps.setText(anime?.capitulos.toString())
        val position = temporadas.indexOf(anime?.temporada)
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, temporadas)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spTemporada.adapter = adapter
        spTemporada.onItemSelectedListener = this
        if (position >= 0) {
            spTemporada.setSelection(position)
            TemporadaSeleccionada = temporadas[position]
        }
    }

    private fun inicializarVistas() {
        etAnime = findViewById(R.id.etAnime)
        bnGuardar = findViewById(R.id.bnGuardar)
        etCaps = findViewById(R.id.etCaps)
        spTemporada = findViewById(R.id.spTemporada)
        tvAnime = findViewById(R.id.tvAnime)
        bnGuardar.setOnClickListener {
            var caps_actual: Int
            caps_actual = etCaps.text.toString().toInt()
            actualizarJuego(etAnime.text.toString(), caps_actual, TemporadaSeleccionada)
        }
    }

    val columnaNombreAnime = "nombre"
    val columnaCapitulos = "caps"
    val columnaTemporada = "temporada"

    private fun actualizarJuego(nombreAnime: String, caps: Int, temporada: String) {
        if (!TextUtils.isEmpty(temporada)) {
            val baseDatos = ManejadorBaseDatos(this)
            val contenido = ContentValues()
            contenido.put(columnaNombreAnime, nombreAnime)
            contenido.put(columnaCapitulos, caps)
            contenido.put(columnaTemporada, temporada)
            if ( id > 0) {
                val argumentosWhere = arrayOf(id.toString())
                val id_actualizado = baseDatos.actualizar(contenido, "id = ?", argumentosWhere)
                if (id_actualizado > 0) {
                    Snackbar.make(etAnime, "Juego actualizado", Snackbar.LENGTH_LONG).show()
                } else {
                    val alerta = AlertDialog.Builder(this)
                    alerta.setTitle("Atención")
                        .setMessage("No fue posible actualizarlo")
                        .setCancelable(false)
                        .setPositiveButton("Aceptar") { dialog, which ->

                        }
                        .show()
                }
            } else {
                Toast.makeText(this, "no hiciste id", Toast.LENGTH_LONG).show()
            }
            baseDatos.cerrarConexion()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }

    @SuppressLint("Range")
    private fun buscar(idAnime: Int) {

        if (idAnime > 0) {
            val baseDatos = ManejadorBaseDatos(this)
            val columnasATraer = arrayOf("id", "nombre", "caps", "temporada")
            val condicion = " id = ?"
            val argumentos = arrayOf(idAnime.toString())
            val ordenarPor = "id"
            val cursor = baseDatos.seleccionar(columnasATraer, condicion, argumentos, ordenarPor)

            if (cursor.moveToFirst()) {
                do {
                    val anime_id = cursor.getInt(cursor.getColumnIndex("id"))
                    val nombre = cursor.getString(cursor.getColumnIndex("nombre"))
                    val capitulos = cursor.getInt(cursor.getColumnIndex("caps"))
                    val temporada = cursor.getString(cursor.getColumnIndex("temporada"))
                    anime = Anime(anime_id, nombre, capitulos, temporada)
                } while (cursor.moveToNext())
            }
            baseDatos.cerrarConexion()
        }
    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, position: Int, p3: Long) {
        TemporadaSeleccionada = temporadas[position]
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {

    }
}