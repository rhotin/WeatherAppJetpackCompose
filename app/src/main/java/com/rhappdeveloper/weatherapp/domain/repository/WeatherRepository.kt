package com.rhappdeveloper.weatherapp.domain.repository

import com.rhappdeveloper.weatherapp.domain.modals.Weather
import com.rhappdeveloper.weatherapp.utils.Response
import kotlinx.coroutines.flow.Flow

interface WeatherRepository {
    fun getWeatherData(): Flow<Response<Weather>>
}