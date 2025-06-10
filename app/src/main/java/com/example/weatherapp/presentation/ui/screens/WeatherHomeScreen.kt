package com.example.weatherapp.presentation.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
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
import com.example.weatherapp.presentation.state.LocationState
import com.example.weatherapp.presentation.state.WeatherResponseState
import com.example.weatherapp.presentation.ui.composables.CurrentLocationText
import com.example.weatherapp.presentation.ui.composables.NextSevenDaysSection
import com.example.weatherapp.presentation.ui.composables.TodaySection
import com.example.weatherapp.presentation.ui.composables.WeatherInfoCard
import com.example.weatherapp.presentation.ui.composables.WeatherInformation
import com.example.weatherapp.presentation.ui.utils.HomeScreenUtils.getWeatherInfoCardInformation
import com.example.weatherapp.presentation.ui.utils.HomeScreenUtils.splitTodaySectionTime
import org.koin.androidx.compose.koinViewModel

@Composable
fun WeatherHomeScreen(
    viewModel: HomeScreenViewModel = koinViewModel()
) {
    val weatherState by viewModel.state.collectAsState()
    val locationState by viewModel.locationState.collectAsState()

    var locationText by remember { mutableStateOf("") }

    val scrollState = rememberScrollState()

    val isRow by remember {
        derivedStateOf {
            scrollState.value > 2
        }
    }

    when (locationState) {
        is LocationState.Initial -> {
            Box(modifier = Modifier.fillMaxSize()) {
                Text(
                    text = "Tap here to get your location",
                    modifier = Modifier
                        .align(Alignment.Center)
                        .clickable {
                            viewModel.onGetLocationClickListener()
                        }
                )
            }
        }

        is LocationState.OnError -> {
            Box(modifier = Modifier.fillMaxSize()) {
                Text(
                    text = (locationState as LocationState.OnError).errorMessage,
                    modifier = Modifier
                        .align(Alignment.Center)
                        .clickable {
                            viewModel.onGetLocationClickListener()
                        }
                )
            }
        }

        is LocationState.OnLoading -> {
            Box(modifier = Modifier.fillMaxSize()) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
        }

        is LocationState.OnSuccess -> {
            locationText = (locationState as LocationState.OnSuccess).cityLocation.locationName
        }
    }

    when (weatherState) {
        is WeatherResponseState.Initial -> {}

        is WeatherResponseState.OnError -> {
            Box(modifier = Modifier.fillMaxSize()) {
                Text(
                    text = (weatherState as WeatherResponseState.OnError).errorMessage,
                    modifier = Modifier
                        .align(Alignment.Center)
                )
            }
        }

        is WeatherResponseState.OnLoading -> {
            Box(modifier = Modifier.fillMaxSize()) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
        }

        is WeatherResponseState.OnSuccess -> {
            val weather = (weatherState as WeatherResponseState.OnSuccess).weatherResponse
            val currentIsDayHour = weather.current.isDay

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

                CurrentLocationText(
                    locationName = locationText,
                    onTextClick = {viewModel.onGetLocationClickListener() },
                    isDayHour = currentIsDayHour,
                    modifier = Modifier
                        .padding(top = 64.dp)
                        .fillMaxWidth()
                )

                WeatherInformation(
                    icon = weather.current.weatherIcon,
                    currentTemperature = weather.current.temperature,
                    weatherStatus = weather.current.weatherStatus,
                    maxTemperatureOfTheDay = weather.daily.maxTemperatures.first(),
                    minimumTemperatureOfTheDay = weather.daily.minimumTemperatures.first(),
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
                            isDayHour = currentIsDayHour,
                            modifier = Modifier
                                .height(115.dp)
                                .border(
                                    width = 1.dp,
                                    color = if (currentIsDayHour == 1) Color(0x14060414) else Color(
                                        0x14FFFFFF
                                    ),
                                    shape = RoundedCornerShape(size = 24.dp)
                                )
                                .background(
                                    color = if (currentIsDayHour == 1) Color(0xB2FFFFFF) else Color(
                                        0xB2060414
                                    ),
                                    shape = RoundedCornerShape(size = 24.dp)
                                )
                                .fillMaxWidth()
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
                    ),
                    modifier = Modifier.padding(start = 12.dp, top = 24.dp)
                )

                LazyRow(
                    contentPadding = PaddingValues(horizontal = 12.dp, vertical = 24.dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(weather.hourly.times.size) {
                        TodaySection(
                            weatherImage = painterResource(
                                weather.hourly.weatherIcons[it]
                            ),
                            weatherTemperature = weather.hourly.temperatures[it],
                            currentHour = splitTodaySectionTime(weather.hourly.times[it]),
                            isDayHour = currentIsDayHour,
                            modifier = Modifier
                                .border(
                                    width = 1.dp,
                                    color = if (currentIsDayHour == 1) Color(0x14060414) else Color(
                                        0x14FFFFFF
                                    ),
                                    shape = RoundedCornerShape(size = 20.dp)
                                )
                                .width(88.dp)
                                .height(120.dp)
                                .background(
                                    color = if (currentIsDayHour == 1) Color(0xB2FFFFFF) else Color(
                                        0xB2060414
                                    ),
                                    shape = RoundedCornerShape(size = 20.dp)
                                )
                        )
                    }
                }

                Text(
                    text = "Next 7 days",
                    style = TextStyle(
                        fontSize = 20.sp,
                        fontFamily = FontFamily(Font(R.font.urbanist_semi_bold)),
                        fontWeight = FontWeight(600),
                        color = if (currentIsDayHour == 1) Color(0xFF060414) else Color.White,
                    ),
                    modifier = Modifier.padding(bottom = 12.dp, start = 12.dp)
                )

                NextSevenDaysSection(
                    days = weather.daily.times,
                    maxTemperaturesOfDay = weather.daily.maxTemperatures,
                    minimumTemperaturesOfDay = weather.daily.minimumTemperatures,
                    weatherImages = weather.daily.weatherIcons,
                    isDayHour = currentIsDayHour,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 32.dp)
                        .padding(horizontal = 12.dp)
                        .background(
                            color = if (currentIsDayHour == 1) Color(0xB2FFFFFF) else Color(
                                0x14060414
                            ),
                            shape = RoundedCornerShape(24.dp)
                        )
                        .border(
                            width = 1.dp,
                            color = if (currentIsDayHour == 1) Color(0x14060414) else Color(
                                0x14FFFFFF
                            ),
                            shape = RoundedCornerShape(size = 24.dp)
                        )
                )
            }
        }
    }
}