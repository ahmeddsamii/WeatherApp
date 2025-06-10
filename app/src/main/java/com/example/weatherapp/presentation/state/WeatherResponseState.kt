package com.example.weatherapp.presentation.state

import com.example.weatherapp.presentation.state.mapper.WeatherUiState


sealed class WeatherResponseState() {
    data object Initial : WeatherResponseState()
    data object OnLoading : WeatherResponseState()
    class OnSuccess(val weatherResponse: WeatherUiState) : WeatherResponseState()
    class OnError(val errorMessage: String) : WeatherResponseState()
}