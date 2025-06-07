package com.example.weatherapp.domain.entity

data class Current(
    val apparent_temperature: Double,
    val is_day: Int,
    val pressure_msl: Double,
    val rain: Double,
    val relative_humidity_2m: Int,
    val temperature_2m: Double,
    val uv_index: Double,
    val weather_code: Int,
    val wind_speed_10m: Double
)