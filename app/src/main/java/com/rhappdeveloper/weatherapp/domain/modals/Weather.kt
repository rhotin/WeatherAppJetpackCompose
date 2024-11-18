package com.rhappdeveloper.weatherapp.domain.modals

data class Weather(
    val currentWeather: CurrentWeather,
    val hourly: Hourly,
    val daily: Daily
)
