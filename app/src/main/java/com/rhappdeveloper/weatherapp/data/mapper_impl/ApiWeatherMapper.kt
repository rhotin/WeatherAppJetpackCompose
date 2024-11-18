package com.rhappdeveloper.weatherapp.data.mapper_impl

import com.rhappdeveloper.weatherapp.di.ApiCurrentWeatherMapperAnnotation
import com.rhappdeveloper.weatherapp.di.ApiDailyMapperAnnotation
import com.rhappdeveloper.weatherapp.di.ApiHourlyMapperAnnotation
import com.rhappdeveloper.weatherapp.domain.modals.CurrentWeather
import com.rhappdeveloper.weatherapp.domain.modals.Daily
import com.rhappdeveloper.weatherapp.domain.modals.Hourly
import com.rhappdeveloper.weatherapp.domain.modals.Weather
import com.rhappdeveloper.weatherapp.data.mappers.ApiMapper
import com.rhappdeveloper.weatherapp.data.remote.models.ApiCurrentWeather
import com.rhappdeveloper.weatherapp.data.remote.models.ApiDailyWeather
import com.rhappdeveloper.weatherapp.data.remote.models.ApiHourlyWeather
import com.rhappdeveloper.weatherapp.data.remote.models.ApiWeather
import javax.inject.Inject

class ApiWeatherMapper @Inject constructor(
    @ApiDailyMapperAnnotation private val apiDailyMapper: ApiMapper<Daily, ApiDailyWeather>,
    @ApiHourlyMapperAnnotation private val apiHourlyMapper: ApiMapper<Hourly, ApiHourlyWeather>,
    @ApiCurrentWeatherMapperAnnotation private val apiCurrentWeatherMapper: ApiMapper<CurrentWeather, ApiCurrentWeather>
) : ApiMapper<Weather, ApiWeather> {
    override fun mapToDomain(apiEntity: ApiWeather): Weather {
        return Weather(
            currentWeather = apiCurrentWeatherMapper.mapToDomain(apiEntity.current),
            hourly = apiHourlyMapper.mapToDomain(apiEntity.hourly),
            daily = apiDailyMapper.mapToDomain(apiEntity.daily)
        )
    }
}