package com.example.appdesafiodasemana3.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.appdesafiodasemana3.db.dao.DadosDeImagemDao
import com.example.appdesafiodasemana3.model.DadosDeImagem

@Database(entities = [DadosDeImagem::class], version = 1, exportSchema = false)
 abstract class DataBaseApp : RoomDatabase() {

    abstract fun dadosDeImagemDao(): DadosDeImagemDao

    companion object {
        @Volatile
        private var INSTANCE: DataBaseApp? = null

        fun getDatabase(context: Context): DataBaseApp {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    DataBaseApp::class.java,
                    "images_database"
                )
                    .allowMainThreadQueries()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}