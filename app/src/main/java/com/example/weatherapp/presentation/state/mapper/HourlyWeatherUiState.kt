package com.example.weatherapp.presentation.state.mapper

data class HourlyWeatherUiState(
    val temperatures: List<String>,
    val times: List<String>,
    val weatherCodes: List<Int>,
    val isDays: List<Int>,
    val weatherIcons: List<Int>
)
