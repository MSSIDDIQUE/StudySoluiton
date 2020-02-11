package com.baymax.studysolutions.ui.weather.current

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.baymax.studysolutions.data.repository.ForecastWeatherAppRepo

class CurrentWeatherViewModelFactory(
    private val forecastWeatherAppRepo: ForecastWeatherAppRepo
):ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T: ViewModel?> create(modelClass:Class<T>):T{
        return CurrentWeatherViewModel(forecastWeatherAppRepo)as T
    }
}