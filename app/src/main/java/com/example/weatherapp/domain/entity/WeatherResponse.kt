package com.example.weatherapp.domain.entity

data class WeatherResponse(
    val current: CurrentWeather,
    val daily: DailyWeather,
    val hourly: HourlyWeather,
)