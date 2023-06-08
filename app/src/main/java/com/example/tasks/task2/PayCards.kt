package com.example.tasks.task2

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tasks.R

@Composable
fun PayCards(
    price: Long,
    title: String,
    color: Color,
    shouldHaveArrow: Boolean = false,
    buttonTxt: String,
) {


    Card(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .clickable { },
        shape = RoundedCornerShape(20.dp),
        border = BorderStroke(width = 1.dp, Color.Gray),
        colors = CardDefaults.cardColors(
            containerColor = Color.Transparent
        )
    ) {

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp)
                .padding(bottom = 16.dp)
                .padding(horizontal = 8.dp)
        ) {

            Text(
                text = title,
                fontSize = 10.sp,
                color = Color.Gray,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(vertical = 8.dp),
                fontFamily = FontFamily(Font(R.font.sans))
            )

            Text(
                text = "${DigitHelper.digitByLang(price.toString())} ریال",
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(bottom = 12.dp),
                fontFamily = FontFamily(Font(R.font.sans))
            )

            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Button(
                    onClick = { /*TODO*/ },
                    shape = RoundedCornerShape(20.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = color
                    ),
                    modifier = Modifier
                        .weight(0.9f)
                        .height(36.dp)
                        .padding(horizontal = 4.dp)
                ) {
                    Text(
                        text = buttonTxt,
                        fontSize = 10.sp,
                        color = Color.Black,
                        modifier = Modifier
                            .fillMaxSize(),
                        textAlign = TextAlign.Center,
                        fontFamily = FontFamily(Font(R.font.sans))
                    )
                }

                if (shouldHaveArrow) {

                    Icon(
                        painter = painterResource(R.drawable.arrow),
                        contentDescription = "",
                        modifier = Modifier
                            .weight(0.2f)
                            .size(30.dp)
                            .clip(shape = RoundedCornerShape(50.dp))
                            .background(Color.LightGray)
                            .padding(4.dp),

                        tint = Color.Black
                    )

                }
            }

        }

    }


}