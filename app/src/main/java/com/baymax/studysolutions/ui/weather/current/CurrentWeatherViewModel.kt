package com.baymax.studysolutions.ui.weather.current

import androidx.lifecycle.ViewModel
import com.baymax.studysolutions.data.repository.ForecastWeatherAppRepo
import com.baymax.studysolutions.utils.lazyDeferred

class CurrentWeatherViewModel(private val repo: ForecastWeatherAppRepo):ViewModel() {
    val weather by lazyDeferred {
        repo.getCurrentWeather()
    }
}