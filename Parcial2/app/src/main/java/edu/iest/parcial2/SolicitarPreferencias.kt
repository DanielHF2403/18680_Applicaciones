package edu.iest.parcial2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.EditText
import com.google.android.material.floatingactionbutton.FloatingActionButton

class SolicitarPreferencias : AppCompatActivity() {

    private lateinit var etNombreDueño: EditText
    private lateinit var etNombreGato: EditText
    private lateinit var etEdad: EditText
    private lateinit var fabPreferencias: FloatingActionButton
    private val NOMBREGATO_KEY = "nombregato"
    private val NOMBREDUEÑO_KEY = "nombredueño"
    private val EDAD_KEY = "edad"
    private var nombregato: String = ""
    private var nombredueño: String = ""
    private var edad: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("PREFERENCIAS", "onCreate")
        setContentView(R.layout.activity_solicitar_preferencias)

        inicializarVistas()


    }

    override fun onSaveInstanceState(outState: Bundle) {
        Log.d("PREFERENCIAS", "onSaveInstanceState")
        outState.putString(NOMBREDUEÑO_KEY, nombredueño )
        outState.putInt(EDAD_KEY, edad )
        outState.putString(NOMBREGATO_KEY, nombregato )
        outState?.run {
            putString(NOMBREDUEÑO_KEY, nombredueño)
            putInt(EDAD_KEY, edad)
            putString(NOMBREGATO_KEY, nombregato)
        }
// call superclass to save any view hierarchy
        super.onSaveInstanceState(outState)

    }

    override fun onResume() {
        Log.d("PREFERENCIAS", "onResume")
        if(TextUtils.isEmpty(nombredueño)){
            val miSharedPreferences = getSharedPreferences("PERSISTENCIA", MODE_PRIVATE)
            nombredueño = miSharedPreferences.getString(NOMBREDUEÑO_KEY, "").toString()
            edad = miSharedPreferences.getInt(EDAD_KEY, 0)
            nombregato = miSharedPreferences.getString(NOMBREGATO_KEY, "").toString()

        }
        super.onResume()
    }

    override fun onStart() {
        Log.d("PREFERENCIAS", "onStart")
        super.onStart()
    }

    override fun onPause() {
        Log.d("PREFERENCIAS", "onPause")
        super.onPause()
    }

    override fun onDestroy() {
        Log.d("PREFERENCIAS", "onDestroy")
        super.onDestroy()
    }


    private fun inicializarVistas(){
        etNombreDueño = findViewById(R.id.etNombreDueño)
        etNombreGato = findViewById(R.id.etNombreGato)
        etEdad = findViewById(R.id.etEdad)
        fabPreferencias = findViewById(R.id.fabPreferencias)

        fabPreferencias.setOnClickListener{
            nombredueño = etNombreDueño.text.toString()
            nombregato = etNombreGato.text.toString()
            edad = etEdad.text.toString().toInt()

            val miSharedPreferences = getSharedPreferences("PERSISTENCIA", MODE_PRIVATE)
            val editor = miSharedPreferences.edit()
            editor.putString(NOMBREDUEÑO_KEY, nombredueño)
            editor.putString(NOMBREGATO_KEY, nombregato)
            editor.putInt(EDAD_KEY, edad)
            editor.apply()
            val s = Intent(this, MostarPreferencias::class.java)
            startActivity(s)
            finish()
        }
    }

}