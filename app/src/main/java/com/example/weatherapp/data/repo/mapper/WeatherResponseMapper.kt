package com.example.weatherapp.data.repo.mapper

import com.example.weatherapp.R
import com.example.weatherapp.data.dto.CurrentDto
import com.example.weatherapp.data.dto.CurrentUnitsDto
import com.example.weatherapp.data.dto.DailyDto
import com.example.weatherapp.data.dto.DailyUnitsDto
import com.example.weatherapp.data.dto.HourlyDto
import com.example.weatherapp.data.dto.HourlyUnitsDto
import com.example.weatherapp.data.dto.WeatherResponseDto
import com.example.weatherapp.domain.entity.CurrentWeather
import com.example.weatherapp.domain.entity.DailyWeather
import com.example.weatherapp.domain.entity.HourlyWeather
import com.example.weatherapp.domain.entity.WeatherResponse
import com.example.weatherapp.presentation.ui.utils.HomeScreenUtils
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun WeatherResponseDto.toWeatherResponse(): WeatherResponse {
    return WeatherResponse(
        current = this.current.toCurrentWeather(this.current_units),
        daily = this.daily.toDailyWeather(this.daily_units),
        hourly = this.hourly.toHourly(this.hourly_units)
    )
}

fun CurrentDto.toCurrentWeather(currentUnitsDto: CurrentUnitsDto): CurrentWeather {
    return CurrentWeather(
        weatherIcon = getIconsByWeatherCode(this.weather_code.toString(), this.is_day),
        weatherStatus = getWeatherStatusByWeatherCode(this.weather_code.toString()),
        feelsLike = "${this.apparent_temperature} ${currentUnitsDto.apparent_temperature}",
        isDay = this.is_day,
        pressure = "${this.pressure_msl} ${currentUnitsDto.pressure_msl}",
        rain = "${this.rain} ${currentUnitsDto.rain}",
        humidity = "${this.relative_humidity_2m} ${currentUnitsDto.relative_humidity_2m}",
        temperature = "${this.temperature_2m} ${currentUnitsDto.temperature_2m}",
        time = this.time,
        uvIndex = "${this.uv_index} ${currentUnitsDto.uv_index}",
        weatherCode = this.weather_code,
        windSpeed = "${this.wind_speed_10m} ${currentUnitsDto.wind_speed_10m}"
    )
}

fun DailyDto.toDailyWeather(dailyUnitsDto: DailyUnitsDto): DailyWeather {
    return DailyWeather(
        weatherIcons = this.weather_code.map { code ->
            getIconsByWeatherCode(code.toString(), 1)
        },
        maxTemperatures = this.temperature_2m_max.map { "$it ${dailyUnitsDto.temperature_2m_max}" },
        minimumTemperatures = this.temperature_2m_min.map { "$it ${dailyUnitsDto.temperature_2m_max}" },
        times = this.time.map { getDayName(it) },
        weatherCodes = this.weather_code
    )
}

fun HourlyDto.toHourly(hourlyUnitsDto: HourlyUnitsDto): HourlyWeather {
    return HourlyWeather(
        temperatures = this.temperature_2m.map { "$it ${hourlyUnitsDto.temperature_2m}" },
        times = this.time.filter { HomeScreenUtils.isToday(it) },
        weatherCodes = this.weather_code,
        isDays = this.is_day,
        weatherIcons = this.weather_code.mapIndexed { index, code ->
            getIconsByWeatherCode(code.toString(), this.is_day[index])
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