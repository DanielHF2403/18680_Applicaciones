package edu.iest.botones

import android.annotation.SuppressLint
import android.content.Intent
import android.content.IntentSender.SendIntentException
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")

    private lateinit var tvFrase : TextView
    private lateinit var etFrase: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tvFrase = findViewById(R.id.textViewFrase)
        etFrase = findViewById(R.id.editTextFrase)
        var bnMostrar = findViewById<Button>(R.id.buttonMostrar)
        bnMostrar.setOnClickListener{
            var frase = obtenerFrase()
            Snackbar.make(tvFrase, "Su frase fue $frase", Snackbar.LENGTH_LONG).show()
        }

        var bnSiguiente = findViewById<Button>(R.id.bnSiguiente)
        bnSiguiente.setOnClickListener {
            val s = Intent(this, SegundaActivity::class.java)
            s.putExtra("frase", obtenerFrase())
            s.putExtra("edad", 21)
            startActivity(s)
            finish()
        }



    }

    private fun obtenerFrase() : String{
        return etFrase.text.toString()
    }

}