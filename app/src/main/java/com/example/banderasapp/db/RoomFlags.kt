package com.example.banderasapp.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.banderasapp.pojo.Flags

@Database(entities = [Flags::class], version = 1,exportSchema = false)
abstract class RoomFlags : RoomDatabase(){


    abstract fun barDao() : DaoFlags

    companion object {
        @Volatile
        private var INSTANCE: RoomFlags? = null

        fun getDatabase(context: Context): RoomFlags {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    RoomFlags::class.java,
                    "Flags_table")
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}
