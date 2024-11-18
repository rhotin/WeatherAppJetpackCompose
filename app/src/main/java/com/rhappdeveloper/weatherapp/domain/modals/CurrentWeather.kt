package com.rhappdeveloper.weatherapp.domain.modals

import com.rhappdeveloper.weatherapp.utils.WeatherInfoItem

data class CurrentWeather(
    val temperature: Double,
    val time: String,
    val weatherStatus: WeatherInfoItem,
    val windDirection: String,
    val windSpeed: Double,
    val isDay: Boolean
)
