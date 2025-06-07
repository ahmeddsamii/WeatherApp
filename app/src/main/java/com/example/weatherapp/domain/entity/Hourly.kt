package com.example.weatherapp.domain.entity

data class Hourly(
    val temperature_2m: List<Double>,
    val time: List<String>,
    val weather_code: List<Int>,
    val is_day: List<Int>
)