package com.example.banderasapp.remote

import com.example.banderasapp.pojo.Flags
import retrofit2.Call
import retrofit2.http.GET

interface Api {
    @GET("all")
    fun getFlags(): Call<Flags>
    //fun getTragos(): Call<List<Cocktails>>
}