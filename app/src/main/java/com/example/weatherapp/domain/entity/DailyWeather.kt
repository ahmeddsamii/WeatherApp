package com.example.weatherapp.domain.entity

data class DailyWeather(
    val weatherIcons: List<Int>,
    val maxTemperatures: List<String>,
    val minimumTemperatures: List<String>,
    val times: List<String>,
    val weatherCodes: List<Int>
)
