package com.example.weatherapp.data.repo

import android.location.Location
import com.example.weatherapp.data.datasource.location.LocationServiceManager
import com.example.weatherapp.domain.repo.LocationRepository

class LocationRepositoryImpl(
    private val locationServiceManager: LocationServiceManager
) : LocationRepository {
    override fun getLocation(
        onSuccess: (Location, String) -> Unit,
        onError: (String) -> Unit
    ) {
        locationServiceManager.getLocation(onSuccess, onError)
    }
}