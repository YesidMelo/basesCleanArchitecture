package com.mitiempo.usescases

import com.mitiempo.data.LocationRepository
import com.mitiempo.domain.Location

class GetLocations(private val locationRepository: LocationRepository) {

    fun invoque() : List<Location> = locationRepository.getSavedLocations()
}