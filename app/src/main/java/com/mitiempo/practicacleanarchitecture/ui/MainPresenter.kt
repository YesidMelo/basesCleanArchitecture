package com.mitiempo.practicacleanarchitecture.ui

import com.mitiempo.data.LocationRepository
import com.mitiempo.domain.Location
import com.mitiempo.practicacleanarchitecture.framework.data.DeviceLocationDataSourceImpl
import com.mitiempo.practicacleanarchitecture.framework.data.InMemoryLocationDataSourceImpl
import com.mitiempo.usescases.GetLocations
import com.mitiempo.usescases.RequestNewLocation
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class MainPresenter(val view : View) : CoroutineScope {

    interface View{
        fun updateItems(locations : List<Location>)
    }

    private val locationRepository = LocationRepository(DeviceLocationDataSourceImpl(),InMemoryLocationDataSourceImpl())
    private val requestNewLocation : RequestNewLocation = RequestNewLocation(locationRepository)
    private val getLocations : GetLocations = GetLocations(locationRepository)
    lateinit var job : Job

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main


    fun onCreate(){
        job = SupervisorJob()

        launch {
            val locations = withContext(Dispatchers.IO){ getLocations.invoque() }
            view.updateItems( locations )
        }
    }

    fun onNewLocationButtonClick() {
        launch {
            val newLocations = withContext(Dispatchers.IO){ requestNewLocation.invoque() }
            view.updateItems(newLocations)
        }
    }

    fun onDestroy(){
        job.cancel()
    }
}