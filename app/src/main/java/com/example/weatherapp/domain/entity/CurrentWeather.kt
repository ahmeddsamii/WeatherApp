package com.example.weatherapp.domain.entity

data class CurrentWeather(
    val feelsLike: Double,
    val isDay: Int,
    val pressure: Double,
    val rain: Int,
    val humidity: Int,
    val temperature: Double,
    val time: String,
    val uvIndex: Double,
    val weatherCode: Int,
    val windSpeed: Double
)
