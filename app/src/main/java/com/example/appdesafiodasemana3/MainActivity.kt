package com.example.appdesafiodasemana3

import android.content.Intent
import android.graphics.Camera
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.core.content.ContentProviderCompat.requireContext
import com.google.android.material.floatingactionbutton.FloatingActionButton
const val TIRAR_FOTO = 2
class MainActivity : AppCompatActivity() {
    lateinit var texto: EditText
    lateinit var camera: ImageView
    lateinit var butaopostagem : FloatingActionButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        butaopostagem = findViewById(R.id.butaoPostagem)
        butaopostagem.setOnClickListener(View.OnClickListener {
            postagem()
        })
        camera = findViewById(R.id.cameraView)
        camera.setOnClickListener(View.OnClickListener {
            chamacamera()
        })


    }

    private fun postagem() {

    }

    private fun chamacamera() {
        val cam = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(cam, TIRAR_FOTO)

    }
}