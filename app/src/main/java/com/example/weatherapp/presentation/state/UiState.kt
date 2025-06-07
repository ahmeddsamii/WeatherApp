package com.example.weatherapp.presentation.state

import com.example.weatherapp.domain.entity.WeatherResponse

sealed class WeatherResponseState() {
    data object onLoading : WeatherResponseState()
    class onSuccess(val weatherResponse: WeatherResponse) : WeatherResponseState()
    class onError(val errorMessage: String) : WeatherResponseState()
}