package com.example.weatherapp.di

import android.content.Context
import com.example.weatherapp.data.datasource.location.GpsLocation
import com.example.weatherapp.data.datasource.location.LocationServiceManager
import com.example.weatherapp.data.datasource.remote.RemoteDataSource
import com.example.weatherapp.data.datasource.remote.WeatherRemoteDataSourceImpl
import com.example.weatherapp.data.repo.LocationRepositoryImpl
import com.example.weatherapp.data.repo.WeatherRepositoryImpl
import com.example.weatherapp.domain.repo.LocationRepository
import com.example.weatherapp.domain.repo.WeatherRepository
import com.example.weatherapp.domain.usecase.GetWeatherUseCase
import com.example.weatherapp.presentation.ui.screens.HomeScreenViewModel
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single { HttpClient(CIO) }

    single<RemoteDataSource> { WeatherRemoteDataSourceImpl(get()) }
    single<WeatherRepository> { WeatherRepositoryImpl(get()) }
    factory<LocationRepository> { LocationRepositoryImpl(get()) }
    single<GetWeatherUseCase> { GetWeatherUseCase(get()) }

    factory { GetWeatherUseCase(get()) }

    single<FusedLocationProviderClient> {
        LocationServices.getFusedLocationProviderClient(get<Context>())
    }
    single<LocationServiceManager> { GpsLocation(get(), get()) }

    viewModel { HomeScreenViewModel(get(), get()) }
}