package com.example.weatherapp.domain.entity

data class CurrentUnits(
    val apparent_temperature: String,
    val is_day: String,
    val pressure_msl: String,
    val rain: String,
    val relative_humidity_2m: String,
    val temperature_2m: String,
    val uv_index: String,
    val weather_code: String,
    val wind_speed_10m: String
)