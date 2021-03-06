package com.baymax.studysolutions.data.network

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.baymax.studysolutions.data.network.response.CurrentWeatherResponse
import com.baymax.studysolutions.utils.exceptions.NoConnectivityException

class WeatherNetworkDataSourceImpl(
    private val weatherApiService:WeatherApiService
) : WeatherNetworkDataSource {
    private val _downloadedCurrentWeather = MutableLiveData<CurrentWeatherResponse>()
    override val downloadedCurrentWeather: LiveData<CurrentWeatherResponse>
        get() = _downloadedCurrentWeather

    override suspend fun fetchCurrentWeather(location: String, language: String) {
        try {
            val fetchedCurrentWeather = weatherApiService.getCurrentWeather(location).await()
            _downloadedCurrentWeather.postValue(fetchedCurrentWeather)
        }
        catch (e:NoConnectivityException){
            Log.e("Connectivity","No internet connection.",e)
        }
    }
}