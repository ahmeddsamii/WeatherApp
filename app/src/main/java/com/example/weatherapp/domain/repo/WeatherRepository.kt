package com.example.weatherapp.domain.repo

import com.example.weatherapp.domain.entity.CityLocation
import com.example.weatherapp.domain.entity.WeatherResponse
import kotlinx.coroutines.flow.Flow


interface WeatherRepository {
    fun getWeatherByLocation(cityLocation: CityLocation): Flow<WeatherResponse>
}