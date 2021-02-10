package com.example.appdesafiodasemana3.db

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.appdesafiodasemana3.model.DadosDeImagem
import java.nio.channels.WritableByteChannel

class DataBaseApp(ctx: Context): SQLiteOpenHelper(ctx,DB_NAME,null,DB_VERSION) {

    companion object{
        private val DB_VERSION =  1
        private val DB_NAME = "Post"
        private  val TABELA_NAME = "Postagem"
        private  val TEXTO = "Texto"
        private  val IMG = "Img"



    }

    override fun onCreate(db: SQLiteDatabase?) {
        val CREATE_TABLE = "CREATE TABLE $DB_NAME ($TEXTO TEXT, $IMG TEXT );"
        if (db != null) {
            db.execSQL(CREATE_TABLE)
        }
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        val DROP_TABLE = "DROP TABLE IF EXISTS $TABELA_NAME ;"
        db?.execSQL(DROP_TABLE)
        onCreate(db)
    }

    fun addDadosDeImagem(dadosDeImagem: DadosDeImagem){
        val db =  writableDatabase
        val values = ContentValues().apply {
            put(TEXTO,dadosDeImagem.postagem)
            put(IMG,dadosDeImagem.foto)
        }
        db.insert(TABELA_NAME,null,values)

    }

/*
    fun getDadosDeImagem(): DadosDeImagem {
        val dadosDeImagem :DadosDeImagem = DadosDeImagem()
        val db = writableDatabase
        val selectQuery = "SELECT * FROM $TABELA_NAME;"
        val postagem = db.rawQuery(selectQuery, null)
        postagem?.moveToFirst()
        dadosDeImagem.postagem = postagem.getString(postagem.getColumnIndex(TEXTO))
        dadosDeImagem.foto = postagem.getString(postagem.getColumnIndex(IMG))
        postagem.close()
        return dadosDeImagem
    }

*/
}