package com.example.weatherapp.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class HourlyDto(
    val apparent_temperature: List<Double>,
    val precipitation: List<Double>,
    val pressure_msl: List<Double>,
    val rain: List<Double>,
    val relative_humidity_2m: List<Int>,
    val temperature_2m: List<Double>,
    val time: List<String>,
    val uv_index: List<Double>,
    val weather_code: List<Int>,
    val wind_speed_10m: List<Double>,
    val is_day: List<Int>
)