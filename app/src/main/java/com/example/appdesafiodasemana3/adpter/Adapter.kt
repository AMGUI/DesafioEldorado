package com.example.appdesafiodasemana3.adpter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.appdesafiodasemana3.R
import com.example.appdesafiodasemana3.model.DadosDeImagem

class Adapter(private val dadosDeImagem: List<DadosDeImagem> ):RecyclerView.Adapter<Adapter.AdapterViewHolder>() {

    inner class AdapterViewHolder(itemView:View) : RecyclerView.ViewHolder(itemView){
        val textoLayout: TextView = itemView.findViewById(R.id.totoroImg)
        fun bind(dados : DadosDeImagem){
            textoLayout.text = dados.postagem
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