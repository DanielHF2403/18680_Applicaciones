package edu.iest.linearlayoutactivity

import android.content.Context
import android.view.View
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar

class Mensajes(var mensaje: String, var contexto: Context) {

    fun mostarToast(){
        Toast.makeText(contexto,mensaje, Toast.LENGTH_LONG).show()
    }

    fun mostrarSnackbar(miVista:View){
        Snackbar.make(miVista, mensaje, Snackbar.LENGTH_LONG).show()
    }

}