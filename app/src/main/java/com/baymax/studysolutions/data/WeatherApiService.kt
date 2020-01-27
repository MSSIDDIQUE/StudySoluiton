package com.baymax.studysolutions.data

import android.util.Log
import com.baymax.studysolutions.data.firebase.CurrentWeatherResponse
import com.baymax.studysolutions.data.firebase.Location
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.Deferred
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query


const val API_KEY = "51adea8df64ca3552c104d42f74c93b1"

interface WeatherApiService {
    @GET("current")
    fun getCurrentWeather(
        @Query("query")location: String
        //@Query("lang") languageCode: String = "en"
    ): Deferred<CurrentWeatherResponse>


    companion object {
        operator fun invoke(
        ): WeatherApiService {
            val requestInterceptor = Interceptor { chain ->

                Log.d("(Saquib)",chain.request().url().toString())
                val url = chain.request()
                    .url()
                    .newBuilder()
                    .addQueryParameter("access_key", API_KEY)
                    .build()
                Log.d("(Saquib)",url.toString())
                val request = chain.request()
                    .newBuilder()
                    .url(url)
                    .build()

                return@Interceptor chain.proceed(request)
            }

            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(requestInterceptor)
                .build()

            return Retrofit.Builder()
                .baseUrl("http://api.weatherstack.com/")
                .client(okHttpClient)
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(WeatherApiService::class.java)
        }
    }
}