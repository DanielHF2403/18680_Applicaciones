package edu.iest.califbotones

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private lateinit var etCalif: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        etCalif = findViewById(R.id.etCalif)
        var bnEnviar = findViewById<Button>(R.id.bnEnviar)
        bnEnviar.setOnClickListener {
            if (obtenerCalif() <= "6.99"){
                val s = Intent(this, Tercera_vista :: class.java).putExtra("Score", obtenerCalif())
                startActivity(s)
                finish()
            } else {
                val s1 = Intent(this, Segunda_Vista :: class.java).putExtra("Score", obtenerCalif())
                startActivity(s1)
                finish()
            }
        }
    }

    private fun obtenerCalif() : String{
        return etCalif.text.toString()
    }

}