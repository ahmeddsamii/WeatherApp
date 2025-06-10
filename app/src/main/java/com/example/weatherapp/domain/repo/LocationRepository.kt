package com.example.weatherapp.domain.repo

import android.location.Location

interface LocationRepository {
    fun getLocation(
        onSuccess: (Location, String) -> Unit,
        onError: (String) -> Unit
    )
}