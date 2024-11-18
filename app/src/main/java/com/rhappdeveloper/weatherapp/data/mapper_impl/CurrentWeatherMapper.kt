package com.rhappdeveloper.weatherapp.data.mapper_impl

import com.rhappdeveloper.weatherapp.domain.modals.CurrentWeather
import com.rhappdeveloper.weatherapp.data.mappers.ApiMapper
import com.rhappdeveloper.weatherapp.data.remote.models.ApiCurrentWeather
import com.rhappdeveloper.weatherapp.utils.Util
import com.rhappdeveloper.weatherapp.utils.WeatherInfoItem

class CurrentWeatherMapper : ApiMapper<CurrentWeather, ApiCurrentWeather> {
    override fun mapToDomain(apiEntity: ApiCurrentWeather): CurrentWeather {
        return CurrentWeather(
            temperature = apiEntity.temperature2m,
            time = parseTime(apiEntity.time),
            weatherStatus = parseWeatherStatus(apiEntity.weatherCode),
            windDirection = parseWindDirection(apiEntity.windDirection10m),
            windSpeed = apiEntity.windSpeed10m,
            isDay = apiEntity.isDay == 1
        )
    }

    private fun parseTime(time: Long): String {
        return Util.formatUnixData("MMM,d", time)
    }

    private fun parseWeatherStatus(code: Int): WeatherInfoItem {
        return Util.getWeatherInfo(code)
    }

    private fun parseWindDirection(direction: Double): String {
        return Util.getWindDirection(direction)
    }

}