package com.mitiempo.practicacleanarchitecture.framework.data

import com.mitiempo.data.PersistenceLocationDataSource
import com.mitiempo.domain.Location
import java.util.*

class InMemoryLocationDataSourceImpl : PersistenceLocationDataSource {
    private var savedLocations = emptyList<DatabaseLocation>()

    override fun getPersistedLocation() = savedLocations.map { it.toDomainLocation() }

    override fun saveLocation(location: Location){
        savedLocations = savedLocations + location.toDatabaseLocation()
    }
}

//Estas clases corresponderan solo al consumo de un servicio la aplicacion siempre devolvera un objeto del dominio.
data class DatabaseLocation(val latitud : String, val longitud : String, val date: Date)

//Las funciones de extension se deben almacenar paquete dentro del mismo entorno, en este caso consumir un api, para las diferentes transformaciondes de
//los modelos recibidos desde la fuente de datos.
fun DatabaseLocation.toDomainLocation() = Location().apply {
    lat = this@toDomainLocation.latitud
    longitude = this@toDomainLocation.longitud
    date = this@toDomainLocation.date
}

fun Location.toDatabaseLocation() = DatabaseLocation(lat!!,longitude!!,date!!)

//ojo buscar mappers