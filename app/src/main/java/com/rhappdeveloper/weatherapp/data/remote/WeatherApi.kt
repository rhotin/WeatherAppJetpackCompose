package com.rhappdeveloper.weatherapp.data.remote

import com.rhappdeveloper.weatherapp.data.remote.models.ApiWeather
import com.rhappdeveloper.weatherapp.utils.ApiParameters
import com.rhappdeveloper.weatherapp.utils.K
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {

    @GET(K.END_POINT)
    suspend fun getWeatherData(
        @Query(ApiParameters.LATITUDE) latitude: Float = -6.8f,
        @Query(ApiParameters.LONGITUDE) longitude: Float = 39.28f,
        @Query(ApiParameters.DAILY) daily: Array<String> = arrayOf(
            "weather_code",
            "temperature_2m_max",
            "temperature_2m_min",
            "wind_speed_10m_max",
            "wind_direction_10m_dominant",
            "sunrise",
            "sunset",
            "uv_index_max",
        ),
        @Query(ApiParameters.CURRENT_WEATHER) currentWeather: Array<String> = arrayOf(
            "temperature_2m",
            "is_day",
            "weather_code",
            "wind_speed_10m",
            "wind_direction_10m",
        ),
        @Query(ApiParameters.HOURLY) hourly: Array<String> = arrayOf(
            "weather_code",
            "temperature_2m",
        ),
        @Query(ApiParameters.TIME_FORMAT) timeFormat: String = "unixtime",
        @Query(ApiParameters.TIMEZONE) timezone: String = "America/Toronto",
    ): ApiWeather

}