package com.example.anchorbooks.remote

import com.example.anchorbooks.pojo.Books
import retrofit2.Call
import retrofit2.http.GET

interface Api {
    @GET("bookDetail")
    fun getBooks(): Call<List<Books>>

    //@GET("book")
    //fun getBooks(): Call<List<Books>>


}