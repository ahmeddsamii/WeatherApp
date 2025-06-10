package com.example.weatherapp.presentation.state.mapper

data class DailyWeatherUiState(
    val weatherIcons: List<Int>,
    val maxTemperatures: List<String>,
    val minimumTemperatures: List<String>,
    val times: List<String>,
    val weatherCodes: List<Int>
)
