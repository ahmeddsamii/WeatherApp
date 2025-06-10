package com.example.weatherapp.domain.entity

data class DailyWeather(
    val maxTemperatures: List<Double>,
    val minimumTemperatures: List<Double>,
    val times: List<String>,
    val weatherCodes: List<Int>
)
