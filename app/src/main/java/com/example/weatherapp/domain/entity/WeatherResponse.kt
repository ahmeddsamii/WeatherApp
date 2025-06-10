package com.example.weatherapp.domain.entity

data class WeatherResponse(
    val current: CurrentWeather,
    val currentUnits: CurrentUnits,
    val daily: DailyWeather,
    val dailyUnits: DailyWeatherUnits,
    val hourly: HourlyWeather,
    val hourlyUnits: HourlyWeatherUnits
)