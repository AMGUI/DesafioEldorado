package com.example.appdesafiodasemana3.adpter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.appdesafiodasemana3.R
import com.example.appdesafiodasemana3.model.DadosDeImagem
import com.example.appdesafiodasemana3.utils.ImageUtils.base64ToBitmap

class ImagensAdapter(private val dadosDeImagem: List<DadosDeImagem> ):RecyclerView.Adapter<ImagensAdapter.AdapterViewHolder>() {

    inner class AdapterViewHolder(itemView:View) : RecyclerView.ViewHolder(itemView){
        val photo: ImageView = itemView.findViewById(R.id.totoroImg)
        val legenda: TextView = itemView.findViewById(R.id.textoDaPostagem)
        fun bind(dados : DadosDeImagem){
            photo.setImageBitmap(base64ToBitmap(dados.image))
            legenda.text = dados.postagem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.adapter_lista_post,parent,false)
        return AdapterViewHolder(view)
    }

    override fun onBindViewHolder(holder: AdapterViewHolder, position: Int) {
        val texto = dadosDeImagem[position]
        holder.bind(texto)
    }

    override fun getItemCount() = dadosDeImagem.size
}