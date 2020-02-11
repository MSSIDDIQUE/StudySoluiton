package com.baymax.studysolutions

import android.app.Application
import com.baymax.studysolutions.data.Repository
import com.baymax.studysolutions.data.db.ForecastDb
import com.baymax.studysolutions.data.firebase.Login
import com.baymax.studysolutions.data.network.*
import com.baymax.studysolutions.data.repository.ForecastWeatherAppRepo
import com.baymax.studysolutions.data.repository.ForecastWeatherAppRepoImpl
import com.baymax.studysolutions.ui.auth.AuthViewModelFactory
import com.baymax.studysolutions.ui.weather.current.CurrentWeatherViewModel
import com.baymax.studysolutions.ui.weather.current.CurrentWeatherViewModelFactory
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

class ForecastApplication() :Application(),KodeinAware {
    override val kodein = Kodein.lazy {
        import(androidXModule(this@ForecastApplication))
        bind() from singleton { Login() }
        bind() from singleton { Repository(instance()) }
        bind() from provider { AuthViewModelFactory(instance()) }
        bind() from singleton {ForecastDb(instance())}
        bind() from singleton { instance<ForecastDb>().currentWeatherDao() }
        bind<ConnectivityInterceptor>() with singleton { ConnectivityInterceptorImpl(instance()) }
        bind() from singleton { WeatherApiService(instance()) }
        bind<WeatherNetworkDataSource>() with singleton { WeatherNetworkDataSourceImpl(instance()) }
        bind<ForecastWeatherAppRepo>()with singleton { ForecastWeatherAppRepoImpl(instance(), instance()) }
        bind() from provider { CurrentWeatherViewModelFactory(instance()) }
    }
}