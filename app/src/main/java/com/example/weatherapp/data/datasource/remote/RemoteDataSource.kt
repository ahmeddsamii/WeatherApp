package com.example.weatherapp.data.datasource.remote

import com.example.weatherapp.data.dto.WeatherResponseDto
import com.example.weatherapp.domain.entity.CityLocation

interface RemoteDataSource {
    suspend fun getWeatherByLocation(cityLocation: CityLocation): WeatherResponseDto
}