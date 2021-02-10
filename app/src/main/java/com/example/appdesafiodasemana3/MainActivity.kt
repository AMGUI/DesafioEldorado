package com.example.appdesafiodasemana3

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.appdesafiodasemana3.adpter.ImagensAdapter
import com.example.appdesafiodasemana3.db.DataBaseApp
import com.example.appdesafiodasemana3.db.dao.DadosDeImagemDao
import com.example.appdesafiodasemana3.model.DadosDeImagem
import com.example.appdesafiodasemana3.utils.ImageUtils.bitmapToBase64
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.*


class MainActivity : AppCompatActivity() {

    lateinit var imagemPostagem: EditText
    lateinit var camera: ImageView
    lateinit var butaopostagem : FloatingActionButton
    lateinit var imagesRecyclerView: RecyclerView

    lateinit var database: DataBaseApp
    lateinit var imagensDao: DadosDeImagemDao

    lateinit var imagensAdapter: ImagensAdapter
    private val listaImagens: ArrayList<DadosDeImagem> = arrayListOf()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        database = DataBaseApp.getDatabase(this)
        imagensDao = database.dadosDeImagemDao()

        imagemPostagem = findViewById(R.id.editTextPostagem)
        imagesRecyclerView = findViewById(R.id.listaPostagem)

        imagensAdapter = ImagensAdapter(listaImagens)

        butaopostagem = findViewById(R.id.butaoPostagem)
        butaopostagem.setOnClickListener(View.OnClickListener {
            postagem()
        })

        camera = findViewById(R.id.cameraView)
        camera.setOnClickListener(View.OnClickListener {
            chamacamera()
        })

        // setup da recyclerView
        imagesRecyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity, RecyclerView.VERTICAL, false)
            setHasFixedSize(true)
            adapter = imagensAdapter
        }

        //Consultar imagens do banco e atualizar recycler
        atualizaRecycler()

    }

    private fun postagem() { }

    private fun atualizaRecycler() {
        listaImagens.clear()
        listaImagens.addAll(imagensDao.pegarImages())
        imagensAdapter.notifyDataSetChanged()
    }

    private fun chamacamera() {
        //Solcitar permissão do usuário pra usar a camera
        val cam = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(cam, REQUEST_CODE_TIRAR_FOTO)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when(requestCode) {
            REQUEST_CODE_TIRAR_FOTO -> {
                if (resultCode == Activity.RESULT_OK && data != null) {
                    val bitmap = data.extras?.get("data") as Bitmap
                    val base64 = bitmapToBase64(bitmap)
                    val dadoDeImagem = DadosDeImagem(
                        id = 0,
                        image = base64,
                        postagem = imagemPostagem.text.toString()
                    )
                    imagemPostagem.setText("")
                    imagensDao.salvarFoto(dadoDeImagem)
                    atualizaRecycler()
                }
            }
        }
    }

    companion object {
        private const val REQUEST_CODE_TIRAR_FOTO = 9875
    }
}