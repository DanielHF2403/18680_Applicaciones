package com.example.retrofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.example.retrofit.models.ImageRandom
import com.example.retrofit.models.ListBreed
import com.example.retrofit.network.API
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        traerImagenAleatoria()
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
            override fun onResponse(
                call: Call<ImageRandom>, response: Response<ImageRandom>
            ) {
                Log.d("API_PRUEBA", "status es " + response.body()?.status)
                Log.d("API_PRUEBA " , "message es " + response.body()?.message)
            // Aqui hacer lo que necesitemos con los datos
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