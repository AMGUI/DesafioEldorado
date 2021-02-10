package com.example.appdesafiodasemana3.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "images_table")
data class DadosDeImagem(
        @PrimaryKey(autoGenerate = true)
        @ColumnInfo(name = "id")
        val id: Int = 0,
        @ColumnInfo(name = "image")
        val image: String = "",
        @ColumnInfo(name = "postagem")
        val postagem: String = ""
)


