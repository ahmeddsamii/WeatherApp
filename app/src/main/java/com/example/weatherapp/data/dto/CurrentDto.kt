package com.example.weatherapp.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class CurrentDto(
    val apparent_temperature: Double,
    val interval: Int,
    val is_day: Int,
    val precipitation: Double,
    val pressure_msl: Double,
    val precipitation_probability: Int,
    val relative_humidity_2m: Int,
    val temperature_2m: Double,
    val time: String,
    val uv_index: Double,
    val weather_code: Int,
    val wind_speed_10m: Double
)