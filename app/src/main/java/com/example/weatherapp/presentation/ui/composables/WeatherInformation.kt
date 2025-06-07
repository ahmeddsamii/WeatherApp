package com.example.weatherapp.presentation.ui.composables

import androidx.annotation.DrawableRes
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.SizeTransform
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideIn
import androidx.compose.animation.slideOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.BlurredEdgeTreatment
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weatherapp.R
import com.example.weatherapp.presentation.ui.utils.HomeScreenUtils

@Composable
fun WeatherInformation(
    @DrawableRes icon: Int,
    currentTemperature: String,
    weatherStatus: String,
    maxTemperatureOfTheDay: String,
    minimumTemperatureOfTheDay: String,
    isDayHour: Int,
    isRow: Boolean
) {

    AnimatedContent(
        targetState = isRow,
        transitionSpec = {
            slideIn(
                animationSpec = tween(durationMillis = 500),
                initialOffset = { fullSize -> IntOffset(fullSize.width, fullSize.height) }
            ) togetherWith slideOut(
                animationSpec = tween(durationMillis = 500),
                targetOffset = { fullSize -> IntOffset(fullSize.width, -fullSize.height) }
            ) using SizeTransform(clip = false)
        },
        label = "Switch Layout"
    ) { rowMode ->

        if (!rowMode) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                    modifier = Modifier.size(200.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .blur(130.dp, edgeTreatment = BlurredEdgeTreatment.Unbounded)
                            .clip(CircleShape)
                            .background(
                                HomeScreenUtils
                                    .extractDominantColor(
                                        LocalContext.current,
                                        icon
                                    ).copy(alpha = 0.32f),
                                shape = CircleShape
                            )
                    )

                    Image(
                        painter = painterResource(icon),
                        contentDescription = "Weather Icon",
                        modifier = Modifier
                            .size(200.dp)
                            .align(Alignment.Center)
                    )
                }

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = currentTemperature,
                        style = TextStyle(
                            fontSize = 64.sp,
                            fontFamily = FontFamily(Font(R.font.urbanist_regular)),
                            fontWeight = FontWeight(600),
                            color = if (isDayHour == 1) Color(0x99060414) else Color.White,
                        )
                    )

                    Text(
                        text = weatherStatus,
                        style = TextStyle(
                            fontSize = 16.sp,
                            fontFamily = FontFamily(Font(R.font.urbanist_medium)),
                            fontWeight = FontWeight(500),
                            color = if (isDayHour == 1) Color(0x99060414) else Color(0x99FFFFFF),
                            textAlign = TextAlign.Center,
                        ),
                        modifier = Modifier
                            .padding(bottom = 12.dp)
                    )

                    Row(
                        modifier = Modifier
                            .background(
                                color = if (isDayHour == 1) Color(0x14060414) else Color(0x14FFFFFF),
                                shape = RoundedCornerShape(size = 100.dp)
                            )
                            .padding(start = 24.dp, top = 8.dp, end = 24.dp, bottom = 8.dp),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(4.dp),
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            Icon(
                                imageVector = ImageVector.vectorResource(R.drawable.arrow_up),
                                contentDescription = "Arrow Up",
                                tint = if (isDayHour == 1) Color(0x99060414) else Color(0xDEFFFFFF)
                            )

                            Text(
                                text = maxTemperatureOfTheDay,
                                style = TextStyle(
                                    fontSize = 16.sp,
                                    fontFamily = FontFamily(Font(R.font.urbanist_medium)),
                                    fontWeight = FontWeight(500),
                                    color = if (isDayHour == 1) Color(0x99060414) else Color(
                                        0xDEFFFFFF
                                    ),
                                    textAlign = TextAlign.Center,
                                )
                            )
                        }

                        Icon(
                            imageVector = ImageVector.vectorResource(R.drawable.divder),
                            contentDescription = "divider",
                            modifier = Modifier.padding(horizontal = 8.dp),
                            tint = if (isDayHour == 1) Color(0x99060414) else Color(0xDEFFFFFF)
                        )

                        Row(
                            horizontalArrangement = Arrangement.spacedBy(4.dp),
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            Icon(
                                imageVector = ImageVector.vectorResource(R.drawable.arrow_down),
                                contentDescription = "Arrow down",
                                tint = if (isDayHour == 1) Color(0x99060414) else Color(0xDEFFFFFF)
                            )

                            Text(
                                text = minimumTemperatureOfTheDay,
                                style = TextStyle(
                                    fontSize = 16.sp,
                                    fontFamily = FontFamily(Font(R.font.urbanist_medium)),
                                    fontWeight = FontWeight(500),
                                    color = if (isDayHour == 1) Color(0x99060414) else Color(
                                        0xDEFFFFFF
                                    ),
                                    textAlign = TextAlign.Center,
                                )
                            )
                        }
                    }
                }
            }
        } else {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Box(
                    modifier = Modifier.size(124.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .blur(90.dp, edgeTreatment = BlurredEdgeTreatment.Unbounded)
                            .clip(CircleShape)
                            .background(
                                if (isDayHour == 1) {
                                    Color(0xff00619D).copy(alpha = 0.33f)
                                } else {
                                    Color(0xffC0B7FF).copy(alpha = 0.20f)
                                },
                                shape = CircleShape
                            )
                    )

                    Image(
                        painter = painterResource(icon),
                        contentDescription = "Weather Icon",
                        modifier = Modifier
                            .size(124.dp)
                            .align(Alignment.Center)
                    )
                }


                Box(modifier = Modifier.weight(0.5f)) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.align(Alignment.CenterEnd)
                    ) {
                        Text(
                            text = currentTemperature,
                            style = TextStyle(
                                fontSize = 50.sp,
                                fontFamily = FontFamily(Font(R.font.urbanist_regular)),
                                fontWeight = FontWeight(600),
                                color = if (isDayHour == 1) Color(0x99060414) else Color.White,
                            )
                        )

                        Text(
                            text = weatherStatus,
                            style = TextStyle(
                                fontSize = 16.sp,
                                fontFamily = FontFamily(Font(R.font.urbanist_medium)),
                                fontWeight = FontWeight(500),
                                color = if (isDayHour == 1) Color(0x99060414) else Color(0x99FFFFFF),
                                textAlign = TextAlign.Center,
                            ),
                            modifier = Modifier
                                .padding(bottom = 12.dp)
                        )

                        Row(
                            modifier = Modifier
                                .background(
                                    color = if (isDayHour == 1) Color(0x14060414) else Color(
                                        0x14FFFFFF
                                    ),
                                    shape = RoundedCornerShape(size = 100.dp)
                                )
                                .padding(start = 24.dp, top = 8.dp, end = 24.dp, bottom = 8.dp),
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            Row(
                                horizontalArrangement = Arrangement.spacedBy(4.dp),
                                verticalAlignment = Alignment.CenterVertically,
                            ) {
                                Icon(
                                    imageVector = ImageVector.vectorResource(R.drawable.arrow_up),
                                    contentDescription = "Arrow Up",
                                    tint = if (isDayHour == 1) Color(0x99060414) else Color(
                                        0xDEFFFFFF
                                    )
                                )

                                Text(
                                    text = maxTemperatureOfTheDay,
                                    style = TextStyle(
                                        fontSize = 15.sp,
                                        fontFamily = FontFamily(Font(R.font.urbanist_medium)),
                                        fontWeight = FontWeight(500),
                                        color = if (isDayHour == 1) Color(0x99060414) else Color(
                                            0xDEFFFFFF
                                        ),
                                        textAlign = TextAlign.Center,
                                    )
                                )
                            }

                            Icon(
                                imageVector = ImageVector.vectorResource(R.drawable.divder),
                                contentDescription = "Divider",
                                modifier = Modifier.padding(horizontal = 8.dp),
                                tint = if (isDayHour == 1) Color(0x99060414) else Color(0xDEFFFFFF)
                            )

                            Row(
                                horizontalArrangement = Arrangement.spacedBy(4.dp),
                                verticalAlignment = Alignment.CenterVertically,
                            ) {
                                Icon(
                                    imageVector = ImageVector.vectorResource(R.drawable.arrow_down),
                                    contentDescription = "Arrow down",
                                    tint = if (isDayHour == 1) Color(0x99060414) else Color(
                                        0xDEFFFFFF
                                    )
                                )

                                Text(
                                    text = minimumTemperatureOfTheDay,
                                    style = TextStyle(
                                        fontSize = 15.sp,
                                        fontFamily = FontFamily(Font(R.font.urbanist_medium)),
                                        fontWeight = FontWeight(500),
                                        color = if (isDayHour == 1) Color(0x99060414) else Color(
                                            0xDEFFFFFF
                                        ),
                                        textAlign = TextAlign.Center,
                                    )
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}