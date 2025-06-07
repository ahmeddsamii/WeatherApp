package com.example.weatherapp.data.datasource

import com.example.weatherapp.data.dto.CityLocationDto
import com.example.weatherapp.data.dto.WeatherResponseDto
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText
import io.ktor.http.URLProtocol
import io.ktor.http.path
import kotlinx.serialization.json.Json

class RemoteDataSourceImpl(
    private val ktorClient: HttpClient
) : RemoteDataSource {

    private val json = Json {
        ignoreUnknownKeys = true
        isLenient = true
    }

    override suspend fun getWeatherByLocation(cityLocation: CityLocationDto): WeatherResponseDto {
        val response = ktorClient.get {
            url {
                protocol = URLProtocol.HTTPS
                host = ApiConstants.WEATHER_HOST
                path(ApiConstants.WEATHER_PATH)

                parameters.append("latitude", cityLocation.latitude.toString())
                parameters.append("longitude", cityLocation.longitude.toString())
                parameters.append("current", ApiConstants.CURRENT_PARAMS)
                parameters.append("daily", ApiConstants.DAILY_PARAMS)
                parameters.append("hourly", ApiConstants.HOURLY_PARAMS)
                parameters.append("timezone", ApiConstants.TIMEZONE)
            }
        }


        return json.decodeFromString<WeatherResponseDto>(response.bodyAsText())
    }
}