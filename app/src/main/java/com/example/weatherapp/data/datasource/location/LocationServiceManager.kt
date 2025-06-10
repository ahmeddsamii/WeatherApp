package com.example.weatherapp.data.datasource.location

import android.location.Location

interface LocationServiceManager {
    fun getLocation(
        onSuccess: (Location, String) -> Unit,
        onError: (String) -> Unit
    )
}