package com.example.weatherapp.presentation.ui.screens

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weatherapp.R
import com.example.weatherapp.presentation.state.WeatherResponseState
import com.example.weatherapp.presentation.ui.composables.LocationText
import com.example.weatherapp.presentation.ui.composables.NextSevenDaysSection
import com.example.weatherapp.presentation.ui.composables.TodaySection
import com.example.weatherapp.presentation.ui.composables.WeatherInfoCard
import com.example.weatherapp.presentation.ui.composables.WeatherInformation
import com.example.weatherapp.presentation.ui.utils.HomeScreenUtils.getNextSevenDaysDate
import com.example.weatherapp.presentation.ui.utils.HomeScreenUtils.getNextSevenDaysMaxTemperatures
import com.example.weatherapp.presentation.ui.utils.HomeScreenUtils.getNextSevenDaysMinimumTemperatures
import com.example.weatherapp.presentation.ui.utils.HomeScreenUtils.getWeatherInfoCardInformation
import com.example.weatherapp.presentation.ui.utils.HomeScreenUtils.splitTodaySectionTime
import org.koin.androidx.compose.koinViewModel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun WeatherHomeScreen(
    viewModel: HomeScreenViewModel = koinViewModel()
) {
    val weatherState by viewModel.state.collectAsState()

    when (weatherState) {
        is WeatherResponseState.onError -> {}
        WeatherResponseState.onLoading -> {}
        is WeatherResponseState.onSuccess -> {
            val weather = (weatherState as WeatherResponseState.onSuccess).weatherResponse
            val currentIsDayHour = weather.current.is_day
            val hourlyIsDayHour = weather.hourly.is_day
            val currentTemperature = weather.current.temperature_2m
            val currentTemperatureUnit = weather.current_units.temperature_2m
            val currentWeatherCode = weather.current.weather_code
            val hourlyWeatherCode = weather.hourly.weather_code
            val daysWeatherCode = weather.daily.weather_code

            val scrollState = rememberScrollState()

            val isRow by remember {
                derivedStateOf {
                    scrollState.value > 20
                }
            }
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        brush = Brush.verticalGradient(
                            if (currentIsDayHour == 1) {
                                listOf(Color(0xFF87CEFA), Color(0xFFFFFFFF))
                            } else {
                                listOf(Color(0xff060414), Color(0xff0D0C19))
                            }
                        )
                    )
                    .verticalScroll(scrollState),
            ) {

                LocationText(
                    locationName = "ismailia",
                    onTextClick = { },
                    isDayHour = currentIsDayHour
                )

                WeatherInformation(
                    icon = viewModel.getIconsByWeatherCode(
                            weather.current.weather_code.toString(),
                            isDayHour = currentIsDayHour
                    ),
                    currentTemperature = "$currentTemperature $currentTemperatureUnit",
                    weatherStatus = viewModel.getWeatherStatusByWeatherCode(
                        currentWeatherCode.toString()
                    ),
                    maxTemperatureOfTheDay =
                    "${weather.daily.temperature_2m_max.first()} ${weather.daily_units.temperature_2m_max}",
                    minimumTemperatureOfTheDay =
                    "${weather.daily.temperature_2m_min.first()} ${weather.daily_units.temperature_2m_min}",
                    isDayHour = currentIsDayHour,
                    isRow = isRow
                )

                LazyVerticalGrid(
                    columns = GridCells.Fixed(3),
                    modifier = Modifier
                        .height(260.dp)
                        .fillMaxWidth(),
                    contentPadding = PaddingValues(horizontal = 12.dp, vertical = 24.dp),
                    verticalArrangement = Arrangement.spacedBy(6.dp),
                    horizontalArrangement = Arrangement.spacedBy(6.dp),
                    userScrollEnabled = false
                ) {
                    items(6) {
                        WeatherInfoCard(
                            weatherInfoTitle = getWeatherInfoCardInformation(weather)[it].title,
                            weatherInfoImage = ImageVector.vectorResource(
                                getWeatherInfoCardInformation(weather)[it].iconResId
                            ),
                            weatherInfoValue = getWeatherInfoCardInformation(weather)[it].value,
                            isDayHour = currentIsDayHour
                        )
                    }
                }

                Text(
                    text = "Today",
                    style = TextStyle(
                        fontSize = 20.sp,
                        fontFamily = FontFamily(Font(R.font.urbanist_semi_bold)),
                        fontWeight = FontWeight(600),
                        color = if (currentIsDayHour == 1) Color(0xFF060414) else Color.White,
                        letterSpacing = 0.25.sp,
                    ),
                    modifier = Modifier.padding(start = 12.dp, top = 24.dp)
                )

                LazyRow(
                    contentPadding = PaddingValues(horizontal = 12.dp, vertical = 24.dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(weather.hourly.time.size) {
                        TodaySection(
                            weatherImage = painterResource(
                                viewModel.getIconsByWeatherCode(
                                    hourlyWeatherCode[it].toString(),
                                    hourlyIsDayHour[it]
                                )
                            ),
                            weatherTemperature = "${weather.hourly.temperature_2m[it]} ${weather.hourly_units.temperature_2m}",
                            currentHour = splitTodaySectionTime(weather.hourly.time[it]),
                            isDayHour = currentIsDayHour
                        )
                    }
                }

                NextSevenDaysSection(
                    days = getNextSevenDaysDate(weather),
                    maxTemperaturesOfDay = getNextSevenDaysMaxTemperatures(weather),
                    minimumTemperaturesOfDay = getNextSevenDaysMinimumTemperatures(weather),
                    weatherImages = List(daysWeatherCode.size) {
                            viewModel.getIconsByWeatherCode(
                                daysWeatherCode[it].toString(),
                                1
                            )
                    },
                    isDayHour = currentIsDayHour
                )
            }
        }
    }
}