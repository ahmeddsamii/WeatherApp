package com.example.weatherapp.presentation.state.mapper

data class CurrentWeatherUiState(
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
