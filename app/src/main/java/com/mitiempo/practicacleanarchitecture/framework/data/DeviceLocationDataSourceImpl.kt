package com.mitiempo.practicacleanarchitecture.framework.data

import com.mitiempo.data.DeviceLocationDataSource
import com.mitiempo.domain.Location
import java.util.*
import kotlin.random.Random


class DeviceLocationDataSourceImpl : DeviceLocationDataSource {
    private val random = Random(System.currentTimeMillis())

    override fun getDeviceLocation(): Location = FrameworkLocation(
        "${random.nextDouble() * 180 - 90}",
        "${random.nextDouble() * 180 - 90}",
        Date()
    ).toDomainLocation()
}

//Estas clases corresponderan solo al consumo de un servicio la aplicacion siempre devolvera un objeto del dominio.
data class FrameworkLocation(val latitud : String, val longitud : String, val date: Date)

//Las funciones de extension se deben almacenar paquete dentro del mismo entorno, en este caso consumir un api, para las diferentes transformaciondes de
//los modelos recibidos desde la fuente de datos.
fun FrameworkLocation.toDomainLocation() = Location().apply {
    lat = this@toDomainLocation.latitud
    longitude = this@toDomainLocation.longitud
    date = this@toDomainLocation.date
}