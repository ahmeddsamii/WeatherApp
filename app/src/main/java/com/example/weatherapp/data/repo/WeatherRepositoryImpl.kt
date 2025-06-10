package com.example.weatherapp.data.repo

import com.example.weatherapp.data.datasource.remote.RemoteDataSource
import com.example.weatherapp.domain.entity.CityLocation
import com.example.weatherapp.domain.entity.WeatherResponse
import com.example.weatherapp.domain.repo.WeatherRepository
import com.example.weatherapp.data.repo.mapper.toWeatherResponse
import com.example.weatherapp.domain.utils.FetchingWeatherException
import com.example.weatherapp.domain.utils.UnexpectedErrorException
import com.example.weatherapp.domain.utils.WeatherNetworkException
import io.ktor.util.network.UnresolvedAddressException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.serialization.SerializationException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

class WeatherRepositoryImpl(
    private val remoteDataSource: RemoteDataSource
) : WeatherRepository {
    override fun getWeatherByLocation(cityLocation: CityLocation): Flow<WeatherResponse> {
        return flow {
            wrapCallWithTry {
                emit(
                    remoteDataSource.getWeatherByLocation(cityLocation).toWeatherResponse()
                )
            }
        }
    }
}

private suspend fun <T> wrapCallWithTry(call: suspend () -> T): T {
    try {
        return call()
    } catch (exception: Exception) {
        throw when (exception) {
            is ConnectException,
            is UnknownHostException,
            is UnresolvedAddressException,
            is SocketTimeoutException -> WeatherNetworkException()

            is NullPointerException,
            is SerializationException -> FetchingWeatherException()

            else -> UnexpectedErrorException()
        }
    }
}