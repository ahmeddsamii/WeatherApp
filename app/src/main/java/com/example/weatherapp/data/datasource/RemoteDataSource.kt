package com.example.weatherapp.data.datasource

import com.example.weatherapp.data.dto.CityLocationDto
import com.example.weatherapp.data.dto.WeatherResponseDto

interface RemoteDataSource {
    suspend fun getWeatherByLocation(cityLocation: CityLocationDto): WeatherResponseDto
}