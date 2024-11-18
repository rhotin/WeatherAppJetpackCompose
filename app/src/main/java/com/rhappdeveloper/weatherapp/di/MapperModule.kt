package com.rhappdeveloper.weatherapp.di

import com.rhappdeveloper.weatherapp.domain.modals.CurrentWeather
import com.rhappdeveloper.weatherapp.domain.modals.Daily
import com.rhappdeveloper.weatherapp.domain.modals.Hourly
import com.rhappdeveloper.weatherapp.domain.modals.Weather
import com.rhappdeveloper.weatherapp.data.mapper_impl.ApiDailyMapper
import com.rhappdeveloper.weatherapp.data.mapper_impl.ApiHourlyMapper
import com.rhappdeveloper.weatherapp.data.mapper_impl.ApiWeatherMapper
import com.rhappdeveloper.weatherapp.data.mapper_impl.CurrentWeatherMapper
import com.rhappdeveloper.weatherapp.data.mappers.ApiMapper
import com.rhappdeveloper.weatherapp.data.remote.models.ApiCurrentWeather
import com.rhappdeveloper.weatherapp.data.remote.models.ApiDailyWeather
import com.rhappdeveloper.weatherapp.data.remote.models.ApiHourlyWeather
import com.rhappdeveloper.weatherapp.data.remote.models.ApiWeather
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Qualifier

@Module
@InstallIn(SingletonComponent::class)
object MapperModule {

    @ApiDailyMapperAnnotation
    @Provides
    fun provideDailyMapper(): ApiMapper<Daily, ApiDailyWeather> {
        return ApiDailyMapper()
    }

    @ApiCurrentWeatherMapperAnnotation
    @Provides
    fun provideCurrentWeatherMapper(): ApiMapper<CurrentWeather, ApiCurrentWeather> {
        return CurrentWeatherMapper()
    }

    @ApiHourlyMapperAnnotation
    @Provides
    fun provideHourlyWeatherMapper(): ApiMapper<Hourly, ApiHourlyWeather> {
        return ApiHourlyMapper()
    }

    @ApiWeatherMapperAnnotation
    @Provides
    fun provideApiWeatherMapper(
        apiDailyMapper: ApiMapper<Daily, ApiDailyWeather>,
        apiHourlyMapper: ApiMapper<Hourly, ApiHourlyWeather>,
        apiCurrentWeatherMapper: ApiMapper<CurrentWeather, ApiCurrentWeather>
    ): ApiMapper<Weather, ApiWeather> {
        return ApiWeatherMapper(
            apiDailyMapper,
            apiHourlyMapper,
            apiCurrentWeatherMapper
        )
    }


}

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class ApiDailyMapperAnnotation

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class ApiWeatherMapperAnnotation

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class ApiHourlyMapperAnnotation

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class ApiCurrentWeatherMapperAnnotation