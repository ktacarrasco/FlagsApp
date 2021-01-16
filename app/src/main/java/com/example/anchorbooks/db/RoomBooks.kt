package com.example.anchorbooks.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.anchorbooks.pojo.Books

@Database(entities = [Books::class], version = 1,exportSchema = false)
abstract class RoomBooks : RoomDatabase(){


    abstract fun barDao() : DaoBooks

    companion object {
        @Volatile
        private var INSTANCE: RoomBooks? = null

        fun getDatabase(context: Context): RoomBooks {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    RoomBooks::class.java,
                    "Books_table")
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}
