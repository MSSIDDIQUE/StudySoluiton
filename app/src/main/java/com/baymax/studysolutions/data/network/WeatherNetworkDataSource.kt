package com.baymax.studysolutions.data.network

import android.view.textclassifier.TextLanguage
import androidx.lifecycle.LiveData
import com.baymax.studysolutions.data.network.response.CurrentWeatherResponse

interface WeatherNetworkDataSource {
    val downloadedCurrentWeather:LiveData<CurrentWeatherResponse>
    suspend fun fetchCurrentWeather(
        location:String,
        language: String
    )
}