package com.example.anchorbooks.pojo

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName


@Entity(tableName = "Books_table")
    data class Books  (@PrimaryKey() @NonNull val  id : Int,
                       val author: String,
                       val country: String,
                       val imageLink: String,
                       val language: String,
                       val title: String,
                       val pages: Int,
                       val year: Int,
                       val price: Int,
                       val lastPrice: Int,
                       val link: String,
                       val delivery: Boolean)
