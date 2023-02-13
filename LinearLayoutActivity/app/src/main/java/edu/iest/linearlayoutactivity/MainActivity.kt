package edu.iest.linearlayoutactivity

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private var bnSuma : Button? = null
    private var bnResta : Button? = null
    private var etNumeroPrimero: EditText? = null
    private var etNumeroSegundo: EditText? = null


    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        inicializarVistas()
        asignarEventos()
    }

    private fun inicializarVistas(){
        bnSuma = findViewById(R.id.bnSuma)
        bnResta = findViewById(R.id.bnResta)
        etNumeroPrimero = findViewById(R.id.etNumeroPrimero)
        etNumeroSegundo = findViewById(R.id.etNumeroSegundo)
    }

    private fun asignarEventos(){
        bnSuma?.setOnClickListener(this)
        bnResta?.setOnClickListener(this)
    }


    override fun onClick(miVista: View?) {
        val num1 = etNumeroPrimero?.text.toString().toFloatOrNull()
        val num2 = etNumeroSegundo?.text.toString().toFloatOrNull()
        if (num1 == null){
            return
        }
        if (num2 == null){
            return
        }

        val aritmetica = Aritmetica()

        //Aqui entrara los click de las vistas
        when(miVista?.id){
            R.id.bnSuma -> {
                val suma = aritmetica.suma(num1,num2)
                val mensaje = Mensajes("La sumatoria es $suma",this)
                mensaje.mostarToast()


                //if (num1 != null && num2 != null){
                    //val suma = num1 + num2
                    //Toast.makeText(this,"La sumatoria es $suma", Toast.LENGTH_LONG).show()
                //}else{
                    //Toast.makeText(this,"No agregaste nada", Toast.LENGTH_LONG).show()
                //}
            }

            R.id.bnResta -> {
                val resta = aritmetica.resta(num1,num2)
                val mensaje = Mensajes("El resultado es $resta",this)
                mensaje.mostrarSnackbar(miVista)


                //if (num1 != null && num2 != null){
                    //val resta = num1 - num2
                    //Snackbar.make(miVista, "El resultado es $resta", Snackbar.LENGTH_LONG).show()
                //}else{
                    //Snackbar.make(miVista,"No agregaste nada", Snackbar.LENGTH_LONG).show()
                //}
            }
        }
    }
}