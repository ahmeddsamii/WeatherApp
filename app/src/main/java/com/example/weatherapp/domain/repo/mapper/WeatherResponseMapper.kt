package com.example.weatherapp.domain.repo.mapper

import com.example.weatherapp.data.dto.CurrentDto
import com.example.weatherapp.data.dto.CurrentUnitsDto
import com.example.weatherapp.data.dto.DailyDto
import com.example.weatherapp.data.dto.DailyUnitsDto
import com.example.weatherapp.data.dto.HourlyDto
import com.example.weatherapp.data.dto.HourlyUnitsDto
import com.example.weatherapp.data.dto.WeatherResponseDto
import com.example.weatherapp.domain.entity.Current
import com.example.weatherapp.domain.entity.CurrentUnits
import com.example.weatherapp.domain.entity.Daily
import com.example.weatherapp.domain.entity.DailyUnits
import com.example.weatherapp.domain.entity.Hourly
import com.example.weatherapp.domain.entity.HourlyUnits
import com.example.weatherapp.domain.entity.WeatherResponse


fun WeatherResponseDto.toWeatherResponse(): WeatherResponse {
    return WeatherResponse(
        current = this.current.toCurrent(),
        current_units = this.current_units.toCurrentUnit(),
        daily = this.daily.toDaily(),
        daily_units = this.daily_units.toDailyUnits(),
        elevation = this.elevation,
        generationtime_ms = this.generationtime_ms,
        latitude = this.latitude,
        longitude = this.longitude,
        timezone = this.timezone,
        timezone_abbreviation = this.timezone_abbreviation,
        utc_offset_seconds = this.utc_offset_seconds,
        hourly_units = this.hourly_units.toHourlyUnits(),
        hourly = this.hourly.toHourly(),
    )
}

fun CurrentDto.toCurrent(): Current {
    return Current(
        apparent_temperature = this.apparent_temperature,
        is_day = this.is_day,
        pressure_msl = this.pressure_msl,
        relative_humidity_2m = this.relative_humidity_2m,
        temperature_2m = this.temperature_2m,
        uv_index = this.uv_index,
        weather_code = this.weather_code,
        wind_speed_10m = this.wind_speed_10m,
        rain = this.rain
    )
}

fun CurrentUnitsDto.toCurrentUnit(): CurrentUnits {
    return CurrentUnits(
        apparent_temperature = this.apparent_temperature,
        is_day = this.is_day,
        pressure_msl = this.pressure_msl,
        relative_humidity_2m = this.relative_humidity_2m,
        temperature_2m = this.temperature_2m,
        uv_index = this.uv_index,
        weather_code = this.weather_code,
        wind_speed_10m = this.wind_speed_10m,
        rain = this.rain
    )
}

fun DailyDto.toDaily(): Daily {
    return Daily(
        temperature_2m_max = this.temperature_2m_max,
        temperature_2m_min = this.temperature_2m_min,
        time = this.time,
        weather_code = this.weather_code
    )
}

fun DailyUnitsDto.toDailyUnits(): DailyUnits {
    return DailyUnits(
        temperature_2m_max = this.temperature_2m_max,
        temperature_2m_min = this.temperature_2m_min,
        time = this.time,
        weather_code = this.weather_code
    )
}

fun HourlyDto.toHourly(): Hourly {
    return Hourly(
        time = this.time,
        temperature_2m = this.temperature_2m,
        weather_code = this.weather_code,
        is_day = this.is_day,
    )
}

fun HourlyUnitsDto.toHourlyUnits(): HourlyUnits {
    return HourlyUnits(
        time = this.time,
        temperature_2m = this.temperature_2m,
        weather_code = this.weather_code,
        is_day = this.is_day,
    )
}