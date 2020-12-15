package com.mitiempo.data

import com.mitiempo.domain.Location

class LocationRepository(
    private val deviceLocationDataSource: DeviceLocationDataSource,
    private val percistenceLocationDataSource: PersistenceLocationDataSource
    ) {

    fun requestNewLocation() : List<Location> {
        val newLocation = deviceLocationDataSource.getDeviceLocation()
        percistenceLocationDataSource.saveLocation(newLocation)
        return percistenceLocationDataSource.getPersistedLocation()
    }

    fun getSavedLocations() : List<Location> = percistenceLocationDataSource.getPersistedLocation()


}

interface DeviceLocationDataSource {
    fun getDeviceLocation(): Location
}

interface PersistenceLocationDataSource {
    fun getPersistedLocation(): List<Location>
    fun saveLocation(location: Location)
}