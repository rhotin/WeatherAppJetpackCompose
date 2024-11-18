package com.rhappdeveloper.weatherapp.ui.daily

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.rhappdeveloper.weatherapp.R
import com.rhappdeveloper.weatherapp.domain.modals.Daily
import com.rhappdeveloper.weatherapp.ui.home.SunSetWeatherItem
import com.rhappdeveloper.weatherapp.ui.home.UvIndexWeatherItem
import com.rhappdeveloper.weatherapp.ui.home.degreesTxt

@Composable
fun DailyScreen(
    modifier: Modifier = Modifier,
    dailyViewModel: DailyViewModel = hiltViewModel()
) {
    val dailyState = dailyViewModel.dailyState
    var selectedWeatherIndex by remember { mutableStateOf(0) }
    val currentDailyWeatherItem = remember(
        key1 = selectedWeatherIndex,
        key2 = dailyState
    ) {
        dailyState.daily?.weatherInfo?.get(selectedWeatherIndex)
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        when (dailyState.isLoading) {
            true -> {
                CircularProgressIndicator()
            }

            else -> {
                currentDailyWeatherItem?.let {
                    Text(
                        text = "Max ${it.temperatureMax} Min ${it.temperatureMin}",
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "7 Days Forecast",
                    style = MaterialTheme.typography.headlineSmall
                )
                Spacer(modifier = Modifier.height(8.dp))
                LazyRow {
                    dailyState.daily?.let { daily ->
                        itemsIndexed(items = daily.weatherInfo) { index, weatherInfo ->
                            val selectedColor =
                                if (selectedWeatherIndex == index) MaterialTheme.colorScheme.inverseSurface
                                else CardDefaults.cardColors().containerColor
                            DailyWeatherInfoItem(
                                weatherInfo = weatherInfo,
                                backgroundColor = selectedColor,
                                onClick = {
                                    selectedWeatherIndex = index
                                }
                            )
                        }
                    }
                }
                Spacer(modifier = Modifier.height(8.dp))
                currentDailyWeatherItem?.let {
                    Card {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                        ) {
                            Row(
                                horizontalArrangement = Arrangement.Center,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(
                                    painter = painterResource(id = R.drawable.wind_ic),
                                    contentDescription = null,
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(
                                    text = "Wind",
                                    style = MaterialTheme.typography.bodyMedium
                                )
                            }
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(
                                text = it.windSpeed.toString() + "km/h " + it.windDirection,
                                style = MaterialTheme.typography.headlineMedium,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        SunSetWeatherItem(weatherInfo = it)
                        UvIndexWeatherItem(weatherInfo = it)
                    }
                }
            }
        }
    }
}

@Composable
fun DailyWeatherInfoItem(
    modifier: Modifier = Modifier,
    weatherInfo: Daily.WeatherInfo,
    backgroundColor: Color,
    onClick: () -> Unit
) {
    Card(
        onClick = onClick,
        modifier = modifier.padding(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = backgroundColor
        )
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "${weatherInfo.temperatureMax} $degreesTxt",
                style = MaterialTheme.typography.bodySmall
            )
            Spacer(modifier = Modifier.height(8.dp))
            Icon(
                painter = painterResource(id = weatherInfo.weatherStatus.icon),
                contentDescription = null,
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = weatherInfo.time,
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}