package com.example.weatherapp.presentation.ui.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.domain.entity.CityLocation
import com.example.weatherapp.domain.repo.LocationRepository
import com.example.weatherapp.domain.usecase.GetWeatherUseCase
import com.example.weatherapp.presentation.state.LocationState
import com.example.weatherapp.presentation.state.WeatherResponseState
import com.example.weatherapp.presentation.state.mapper.toWeatherUiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class HomeScreenViewModel(
    private val getWeatherUseCase: GetWeatherUseCase,
    private val locationRepository: LocationRepository
) : ViewModel() {

    private val _state = MutableStateFlow<WeatherResponseState>(WeatherResponseState.Initial)
    val state = _state.asStateFlow()

    private val _locationState = MutableStateFlow<LocationState>(LocationState.Initial)
    val locationState = _locationState.asStateFlow()

    private fun getWeatherResponse(cityLocation: CityLocation) {
        _state.value = WeatherResponseState.OnLoading
        viewModelScope.launch(Dispatchers.IO) {
            getWeatherUseCase.getWeather(cityLocation).catch {
                _state.value = WeatherResponseState.OnError(it.message ?: "UnExceptedError")
            }.collect {
                _state.value = WeatherResponseState.OnSuccess(it.toWeatherUiState())
            }
        }
    }

    fun onGetLocationClickListener() {
        _locationState.value = LocationState.OnLoading
        locationRepository.getLocation(
            onSuccess = { location, locationAsText ->
                _locationState.value = LocationState.OnSuccess(
                    cityLocation = CityLocation(location.latitude, location.longitude, locationAsText),
                )
                getWeatherResponse(CityLocation(location.latitude, location.longitude, locationAsText))
            },
            onError = { _locationState.value = LocationState.OnError(it) }
        )
    }
}

