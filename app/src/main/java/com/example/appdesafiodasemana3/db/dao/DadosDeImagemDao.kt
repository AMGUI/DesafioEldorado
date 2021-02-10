package com.example.appdesafiodasemana3.db.dao

import androidx.room.*
import com.example.appdesafiodasemana3.model.DadosDeImagem

@Dao
interface DadosDeImagemDao {
    @Query("SELECT * FROM images_table")
    fun pegarImages() : List<DadosDeImagem>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun salvarFoto(dadosDeImagem: DadosDeImagem)

    @Delete()
    fun deletarFoto(dadosDeImagem: DadosDeImagem)

    @Query("DELETE FROM images_table")
    fun limparBanco()
}