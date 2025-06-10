package com.example.weatherapp.data.repo.mapper

import com.example.weatherapp.data.dto.CurrentDto
import com.example.weatherapp.data.dto.CurrentUnitsDto
import com.example.weatherapp.data.dto.DailyDto
import com.example.weatherapp.data.dto.DailyUnitsDto
import com.example.weatherapp.data.dto.HourlyDto
import com.example.weatherapp.data.dto.HourlyUnitsDto
import com.example.weatherapp.data.dto.WeatherResponseDto
import com.example.weatherapp.domain.entity.CurrentUnits
import com.example.weatherapp.domain.entity.CurrentWeather
import com.example.weatherapp.domain.entity.DailyWeather
import com.example.weatherapp.domain.entity.DailyWeatherUnits
import com.example.weatherapp.domain.entity.HourlyWeather
import com.example.weatherapp.domain.entity.HourlyWeatherUnits
import com.example.weatherapp.domain.entity.WeatherResponse

fun WeatherResponseDto.toWeatherResponse(): WeatherResponse {
    return WeatherResponse(
        current = this.current.toCurrentWeather(),
        currentUnits = this.current_units.toCurrentUnit(),
        daily = this.daily.toDailyWeather(),
        dailyUnits = this.daily_units.toDailyWeatherUnits(),
        hourly = this.hourly.toHourly(),
        hourlyUnits = this.hourly_units.toHourlyWeatherUnits()
    )
}

fun CurrentDto.toCurrentWeather(): CurrentWeather {
    return CurrentWeather(
        feelsLike = this.apparent_temperature,
        isDay = this.is_day,
        pressure = this.pressure_msl,
        rain = this.precipitation_probability,
        humidity = this.relative_humidity_2m,
        temperature = this.temperature_2m,
        time = this.time,
        uvIndex = this.uv_index,
        weatherCode = this.weather_code,
        windSpeed = this.wind_speed_10m
    )
}

fun CurrentUnitsDto.toCurrentUnit(): CurrentUnits {
    return CurrentUnits(
        feelsLike = this.apparent_temperature,
        isDay = this.is_day,
        pressure = this.pressure_msl,
        rain = this.precipitation_probability,
        humidity = this.relative_humidity_2m,
        temperature = this.temperature_2m,
        time = this.time,
        uvIndex = this.uv_index,
        windSpeed = this.wind_speed_10m
    )
}

fun DailyDto.toDailyWeather(): DailyWeather {
    return DailyWeather(
        maxTemperatures = this.temperature_2m_max,
        minimumTemperatures = this.temperature_2m_min,
        times = this.time,
        weatherCodes = this.weather_code
    )
}

fun DailyUnitsDto.toDailyWeatherUnits(): DailyWeatherUnits {
    return DailyWeatherUnits(
        rain = this.rain_sum,
        maxTemperatures = this.temperature_2m_max,
        minimumTemperatures = this.temperature_2m_min,
        time = this.time,
    )
}

fun HourlyDto.toHourly(): HourlyWeather {
    return HourlyWeather(
        temperatures = this.temperature_2m,
        times = this.time,
        weatherCodes = this.weather_code,
        isDays = this.is_day,
    )
}

fun HourlyUnitsDto.toHourlyWeatherUnits(): HourlyWeatherUnits {
    return HourlyWeatherUnits(
        temperature = this.temperature_2m
    )
}