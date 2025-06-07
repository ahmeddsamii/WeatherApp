package com.example.weatherapp.domain.entity

data class HourlyUnits(
    val temperature_2m: String,
    val time: String,
    val weather_code: String,
    val is_day: String
)