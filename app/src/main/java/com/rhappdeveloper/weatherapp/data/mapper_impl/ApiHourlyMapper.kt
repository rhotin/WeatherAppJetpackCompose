package com.rhappdeveloper.weatherapp.data.mapper_impl

import com.rhappdeveloper.weatherapp.domain.modals.Hourly
import com.rhappdeveloper.weatherapp.data.mappers.ApiMapper
import com.rhappdeveloper.weatherapp.data.remote.models.ApiHourlyWeather
import com.rhappdeveloper.weatherapp.utils.Util
import com.rhappdeveloper.weatherapp.utils.WeatherInfoItem

class ApiHourlyMapper : ApiMapper<Hourly, ApiHourlyWeather> {
    override fun mapToDomain(apiEntity: ApiHourlyWeather): Hourly {
        return Hourly(
            temperature = apiEntity.temperature2m,
            time = parseTime(apiEntity.time),
            weatherStatus = parseWeatherStatus(apiEntity.weatherCode)
        )
    }

    private fun parseTime(time: List<Long>): List<String> {
        return time.map {
            Util.formatUnixData("HH:mm", it)
        }
    }

    private fun parseWeatherStatus(code: List<Int>): List<WeatherInfoItem> {
        return code.map {
            Util.getWeatherInfo(it)
        }
    }
}