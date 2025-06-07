package com.example.weatherapp.domain.usecase

import com.example.weatherapp.domain.entity.CityLocation
import com.example.weatherapp.domain.entity.WeatherResponse
import com.example.weatherapp.domain.repo.WeatherRepository
import kotlinx.coroutines.flow.Flow

class GetWeatherUseCase(private val repository: WeatherRepository) {
    fun getWeather(cityLocation: CityLocation): Flow<WeatherResponse> {
        return repository.getWeatherByLocation(cityLocation)
    }
}