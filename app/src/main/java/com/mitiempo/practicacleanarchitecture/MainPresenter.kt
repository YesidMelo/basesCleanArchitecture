package com.mitiempo.practicacleanarchitecture

import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext
import kotlin.random.Random

class MainPresenter(val view : View) : CoroutineScope {

    interface View{
        fun updateItems(locations : List<Location>)
    }

    lateinit var job : Job

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main

    private val random = Random(System.currentTimeMillis())
    private var savedLocations = emptyList<Location>()

    fun onCreate(){
        job = SupervisorJob()

        launch {
            view.updateItems( withContext(Dispatchers.IO){ savedLocations } )
        }
    }

    fun onNewLocationButtonClick() {
        launch {
            val newLocations = withContext(Dispatchers.IO){ requestNewLocation() }
            view.updateItems(newLocations)
        }
    }

    private fun requestNewLocation() : List<Location> {
        val newLocation = getDeviceLocation()
        savedLocations = savedLocations + newLocation
        return savedLocations
    }

    private fun getDeviceLocation(): Location = Location().apply {
        lat = "${random.nextDouble() * 180 - 90}"
        longitude = "${random.nextDouble() * 180 - 90}"
    }

    fun onDestroy(){
        job.cancel()
    }
}