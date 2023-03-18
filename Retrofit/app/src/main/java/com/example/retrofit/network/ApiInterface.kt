package com.example.retrofit.network

import com.example.retrofit.models.ImageRandom
import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {
    @GET("breeds/image/random")
    fun imagenAleatoria(): Call<ImageRandom>
}