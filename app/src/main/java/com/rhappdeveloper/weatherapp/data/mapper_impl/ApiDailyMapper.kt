package com.rhappdeveloper.weatherapp.data.mapper_impl

import com.rhappdeveloper.weatherapp.domain.modals.Daily
import com.rhappdeveloper.weatherapp.data.mappers.ApiMapper
import com.rhappdeveloper.weatherapp.data.remote.models.ApiDailyWeather
import com.rhappdeveloper.weatherapp.utils.Util
import com.rhappdeveloper.weatherapp.utils.WeatherInfoItem

class ApiDailyMapper : ApiMapper<Daily, ApiDailyWeather> {
    override fun mapToDomain(apiEntity: ApiDailyWeather): Daily {
        return Daily(
            temperatureMax = apiEntity.temperature2mMax,
            temperatureMin = apiEntity.temperature2mMin,
            time = parseTime(apiEntity.time),
            weatherStatus = parseWeatherStatus(apiEntity.weatherCode),
            windDirection = parseWindDirection(apiEntity.windDirection10mDominant),
            windSpeed = apiEntity.windSpeed10mMax,
            sunrise = apiEntity.sunrise.map { Util.formatUnixData("HH:mm", it.toLong()) },
            sunset = apiEntity.sunset.map { Util.formatUnixData("HH:mm", it.toLong()) },
            uvIndex = apiEntity.uvIndexMax
        )
    }

    private fun parseTime(time: List<Long>): List<String> {
        return time.map { Util.formatUnixData("E", it) }
    }

    private fun parseWeatherStatus(code: List<Int>): List<WeatherInfoItem> {
        return code.map {
            Util.getWeatherInfo(it)
        }
    }

    private fun parseWindDirection(direction: List<Double>): List<String> {
        return direction.map {
            Util.getWindDirection(it)
        }
    }

}