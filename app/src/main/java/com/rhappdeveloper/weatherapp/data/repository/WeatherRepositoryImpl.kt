package com.rhappdeveloper.weatherapp.data.repository

import com.rhappdeveloper.weatherapp.data.mapper_impl.ApiWeatherMapper
import com.rhappdeveloper.weatherapp.data.remote.WeatherApi
import com.rhappdeveloper.weatherapp.domain.modals.Weather
import com.rhappdeveloper.weatherapp.domain.repository.WeatherRepository
import com.rhappdeveloper.weatherapp.utils.Response
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val weatherApi: WeatherApi,
    private val apiWeatherMapper: ApiWeatherMapper
) : WeatherRepository {
    override fun getWeatherData(): Flow<Response<Weather>> = flow {
        emit(Response.Loading())
        val apiWeather = weatherApi.getWeatherData()
        val weather = apiWeatherMapper.mapToDomain(apiWeather)
        emit(Response.Success(weather))
    }.catch { e ->
        e.printStackTrace()
        emit(Response.Error(e.message.orEmpty()))
    }
}