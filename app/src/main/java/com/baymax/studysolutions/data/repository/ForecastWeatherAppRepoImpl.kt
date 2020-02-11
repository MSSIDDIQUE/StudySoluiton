package com.baymax.studysolutions.data.repository

import androidx.lifecycle.LiveData
import com.baymax.studysolutions.data.CurrentWeatherDao
import com.baymax.studysolutions.data.db.entity.CurrentWeatherEntry
import com.baymax.studysolutions.data.network.WeatherNetworkDataSource
import com.baymax.studysolutions.data.network.response.CurrentWeatherResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.time.ZonedDateTime
import java.util.*

class ForecastWeatherAppRepoImpl(
    private val currentWeatherDao: CurrentWeatherDao,
    private val weatherNetworkDataSource: WeatherNetworkDataSource
) : ForecastWeatherAppRepo {

    init{
        weatherNetworkDataSource.downloadedCurrentWeather.observeForever{
            persistFetchedCurrentWeather(it)
        }
    }
    override suspend fun getCurrentWeather(): LiveData<CurrentWeatherEntry> {
        return withContext(Dispatchers.IO){
            initCurrentWeatherData()
            return@withContext currentWeatherDao.getWeather()
        }
    }

    private fun persistFetchedCurrentWeather(fetchedCurrentWeather:CurrentWeatherResponse) {
        GlobalScope.launch {
            currentWeatherDao.upsert(fetchedCurrentWeather.currentWeatherEntry)
        }
    }

    private suspend fun initCurrentWeatherData(){
        if(android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O && isFetchNeeded(ZonedDateTime.now().minusHours(1))){
                fetchCurrentWeather()
            }

    }

    private fun isFetchNeeded(lastFetchTime:ZonedDateTime):Boolean{
        if(android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            val thirtyMinutesAgo = ZonedDateTime.now().minusMinutes(30)
            return lastFetchTime.isBefore(thirtyMinutesAgo)
        }
        return false
    }

    private suspend fun fetchCurrentWeather(){
        weatherNetworkDataSource.fetchCurrentWeather("New York",
            Locale.getDefault().language)
    }
}