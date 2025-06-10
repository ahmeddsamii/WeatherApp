package com.example.weatherapp.presentation.state.mapper

import android.icu.text.SimpleDateFormat
import com.example.weatherapp.R
import com.example.weatherapp.domain.entity.CurrentUnits
import com.example.weatherapp.domain.entity.CurrentWeather
import com.example.weatherapp.domain.entity.DailyWeather
import com.example.weatherapp.domain.entity.DailyWeatherUnits
import com.example.weatherapp.domain.entity.HourlyWeather
import com.example.weatherapp.domain.entity.HourlyWeatherUnits
import com.example.weatherapp.domain.entity.WeatherResponse
import java.util.Calendar
import java.util.Date
import java.util.Locale

fun WeatherResponse.toWeatherUiState(): WeatherUiState {
    return WeatherUiState(
        current = this.current.toCurrentWeatherUiState(this.currentUnits),
        daily = this.daily.toDailyWeatherUiState(this.dailyUnits),
        hourly = this.hourly.toHourlyWeatherUiState(this.hourlyUnits)
    )
}

fun CurrentWeather.toCurrentWeatherUiState(units: CurrentUnits): CurrentWeatherUiState {
    return CurrentWeatherUiState(
        weatherIcon = getIconsByWeatherCode(this.weatherCode.toString(), this.isDay),
        weatherStatus = getWeatherStatusByWeatherCode(this.weatherCode.toString()),
        feelsLike = "${this.feelsLike} ${units.feelsLike}",
        isDay = this.isDay,
        pressure = "${this.pressure} ${units.pressure}",
        rain = "${this.rain} ${units.rain}",
        humidity = "${this.humidity} ${units.humidity}",
        temperature = "${this.temperature} ${units.temperature}",
        time = this.time,
        uvIndex = "${this.uvIndex} ${units.uvIndex}",
        weatherCode = this.weatherCode,
        windSpeed = "${this.windSpeed} ${units.windSpeed}"

    )
}

fun DailyWeather.toDailyWeatherUiState(units: DailyWeatherUnits): DailyWeatherUiState {
    return DailyWeatherUiState(
        weatherIcons = this.weatherCodes.map { code ->
            getIconsByWeatherCode(code.toString(), 1)
        },
        maxTemperatures = this.maxTemperatures.map { "$it ${units.maxTemperatures}" },
        minimumTemperatures = this.minimumTemperatures.map { "$it ${units.minimumTemperatures}" },
        times = this.times.map { getDayName(it) },
        weatherCodes = this.weatherCodes

    )
}

fun HourlyWeather.toHourlyWeatherUiState(units: HourlyWeatherUnits): HourlyWeatherUiState {
    return HourlyWeatherUiState(
        temperatures = this.temperatures.map { "$it ${units.temperature}" },
        times = this.times.filter { isToday(it) },
        weatherCodes = this.weatherCodes,
        isDays = this.isDays,
        weatherIcons = this.weatherCodes.mapIndexed { index, code ->
            getIconsByWeatherCode(code.toString(), this.isDays[index])
        }

    )
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
        "3" -> "Overcast"
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

fun getDayName(dateString: String): String {
    val splitString = dateString.split("T")
    val dayInFormOfDate = splitString[0]

    val inputFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    val outputFormat = SimpleDateFormat("EEEE", Locale.getDefault())

    val date: Date = inputFormat.parse(dayInFormOfDate) ?: return ""
    return outputFormat.format(date)
}

fun isToday(dateTimeString: String): Boolean {
    val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm", Locale.getDefault())
    val todayFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())

    return try {
        val inputDate = inputFormat.parse(dateTimeString) ?: return false
        val now = Date()

        val inputDateStr = todayFormat.format(inputDate)
        val todayStr = todayFormat.format(now)

        if (inputDateStr != todayStr) {
            return false
        }

        val currentHour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY)
        val inputCalendar = Calendar.getInstance().apply { time = inputDate }
        val inputHour = inputCalendar.get(Calendar.HOUR_OF_DAY)

        inputHour >= currentHour

    } catch (e: Exception) {
        false
    }
}