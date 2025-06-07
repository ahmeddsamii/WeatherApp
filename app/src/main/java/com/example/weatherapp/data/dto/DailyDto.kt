package com.example.weatherapp.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class DailyDto(
    val rain_sum: List<Double>, // Changed from Int to Double
    val temperature_2m_max: List<Double>,
    val temperature_2m_min: List<Double>,
    val time: List<String>,
    val weather_code: List<Int>
)