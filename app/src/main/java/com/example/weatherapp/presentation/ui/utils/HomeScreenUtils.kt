package com.example.weatherapp.presentation.ui.utils

import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.drawable.Drawable
import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.toBitmap
import androidx.palette.graphics.Palette
import com.example.weatherapp.R
import com.example.weatherapp.domain.entity.WeatherDetails
import com.example.weatherapp.domain.entity.WeatherResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

object HomeScreenUtils {
    fun splitTodaySectionTime(time: String): String {
        val splitTime = time.split("T")
        return splitTime[1]
    }

    fun getWeatherInfoCardInformation(
        weatherResponse: WeatherResponse,
    ): List<WeatherDetails> {
        return listOf(
            WeatherDetails(
                title = "Wind",
                value = "${weatherResponse.current.wind_speed_10m} ${weatherResponse.current_units.wind_speed_10m}",
                iconResId = R.drawable.fast_wind
            ),
            WeatherDetails(
                title = "Humidity",
                value = "${weatherResponse.current.relative_humidity_2m} ${weatherResponse.current_units.relative_humidity_2m}",
                iconResId = R.drawable.humidity
            ),
            WeatherDetails(
                title = "Rain",
                value = "${weatherResponse.current.rain} ${weatherResponse.current_units.rain}",
                iconResId = R.drawable.rain
            ),
            WeatherDetails(
                title = "UV Index",
                value = "${weatherResponse.current.uv_index} ${weatherResponse.current_units.uv_index}",
                iconResId = R.drawable.uv_index
            ),
            WeatherDetails(
                title = "Pressure",
                value = "${weatherResponse.current.pressure_msl} ${weatherResponse.current_units.pressure_msl}",
                iconResId = R.drawable.pressure
            ),
            WeatherDetails(
                title = "Feels like",
                value = "${weatherResponse.current.apparent_temperature} ${weatherResponse.current_units.apparent_temperature}",
                iconResId = R.drawable.feels_like
            )
        )
    }

    fun getNextSevenDaysDate(
        weatherResponse: WeatherResponse
    ): List<String> {
        val listOfNextSevenDaysDates = mutableListOf<String>()

        for (i in 0..6) {
            val dateOfOneDay = weatherResponse.daily.time[i]
            listOfNextSevenDaysDates.add(dateOfOneDay)
        }
        return listOfNextSevenDaysDates
    }

    fun getNextSevenDaysMaxTemperatures(
        weatherResponse: WeatherResponse
    ): List<String> {
        val listOfNextSevenDaysMaxTemperatures = mutableListOf<String>()

        for (i in 0..6) {
            val dateOfOneDay =
                "${weatherResponse.daily.temperature_2m_max[i]} ${weatherResponse.daily_units.temperature_2m_max}"
            listOfNextSevenDaysMaxTemperatures.add(dateOfOneDay)
        }
        return listOfNextSevenDaysMaxTemperatures
    }

    fun getNextSevenDaysMinimumTemperatures(
        weatherResponse: WeatherResponse
    ): List<String> {
        val listOfNextSevenDaysMinimumTemperatures = mutableListOf<String>()

        for (i in 0..6) {
            val dateOfOneDay =
                "${weatherResponse.daily.temperature_2m_min[i]} ${weatherResponse.daily_units.temperature_2m_min}"
            listOfNextSevenDaysMinimumTemperatures.add(dateOfOneDay)
        }
        return listOfNextSevenDaysMinimumTemperatures
    }

    fun extractDominantColor(context: Context, @DrawableRes drawableRes: Int): Color {
        val drawable = ContextCompat.getDrawable(context, drawableRes)
        val bitmap = drawable?.toBitmap()

        return bitmap?.let {
            val palette = Palette.from(it).generate()
            Color(palette.getDominantColor(Color.Gray.toArgb()))
        } ?: Color.Gray
    }
}