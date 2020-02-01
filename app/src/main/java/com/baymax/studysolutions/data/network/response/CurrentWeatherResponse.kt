package com.baymax.studysolutions.data.network.response


import com.baymax.studysolutions.data.db.entity.CurrentWeatherEntry
import com.baymax.studysolutions.data.db.entity.Location
import com.google.gson.annotations.SerializedName

data class CurrentWeatherResponse(
    @SerializedName("current")
    val currentWeatherEntry: CurrentWeatherEntry,
    val location: Location
)