package edu.iest.parcial2

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class MostarPreferencias : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mostar_preferencias)
        var tvnombredueño = findViewById<TextView>(R.id.tvNombreDueño)
        var tvnombregato = findViewById<TextView>(R.id.tvNombreGato)
        var tvedadgato = findViewById<TextView>(R.id.tvEdadGato)
        var bnback = findViewById<Button>(R.id.bnback)

        val sharedPreferences = getSharedPreferences("PERSISTENCIA", MODE_PRIVATE)?: return
        val nombredueño = sharedPreferences.getString("nombredueño", "")
        val nombregato = sharedPreferences.getString("nombregato", "")
        val edad = sharedPreferences.getInt("edad", 0).toString()

        tvnombredueño.text = nombredueño
        tvnombregato.text = nombregato
        tvedadgato.text = edad

        bnback.setOnClickListener {
            val s = Intent(this, SolicitarPreferencias::class.java)
            startActivity(s)
            finish()
        }

    }
}