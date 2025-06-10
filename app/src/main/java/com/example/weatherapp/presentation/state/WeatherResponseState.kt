package com.example.weatherapp.presentation.state

import com.example.weatherapp.domain.entity.WeatherResponse

sealed class WeatherResponseState() {
    data object Initial : WeatherResponseState()
    data object OnLoading : WeatherResponseState()
    class OnSuccess(val weatherResponse: WeatherResponse) : WeatherResponseState()
    class OnError(val errorMessage: String) : WeatherResponseState()
}