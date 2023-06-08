package com.example.tasks.task2

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tasks.R

@Composable
fun HotItem(
    painter: Painter,
    title:String,
    isHaveBadge:Boolean = false,
    badgeTitle:String = ""
) {

    Box(
        contentAlignment = Alignment.TopEnd,
        modifier = Modifier
            .clickable {  }
    ) {


        Column(
            modifier = Modifier
                .width(100.dp)
                .height(90.dp)
                .padding(top = 16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Image(
                painter = painter,
                contentDescription ="",
                modifier = Modifier
                    .weight(0.8f)
                    .padding(horizontal = 4.dp)
            )

            Text(
                text = title,
                fontSize = 10.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                modifier = Modifier
                    .weight(0.2f),
                fontFamily = FontFamily(Font(R.font.sans))
            )


        }


        if (isHaveBadge){
            Box(
                modifier = Modifier
                    .padding(end = 8.dp)
                    .clip(RoundedCornerShape(4.dp))
                    .background(Color.Red)


            ) {


                Text(
                    text = badgeTitle,
                    fontSize = 8.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    modifier = Modifier
                        .padding(horizontal = 6.dp),
                    fontFamily = FontFamily(Font(R.font.sans))
                )

            }
        }


    }



}