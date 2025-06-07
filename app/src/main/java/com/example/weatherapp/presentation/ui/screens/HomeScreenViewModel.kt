package com.example.weatherapp.presentation.ui.screens

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.R
import com.example.weatherapp.domain.entity.CityLocation
import com.example.weatherapp.domain.usecase.GetWeatherUseCase
import com.example.weatherapp.presentation.state.WeatherResponseState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeScreenViewModel(
    private val getWeatherUseCase: GetWeatherUseCase
) : ViewModel() {
    private val _state = MutableStateFlow<WeatherResponseState>(WeatherResponseState.onLoading)
    val state = _state.asStateFlow()

    // ismailia 30.5965   32.2715
    // alasaka 63.5888    154.4931
    init {
        getWeatherResponse(CityLocation(30.5965, 32.2715))
    }

    private fun getWeatherResponse(cityLocation: CityLocation) {
        _state.value = WeatherResponseState.onLoading
        viewModelScope.launch(Dispatchers.IO) {
            getWeatherUseCase.getWeather(cityLocation).catch {
                _state.value = WeatherResponseState.onError(it.message ?: "UnExceptedError")
            }.collect {
                _state.value = WeatherResponseState.onSuccess(it)
            }
        }
    }

    fun getIconsByWeatherCode(weatherIconCode: String, isDayHour: Int): Int {
        return when (weatherIconCode) {
            "0" -> if (isDayHour == 1) R.drawable.clear_sky else R.drawable.clear_sky_night
            "1" -> if (isDayHour == 1) R.drawable.mainly_clear else R.drawable.mainly_clear_night
            "2" -> if (isDayHour == 1) R.drawable.partly_cloudy else R.drawable.partly_cloudy_night
            "3" -> if (isDayHour == 1) R.drawable.overcast else R.drawable.overcast_night
            "45" -> if (isDayHour == 1) R.drawable.fog else R.drawable.fog_night
            "48" -> if (isDayHour == 1) R.drawable.depositing_rime_fog else R.drawable.depositing_rime_fog_night
            "51" -> if (isDayHour == 1) R.drawable.drizzle_light else R.drawable.drizzle_light_night
            "53" -> if (isDayHour == 1) R.drawable.drizzle_moderate else R.drawable.drizzle_moderate_night
            "55" -> if (isDayHour == 1) R.drawable.drizzle_intensity else R.drawable.drizzle_intensity_night
            "56" -> if (isDayHour == 1) R.drawable.freezing_drizzle_light else R.drawable.freezing_drizzle_light_night
            "57" -> if (isDayHour == 1) R.drawable.freezing_drizzle_intensity else R.drawable.freezing_drizzle_intensity_night
            "61" -> if (isDayHour == 1) R.drawable.rain_slight else R.drawable.rain_slight_night
            "63" -> if (isDayHour == 1) R.drawable.rain_moderate else R.drawable.rain_moderate_night
            "65" -> if (isDayHour == 1) R.drawable.rain_intensity else R.drawable.rain_intensity_night
            "66" -> if (isDayHour == 1) R.drawable.freezing_loght else R.drawable.freezing_light_night
            "67" -> if (isDayHour == 1) R.drawable.freezing_heavy else R.drawable.freezing_heavy_night
            "71" -> if (isDayHour == 1) R.drawable.snow_fall_light else R.drawable.snow_fall_light_night
            "73" -> if (isDayHour == 1) R.drawable.snow_fall_moderate else R.drawable.snow_fall_moderate_night
            "75" -> if (isDayHour == 1) R.drawable.snow_fall_intensity else R.drawable.snow_fall_intensity_night
            "77" -> if (isDayHour == 1) R.drawable.snow_grains else R.drawable.snow_grains_night
            "80" -> if (isDayHour == 1) R.drawable.rain_shower_slight else R.drawable.rain_shower_slight_night
            "81" -> if (isDayHour == 1) R.drawable.rain_shower_moderate else R.drawable.rain_shower_moderate_night
            "82" -> if (isDayHour == 1) R.drawable.rain_shower_violent else R.drawable.rain_shower_violent_night
            "85" -> if (isDayHour == 1) R.drawable.snow_shower_slight else R.drawable.snow_shower_slight_night
            "86" -> if (isDayHour == 1) R.drawable.snow_shower_heavy else R.drawable.snow_shower_heavy_night
            "95" -> if (isDayHour == 1) R.drawable.thunderstrom_slight_or_moderate else R.drawable.thunderstrom_slight_or_moderate_night
            "96" -> if (isDayHour == 1) R.drawable.thunderstrom_with_slight_hail else R.drawable.thunderstrom_with_slight_hail_night
            "99" -> if (isDayHour == 1) R.drawable.thunderstrom_with_heavy_hail else R.drawable.thunderstrom_with_heavy_hail_night
            else -> R.drawable.clear_sky
        }
    }

    fun getWeatherStatusByWeatherCode(weatherIconCode: String): String {
        return when (weatherIconCode) {
            "0" -> "Clear sky"
            "1" -> "Mainly clear"
            "2" -> "Partly cloudy"
            "3" ->  "Overcast"
            "45" -> "Fog"
            "48" -> "Depositing rime fog"
            "51" -> "Drizzle light"
            "53" -> "Drizzle moderate"
            "55" -> "Drizzle intensity"
            "56" -> "Freezing drizzle light"
            "57" -> "Freezing drizzle intensity"
            "61" -> "Rain slight"
            "63" -> "Rain moderate"
            "65" -> "Rain intensity"
            "66" -> "Freezing light"
            "67" -> "Freezing heavy"
            "71" -> "Snow fall light"
            "73" -> "Snow fall moderate"
            "75" -> "Snow fall intensity"
            "77" -> "Snow grains"
            "80" -> "Rain shower slight"
            "81" -> "Rain shower moderate"
            "82" -> "Rain shower violent"
            "85" -> "Snow shower slight"
            "86" -> "Snow shower heavy"
            "95" -> "Thunder storm slight or moderate"
            "96" -> "Thunder storm with slight hail"
            "99" -> "Thunder storm with heavy hail"
            else -> "Unknown"
        }
    }
}

