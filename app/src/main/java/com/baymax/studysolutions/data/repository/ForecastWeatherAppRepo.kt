package com.baymax.studysolutions.data.repository

import androidx.lifecycle.LiveData
import com.baymax.studysolutions.data.db.entity.CurrentWeatherEntry

interface ForecastWeatherAppRepo {
    suspend fun getCurrentWeather(): LiveData<CurrentWeatherEntry>
}