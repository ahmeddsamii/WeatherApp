package com.example.weatherapp.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class DailyUnitsDto(
    val rain_sum: String,
    val temperature_2m_max: String,
    val temperature_2m_min: String,
    val time: String,
    val weather_code: String,
)