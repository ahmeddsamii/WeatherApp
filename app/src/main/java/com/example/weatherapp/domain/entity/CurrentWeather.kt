package com.example.weatherapp.domain.entity

data class CurrentWeather(
    val weatherIcon: Int,
    val weatherStatus: String,
    val feelsLike: String,
    val isDay: Int,
    val pressure: String,
    val rain: String,
    val humidity: String,
    val temperature: String,
    val time: String,
    val uvIndex: String,
    val weatherCode: Int,
    val windSpeed: String
)
