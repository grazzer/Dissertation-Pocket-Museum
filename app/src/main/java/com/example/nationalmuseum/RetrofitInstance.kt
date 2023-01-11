package com.example.nationalmuseum

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    val api: NatMuseumApi by lazy {
        Retrofit.Builder()
            .baseUrl("https://1133-138-37-230-124.eu.ngrok.io")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NatMuseumApi::class.java)
    }
}