package edu.iest.califbotones

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class Tercera_vista : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tercera_vista)

        var recibeCalif = intent.getStringExtra("Score")
        val tvReprobado = findViewById<TextView>(R.id.tvReprobado)
        tvReprobado.text = "Obtuviste un $recibeCalif  :("

        var bnRegR = findViewById<Button>(R.id.bnRegR)
        bnRegR.setOnClickListener {
            val s = Intent(this, MainActivity::class.java)
            startActivity(s)
            finish()
        }
    }
}