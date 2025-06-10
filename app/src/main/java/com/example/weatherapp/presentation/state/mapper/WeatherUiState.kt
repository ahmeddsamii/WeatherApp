package com.example.weatherapp.presentation.state.mapper

data class WeatherUiState(
    val current: CurrentWeatherUiState,
    val daily: DailyWeatherUiState,
    val hourly: HourlyWeatherUiState,
)
