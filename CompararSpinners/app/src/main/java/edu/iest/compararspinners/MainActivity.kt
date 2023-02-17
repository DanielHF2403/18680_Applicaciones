package edu.iest.compararspinners

import android.app.Activity
import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import edu.iest.compararspinners.databinding.ActivityMainBinding

class MainActivity : Activity(), AdapterView.OnItemSelectedListener {

    private lateinit var binding: ActivityMainBinding
    private var num1: String? = null
    private var num2: String? = null
    private var numMayor: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.bnComparar.text = "Comparar"

        val adaptador1 = ArrayAdapter.createFromResource(this, R.array.sp1, android.R.layout.simple_spinner_item)
        adaptador1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        binding.sp1.adapter = adaptador1
        binding.sp1.onItemSelectedListener = this

        val adaptador2 = ArrayAdapter.createFromResource(this, R.array.sp2, android.R.layout.simple_spinner_item)
        adaptador2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        binding.sp2.adapter = adaptador2
        binding.sp2.onItemSelectedListener = this


        binding.bnComparar.setOnClickListener {
            val a = AlertDialog.Builder(this)
            if(Integer.valueOf(num1!!) >= Integer.valueOf(num2!!)){
                numMayor = num1
            } else {
                numMayor = num2
            }
            a.setTitle("Calcular").setMessage("El numero mayor es: $numMayor").setCancelable(false).setPositiveButton("ok", DialogInterface.OnClickListener(){
                DialogInterface, i -> Toast.makeText(this, "Gracias por participar",Toast.LENGTH_LONG).show()
            }).show()
        }



    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, pos: Int, p3: Long) {
        if (p0!!.id == R.id.sp1) {
            num1 = p0.getItemAtPosition(pos).toString()
        } else if (p0!!.id == R.id.sp2) {
            num2 = p0.getItemAtPosition(pos).toString()
        }
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
        TODO("Not yet implemented")
    }
}