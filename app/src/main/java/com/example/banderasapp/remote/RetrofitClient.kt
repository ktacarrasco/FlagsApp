package com.example.banderasapp.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient {

    companion object {
        // private const val BASE_URL = "https://my-json-server.typicode.com/ktacarrasco/apitragos/"
        private const val BASE_URL = "https://restcountries.eu/rest/v2/"

        fun retrofitInstance(): Api {
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return retrofit.create(Api::class.java)
        }
    }
}