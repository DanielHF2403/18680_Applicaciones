package edu.iest.parcial2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var ivcerrar = findViewById<ImageView>(R.id.ivCerrar)
        var tvcerrar = findViewById<TextView>(R.id.tvCerrar)
        var tvperfil = findViewById<TextView>(R.id.tvProfile)
        var ivperfil = findViewById<ImageView>(R.id.ivProfile)

        ivcerrar.setOnClickListener {
            finish()
        }

        tvcerrar.setOnClickListener {
            finish()
        }

        ivperfil.setOnClickListener {
            val s = Intent(this, SolicitarPreferencias::class.java)
            startActivity(s)
            finish()
        }

        tvperfil.setOnClickListener {
            val s = Intent(this, SolicitarPreferencias::class.java)
            startActivity(s)
            finish()
        }
    }
}