package com.example.weatherapp.presentation.ui.composables

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weatherapp.R
import com.example.weatherapp.presentation.ui.utils.HomeScreenUtils

@Composable
fun NextSevenDaysSection(
    days: List<String>,
    maxTemperaturesOfDay: List<String>,
    minimumTemperaturesOfDay: List<String>,
    @DrawableRes weatherImages: List<Int>,
    isDayHour: Int
) {
    Text(
        text = "Next 7 days",
        style = TextStyle(
            fontSize = 20.sp,
            fontFamily = FontFamily(Font(R.font.urbanist_semi_bold)),
            fontWeight = FontWeight(600),
            color = if (isDayHour == 1) Color(0xFF060414) else Color.White,
            letterSpacing = 0.25.sp,
        ),
        modifier = Modifier.padding(bottom = 12.dp, start = 12.dp)
    )

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 32.dp)
            .padding(horizontal = 12.dp)
            .background(
                color = if (isDayHour == 1) Color(0xB2FFFFFF) else Color(0x14060414),
                shape = RoundedCornerShape(24.dp)
            )
            .border(
                width = 1.dp,
                color = if (isDayHour == 1) Color(0x14060414) else Color(0x14FFFFFF),
                shape = RoundedCornerShape(size = 24.dp)
            ),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        days.forEachIndexed { index, day ->
            NextSevenDaysRow(
                day = day,
                highTemp = maxTemperaturesOfDay[index],
                lowTemp = minimumTemperaturesOfDay[index],
                weatherIcon = weatherImages[index],
                isLastRow = index == days.lastIndex,
                isDayHour = isDayHour
            )
        }
    }
}

@Composable
private fun NextSevenDaysRow(
    day: String,
    highTemp: String,
    lowTemp: String,
    @DrawableRes weatherIcon: Int,
    isLastRow: Boolean,
    isDayHour: Int,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = day,
            style = TextStyle(
                fontSize = 16.sp,
                fontFamily = FontFamily(Font(R.font.urbanist_regular)),
                fontWeight = FontWeight(400),
                color = if (isDayHour == 1) Color(0x99060414) else Color(0x99FFFFFF),
            ),
            modifier = Modifier.weight(0.3f)
        )

        Box(
            modifier = Modifier.size(50.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .blur(145.dp, edgeTreatment = BlurredEdgeTreatment.Unbounded)
                    .clip(CircleShape)
                    .background(
                        HomeScreenUtils
                            .extractDominantColor(
                                LocalContext.current,
                                weatherIcon
                            ).copy(alpha = 0.32f),
                        shape = CircleShape
                    )
            )

            Image(
                painter = painterResource(weatherIcon),
                contentDescription = "Sun icon",
                modifier = Modifier
                    .height(45.dp)
                    .width(45.dp),
                contentScale = ContentScale.FillWidth
            )
        }

        Box(
            modifier = Modifier.weight(0.4f)
        ) {
            Row(
                modifier = Modifier
                    .clip(shape = RoundedCornerShape(size = 100.dp))
                    .padding(top = 8.dp, end = 0.dp, bottom = 8.dp)
                    .align(Alignment.CenterEnd),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(4.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Icon(
                        imageVector = ImageVector.vectorResource(R.drawable.arrow_up),
                        contentDescription = "Arrow Up",
                        tint = if (isDayHour == 1) Color(0x99060414) else Color(0xDEFFFFFF),
                    )

                    Text(
                        text = highTemp,
                        style = TextStyle(
                            fontSize = 16.sp,
                            fontFamily = FontFamily(Font(R.font.urbanist_medium)),
                            fontWeight = FontWeight(500),
                            color = if (isDayHour == 1) Color(0x99060414) else Color(0xDEFFFFFF),
                            textAlign = TextAlign.Center,
                            letterSpacing = 0.25.sp,
                        )
                    )
                }

                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.divder),
                    contentDescription = "Divider",
                    modifier = Modifier.padding(horizontal = 8.dp),
                    tint = if (isDayHour == 1) Color(0x99060414) else Color(0xDEFFFFFF),
                )

                Row(
                    horizontalArrangement = Arrangement.spacedBy(4.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Icon(
                        imageVector = ImageVector.vectorResource(R.drawable.arrow_down),
                        contentDescription = "Arrow Down",
                        tint = if (isDayHour == 1) Color(0x99060414) else Color(0xDEFFFFFF),
                    )

                    Text(
                        text = lowTemp,
                        style = TextStyle(
                            fontSize = 16.sp,
                            fontFamily = FontFamily(Font(R.font.urbanist_medium)),
                            fontWeight = FontWeight(500),
                            color = if (isDayHour == 1) Color(0x99060414) else Color(0xDEFFFFFF),
                            textAlign = TextAlign.Center,
                        ),
                    )
                }
            }
        }

    }

    if (!isLastRow) HorizontalDivider(
        thickness = 1.dp,
        color = if (isDayHour == 1) Color(0x14060414) else Color(0x14FFFFFF)
    )
}