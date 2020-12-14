package com.mitiempo.practicacleanarchitecture

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mitiempo.practicacleanarchitecture.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(),MainPresenter.View {

    /**
     * Variables UI
     */
    private lateinit var binding : ActivityMainBinding

    lateinit var mainPresenter: MainPresenter
    lateinit var locationAdapter: LocationAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mainPresenter = MainPresenter(this)
        mainPresenter.onCreate()
        locationAdapter = LocationAdapter(emptyList<Location>().toMutableList(),layoutInflater)
        binding.buttonGenerarLocalizacion.setOnClickListener {
            mainPresenter.onNewLocationButtonClick()
        }

        binding.reciclerViewPosiciones.adapter = locationAdapter
    }

    override fun onDestroy() {
        super.onDestroy()
        mainPresenter.onDestroy()
    }

    override fun updateItems(locations: List<Location>) {
        locationAdapter.listaLocalizaciones = locations.toMutableList()
        locationAdapter.notifyDataSetChanged()
    }

}