package com.example.retrofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import retrofit2.Call
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofit.adapters.rvAdapter
import com.example.retrofit.models.ImageRandom
import com.example.retrofit.models.ListBreed
import com.example.retrofit.network.API
import retrofit2.Response
import retrofit2.Callback


class list : AppCompatActivity() {

    private var txtTitleRaza: TextView? = null
    private var myRecycler: RecyclerView? = null
    val apiCall = API().crearServicioAPI()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)
        var raza = intent.getStringExtra("raza")
        Log.d("DATOS:", raza.toString())

        txtTitleRaza = findViewById(R.id.txtTitleRaza)
        txtTitleRaza?.setText(raza)

        Toast.makeText(this, "Buscando $raza", Toast.LENGTH_LONG).show()

        myRecycler = findViewById(R.id.recyclerviewPerros)
        myRecycler?.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        GetDogs(raza.toString())
    }

    private fun GetDogs(raza: String){

        apiCall.listaImagenesDePerrosPorRaza("$raza").enqueue(/* callback = */ object : Callback<ListBreed> {

            // @Override obtiene la respuesta/resultado
            override fun onResponse(call: Call<ListBreed>, response: Response<ListBreed>) {
                //TODO("Not yet implemented")
                val result = ArrayList<ImageRandom>()
                val dogs = response.body()?.message // Array
                val status = response.body()?.status
                Log.d("PRUEBAS", "Status de la respuesta ${response.body()?.status}")
                if (dogs != null) {
                    for (dog in dogs) {
                        Log.d("PRUEBAS", "Tu perro es ${dog}")

                        // Agregando perro a lista (Modelo = <ImageRandom>)
                        result.add(ImageRandom("$status", "${dog}"))
                    }

                    // Seteando el RecyclerView con el resultado (Lista de perros)
                    val adapter = rvAdapter(result)
                    myRecycler?.adapter = adapter
                }

            }

            // @Override por si algo sale mal al conectar con la API
            override fun onFailure(call: Call<ListBreed>, t: Throwable) {
                //TODO("Not yet implemented")
                Toast.makeText(
                    this@list,
                    "No fue posible conectar a API",
                    Toast.LENGTH_SHORT
                ).show()
            }

        })
    }
}