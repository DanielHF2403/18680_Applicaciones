package edu.iest.califbotones

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class Segunda_Vista : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_segunda_vista)

        var recibeCalif = intent.getStringExtra("Score")
        val tvAprobado = findViewById<TextView>(R.id.tvAprobado)
        tvAprobado.text = "Felicidades, aprobaste con $recibeCalif"

        var bnRegA = findViewById<Button>(R.id.bnRegA)
        bnRegA.setOnClickListener{
            val s = Intent(this, MainActivity :: class.java)
            startActivity(s)
            finish()
        }
    }
}