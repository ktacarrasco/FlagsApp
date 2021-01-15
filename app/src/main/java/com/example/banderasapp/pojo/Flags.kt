package com.example.banderasapp.pojo

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName


@Entity(tableName = "Flags_table")
    data class Flags (@SerializedName("mId") @PrimaryKey(autoGenerate = true) val  id : Int,
                      val name: String,
                      val alpha2Code: String,
                      val capital: String,
                      val region: String,
                      val subregion: String,
                      val population: Int,
                      val nativeName: String)
