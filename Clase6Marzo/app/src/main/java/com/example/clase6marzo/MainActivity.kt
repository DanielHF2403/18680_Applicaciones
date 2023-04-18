package com.example.clase6marzo

import android.content.Context
import android.content.SharedPreferences
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.text.TextUtils
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Switch
import android.widget.TextView
import androidx.appcompat.app.ActionBar

class MainActivity : AppCompatActivity() {
    private lateinit var tvBienvenido: TextView
    private lateinit var etNombre: EditText
    private lateinit var etnEdad: EditText
    private lateinit var etnAltura: EditText
    private lateinit var etnMonedero: EditText
    private lateinit var bnGuardar: Button
    private lateinit var switchPreferencias: Switch
    private val NOMBRE_KEY = "nombre"
    private val EDAD_KEY = "edad"
    private val ALTURA_KEY = "altura"
    private val MONEDERO_KEY = "monedero"
    private val SWITCH_KEY = "switch_estado"
    private val NOMBRE_INSTANCIA = "nombre_instancia"
    private var nombre: String = ""
    private var edad: Int = 0
    private var altura: Float = 0.0f
    private var monedero: Float = 0.0f

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("PREFERENCIAS", "onCreate")
        setContentView(R.layout.activity_main)

        var actionBar: ActionBar?
        actionBar = supportActionBar
        var colorDrawable: ColorDrawable
        colorDrawable = ColorDrawable(Color.parseColor("#FF018786"))
        actionBar!!.setBackgroundDrawable(colorDrawable)

        inicializarVistas()

        Log.d("PREFERENCIAS", savedInstanceState?.getString(NOMBRE_KEY).toString())
//nombre = savedInstanceState?.getString(NOMBRE_KEY).toString()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        Log.d("PREFERENCIAS", "onSaveInstanceState")
        outState.putString(NOMBRE_KEY, nombre )
        outState.putInt(EDAD_KEY, edad )
        outState.putFloat(ALTURA_KEY, altura )
        outState.putFloat(MONEDERO_KEY, monedero )
        outState?.run {
            putString(NOMBRE_KEY, nombre)
            putInt(EDAD_KEY, edad)
            putFloat(ALTURA_KEY, altura)
            putFloat(MONEDERO_KEY, monedero)
        }
// call superclass to save any view hierarchy
        super.onSaveInstanceState(outState)

    }

    override fun onResume() {
        Log.d("PREFERENCIAS", "onResume")
        if(TextUtils.isEmpty(nombre)){
            val miSharedPreferences = getSharedPreferences("PERSISTENCIA", MODE_PRIVATE)
            nombre = miSharedPreferences.getString(NOMBRE_KEY, "").toString()
            edad = miSharedPreferences.getInt(EDAD_KEY, 0)
            altura = miSharedPreferences.getFloat(NOMBRE_KEY, 0.0f)
            monedero = miSharedPreferences.getFloat(NOMBRE_KEY, 0.0f)

        }

        tvBienvenido.text = nombre
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

    private fun cambiarTextoBienvenida(nombre: String) {
        if (!TextUtils.isEmpty(nombre)) {
            tvBienvenido.text = "Bienvenido " + nombre
        }
    }

    private fun inicializarVistas() {
        tvBienvenido = findViewById(R.id.tvSaludo)
        etNombre = findViewById(R.id.etNombre)
        bnGuardar = findViewById(R.id.bnGuardar)
        switchPreferencias = findViewById(R.id.swPref)
        etnEdad = findViewById(R.id.etnEdad)
        etnAltura = findViewById(R.id.etnAltura)
        etnMonedero = findViewById(R.id.etnMonedero)

        bnGuardar.setOnClickListener {
            nombre = etNombre.text.toString()
            edad = etnEdad.text.toString().toInt()
            altura = etnAltura.text.toString().toFloat()
            monedero = etnMonedero.text.toString().toFloat()
            cambiarTextoBienvenida(nombre)
            val miSharedPreferences = getSharedPreferences("PERSISTENCIA", MODE_PRIVATE)
            val editor = miSharedPreferences.edit()
            editor.putString(NOMBRE_KEY, nombre)
            editor.putInt(EDAD_KEY, edad)
            editor.putFloat(ALTURA_KEY, altura)
            editor.putFloat(MONEDERO_KEY, monedero)
            editor.apply()
        }

    }
}