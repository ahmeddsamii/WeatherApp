package com.example.weatherapp.domain.entity

data class HourlyWeather(
    val temperatures: List<Double>,
    val times: List<String>,
    val weatherCodes: List<Int>,
    val isDays: List<Int>,
)
