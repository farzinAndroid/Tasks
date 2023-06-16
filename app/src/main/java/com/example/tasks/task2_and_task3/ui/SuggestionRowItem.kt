package com.example.tasks.task2_and_task3.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tasks.R

@Composable
fun SuggestionRowItem(
    icon: Painter,
    title: String,
    isProgressBarEnabled: Boolean = false,
) {

    Column(
        modifier = Modifier
            .padding(horizontal = 8.dp)
            .padding(vertical = 12.dp)
            .height(110.dp)
            .width(90.dp)
            .clickable { },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        if (!isProgressBarEnabled) {
            Card(
                shape = RoundedCornerShape(50.dp),
                modifier = Modifier
                    .size(60.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xfff8b300)
                ),

                ) {

                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    Icon(
                        painter = icon,
                        contentDescription = "",
                        modifier = Modifier
                            .size(25.dp),
                        tint = Color.Black
                    )
                }

            }
        } else {

            Box(
                modifier = Modifier
                    .size(60.dp),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(
                    progress = 0.5f,
                    strokeWidth = 3.dp,
                    color = Color.Green,
                    modifier = Modifier
                        .fillMaxSize()
                        .border(
                            width = 1.dp,
                            color = Color.Gray,
                            shape = CircleShape
                        ),
                )

                Text(
                    text = "${DigitHelper.digitByLang("200")} مگ",
                    textAlign = TextAlign.Center,
                    fontSize = 10.sp,
                    fontFamily = FontFamily(Font(R.font.sans))
                )
            }


        }



        Spacer(modifier = Modifier.height(8.dp))


        Text(
            text = title,
            fontSize = 10.sp,
            color = Color.Black,
            modifier = Modifier
                .height(20.dp)
                .clip(RoundedCornerShape(20.dp))
                .fillMaxWidth()
                .background(Color(0xfff8b300))
                .padding(top = 4.dp)
                ,
            maxLines = 1,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,

            )

//        Card(
//            shape = RoundedCornerShape(50.dp),
//            colors = CardDefaults.cardColors(
//                containerColor = Color(0xfff8b300)
//            ),
//            modifier = Modifier.fillMaxWidth()
//        ) {
//
//            Text(
//                text = title,
//                fontSize = 8.sp,
//                color = Color.Black,
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(horizontal = 16.dp)
//                    .padding(vertical = 6.dp),
//                maxLines = 1,
//                textAlign = TextAlign.Center,
//                fontWeight = FontWeight.Bold,
//
//                )
//        }


    }


}