package com.mitiempo.practicacleanarchitecture.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mitiempo.domain.Location
import com.mitiempo.practicacleanarchitecture.databinding.ItemListaLocalizacionesBinding

class LocationAdapter(
    var listaLocalizaciones : MutableList<Location> = emptyList<Location>().toMutableList(),
    val layoutInflater: LayoutInflater
) : RecyclerView.Adapter<LocationAdapter.ViewHolder>(){


    inner class ViewHolder(val vista : View,val binding : ItemListaLocalizacionesBinding) : RecyclerView.ViewHolder(vista){

        fun llenarVista(localtion : Location){
            binding.posicion = localtion
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemListaLocalizacionesBinding.inflate(layoutInflater)
        return ViewHolder(binding.root,binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.llenarVista(listaLocalizaciones[position])
    }

    override fun getItemCount(): Int {
        return listaLocalizaciones.size
    }

}