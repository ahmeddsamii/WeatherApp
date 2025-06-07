package com.example.weatherapp.di

import com.example.weatherapp.data.datasource.RemoteDataSource
import com.example.weatherapp.data.datasource.RemoteDataSourceImpl
import com.example.weatherapp.data.repo.WeatherRepositoryImpl
import com.example.weatherapp.domain.repo.WeatherRepository
import com.example.weatherapp.domain.usecase.GetWeatherUseCase
import com.example.weatherapp.presentation.ui.screens.HomeScreenViewModel
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single { HttpClient(CIO) }

    single<RemoteDataSource> { RemoteDataSourceImpl(get()) }
    single<WeatherRepository> { WeatherRepositoryImpl(get()) }
    single<GetWeatherUseCase> { GetWeatherUseCase(get()) }

    // Provide UseCase
    factory { GetWeatherUseCase(get()) }

    // Provide ViewModel
    viewModel { HomeScreenViewModel(get()) }
}