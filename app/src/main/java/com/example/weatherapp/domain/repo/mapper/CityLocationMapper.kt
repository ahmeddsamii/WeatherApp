package com.example.weatherapp.domain.repo.mapper

import com.example.weatherapp.data.dto.CityLocationDto
import com.example.weatherapp.domain.entity.CityLocation

fun CityLocationDto.toCityLocation(): CityLocation{
    return CityLocation(
        latitude = this.latitude,
        longitude = this.longitude
    )
}

fun CityLocation.toCityLocationDto(): CityLocationDto{
    return CityLocationDto(
        latitude = this.latitude,
        longitude = this.longitude
    )
}