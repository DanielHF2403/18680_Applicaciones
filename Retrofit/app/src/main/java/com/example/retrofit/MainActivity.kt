package com.example.retrofit

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.*
import com.example.retrofit.models.ImageRandom
import com.example.retrofit.models.ListBreed
import com.example.retrofit.network.API
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var txtURL: TextView
    private lateinit var imgRandom: ImageView
    private var spBreeds: Spinner? = null
    private var btnBuscar: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        inicializarVistas()
        agregarEventos()
        traerImagenAleatoria()
    }

    private fun inicializarVistas(){
        txtURL = findViewById(R.id.txtURL)
        imgRandom = findViewById(R.id.imgRandom)
        btnBuscar = findViewById(R.id.btnBuscar)
        spBreeds = findViewById(R.id.spBreeds)
    }

    private fun agregarEventos(){
        btnBuscar?.setOnClickListener(this)
    }
    override fun onClick(p0: View?) {
        //TODO("Not yet implemented")
        val raza = spBreeds?.selectedItem.toString()

        when(p0?.id){
            R.id.btnBuscar -> {
                Log.d("RAZA", "raza es $raza")
                var i = Intent(this, list::class.java)
                i.putExtra("raza", raza)
                startActivity(i)
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_images, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.option_menu_list_images){
            Toast.makeText(this, "Option Menu 1", Toast.LENGTH_SHORT).show()
            val apiCall = API().crearServicioAPI()
            apiCall.listaImagenesDePerrosPorRaza("hound").enqueue(object: Callback<ListBreed>{
                override fun onResponse(call: Call<ListBreed>, response: Response<ListBreed>) {
                    //Nuestra logica
                    val dogs = response.body()?.message //array
                    Log.d("PRUEBAS", "Status de la respuesta ${response.body()?.status}")
                    if (dogs != null){
                        for (dog in dogs){
                            Log.d("Pruebas", "Perro es $dog")
                        }
                    }

                }

                override fun onFailure(call: Call<ListBreed>, t: Throwable) {
                    TODO("Not yet implemented")
                }

            })
        }
        return super.onOptionsItemSelected(item)
    }

    private fun traerImagenAleatoria(){
        val apiCall = API().crearServicioAPI()

        apiCall.imagenAleatoria().enqueue(object : Callback<ImageRandom> {
            override fun onResponse(call: Call<ImageRandom>, response: Response<ImageRandom>) {
                Log.d("API_PRUEBA", "status es " + response.body()?.status)
                Log.d("API_PRUEBA ", "message es " + response.body()?.message)

                // Aqui hacer lo que necesitemos con los datos
                val status = response.body()?.status
                val message = response.body()?.message

                if(status == "success"){
                    txtURL.text = message
                    Picasso.get().load(response.body()?.message).into(imgRandom)
                } else {
                    Toast.makeText(
                        this@MainActivity,
                        "Error Status",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

            override fun onFailure(call: Call<ImageRandom>, t: Throwable) {
                Toast.makeText(
                    this@MainActivity,
                    "No fue posible conectar a API",
                    Toast.LENGTH_SHORT
                ).show()
            }
    })
}
}