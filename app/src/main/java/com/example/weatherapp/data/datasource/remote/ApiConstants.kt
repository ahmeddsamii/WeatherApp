package com.example.weatherapp.data.datasource.remote

object ApiConstants {
    const val WEATHER_HOST = "api.open-meteo.com"
    const val WEATHER_PATH = "v1/forecast"

    const val CURRENT_PARAMS = "temperature_2m,apparent_temperature,is_day,weather_code,wind_speed_10m,relative_humidity_2m,precipitation,rain,uv_index,pressure_msl"
    const val DAILY_PARAMS = "temperature_2m_max,temperature_2m_min,rain_sum,weather_code"
    const val HOURLY_PARAMS = "temperature_2m,apparent_temperature,precipitation,weather_code,wind_speed_10m,relative_humidity_2m,rain,uv_index,pressure_msl,is_day"
    const val TIMEZONE = "auto"
}