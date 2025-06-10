package com.example.weatherapp.presentation.ui.utils

import android.content.Context
import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.toBitmap
import androidx.palette.graphics.Palette
import com.example.weatherapp.R
import com.example.weatherapp.domain.entity.WeatherCardDetails
import com.example.weatherapp.presentation.state.mapper.WeatherUiState

object HomeScreenUtils {
    fun splitTodaySectionTime(time: String): String {
        val splitTime = time.split("T")
        return splitTime[1]
    }

    fun getWeatherInfoCardInformation(
        weather: WeatherUiState,
    ): List<WeatherCardDetails> {
        return listOf(
            WeatherCardDetails(
                title = "Wind", value = weather.current.windSpeed, iconResId = R.drawable.fast_wind
            ), WeatherCardDetails(
                title = "Humidity",
                value = weather.current.humidity,
                iconResId = R.drawable.humidity
            ), WeatherCardDetails(
                title = "Rain", value = weather.current.rain, iconResId = R.drawable.rain
            ), WeatherCardDetails(
                title = "UV Index", value = weather.current.uvIndex, iconResId = R.drawable.uv_index
            ), WeatherCardDetails(
                title = "Pressure",
                value = weather.current.pressure,
                iconResId = R.drawable.pressure
            ), WeatherCardDetails(
                title = "Feels like",
                value = weather.current.feelsLike,
                iconResId = R.drawable.feels_like
            )
        )
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