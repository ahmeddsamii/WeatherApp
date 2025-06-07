package com.example.weatherapp.presentation.ui.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weatherapp.R

@Composable
fun WeatherInfoCard(
    weatherInfoTitle: String,
    weatherInfoImage: ImageVector,
    weatherInfoValue: String,
    modifier: Modifier = Modifier,
    isDayHour: Int
) {
    Box(
        modifier = modifier
            .height(115.dp)
            .border(
                width = 1.dp,
                color = if (isDayHour == 1) Color(0x14060414) else Color(0x14FFFFFF),
                shape = RoundedCornerShape(size = 24.dp)
            )
            .background(
                color = if (isDayHour == 1) Color(0xB2FFFFFF) else Color(0xB2060414),
                shape = RoundedCornerShape(size = 24.dp)
            )
            .fillMaxWidth()
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .align(Alignment.Center)
        ) {
            Image(
                imageVector = weatherInfoImage,
                contentDescription = "Wind image",
                modifier = Modifier
                    .padding(
                        start = 38.dp,
                        end = 38.dp,
                        top = 16.dp,
                        bottom = 8.dp
                    )
                    .size(32.dp)
            )

            Text(
                text = weatherInfoValue,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                style = TextStyle(
                    fontSize = 20.sp,
                    fontFamily = FontFamily(Font(R.font.urbanist_medium)),
                    fontWeight = FontWeight(500),
                    color = if (isDayHour == 1) Color(0xDE060414) else Color(0xDEFFFFFF),
                    textAlign = TextAlign.Center,
                ),
                modifier = Modifier.padding(bottom = 2.dp)
            )

            Text(
                text = weatherInfoTitle,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                style = TextStyle(
                    fontSize = 14.sp,
                    fontFamily = FontFamily(Font(R.font.urbanist_regular)),
                    fontWeight = FontWeight(400),
                    color = if (isDayHour == 1) Color(0x99060414) else Color(0x99FFFFFF),
                    textAlign = TextAlign.Center,
                ),
                modifier = Modifier.padding(start = 8.dp, end = 8.dp, bottom = 16.dp)
            )
        }
    }
}