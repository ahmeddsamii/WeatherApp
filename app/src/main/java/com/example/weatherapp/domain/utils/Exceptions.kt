package com.example.weatherapp.domain.utils

open class WeatherException(message: String) : Exception(message)
class WeatherNetworkException : WeatherException("Network unavailable! Please check your network.")
class FetchingWeatherException : WeatherException("Can't fetch this city's weather! Please try again.")
class UnexpectedErrorException : WeatherException("Unexpected error happened! Please try again.")