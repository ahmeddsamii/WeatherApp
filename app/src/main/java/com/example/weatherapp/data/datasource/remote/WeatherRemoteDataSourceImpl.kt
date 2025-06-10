package com.example.weatherapp.data.datasource.remote

import com.example.weatherapp.data.dto.WeatherResponseDto
import com.example.weatherapp.domain.entity.CityLocation
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText
import io.ktor.http.URLProtocol
import io.ktor.http.path
import kotlinx.serialization.json.Json

class WeatherRemoteDataSourceImpl(
    private val ktorClient: HttpClient
) : RemoteDataSource {
    override suspend fun getWeatherByLocation(cityLocation: CityLocation): WeatherResponseDto {
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

        return Json.decodeFromString<WeatherResponseDto>(response.bodyAsText())
    }
}