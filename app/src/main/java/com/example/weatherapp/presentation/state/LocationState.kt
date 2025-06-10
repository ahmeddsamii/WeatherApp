package com.example.weatherapp.presentation.state

import com.example.weatherapp.domain.entity.CityLocation

sealed class LocationState() {
    data object Initial: LocationState()
    data object OnLoading : LocationState()
    class OnSuccess(val cityLocation: CityLocation) : LocationState()
    class OnError(val errorMessage: String) : LocationState()
}