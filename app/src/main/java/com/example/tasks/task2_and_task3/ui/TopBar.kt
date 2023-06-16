package com.example.tasks.task2_and_task3.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.TopEnd
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tasks.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar() {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(120.dp)
            .background(Color(0xfff8b300)),
        verticalAlignment = Alignment.Top,
    ) {

        Card(
            modifier = Modifier
                .weight(0.7f)
                .padding(horizontal = 10.dp)
                .padding(vertical = 16.dp)
                .clickable {  },
            shape = RoundedCornerShape(12.dp)
        ) {
            Row(

                verticalAlignment = Alignment.CenterVertically,
            ) {



                Row(
                    modifier = Modifier
                        .background(Color(0xffffbe1a)),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    Image(
                        painter = painterResource(R.drawable.irancell),
                        contentDescription = "",
                        colorFilter = ColorFilter.tint(color = Color.Black),
                        modifier = Modifier
                            .size(50.dp)
                            .weight(0.2f)

                    )

                    Column(
                        horizontalAlignment = Alignment.Start,
                        verticalArrangement = Arrangement.Center,
                        modifier = Modifier
                            .padding(start = 8.dp)
                            .weight(0.7f)

                    ) {

                        Text(
                            text = "Farzin",
                            fontSize = 12.sp,
                            color = Color.Black,
                            fontWeight = FontWeight.SemiBold,
                            textAlign = TextAlign.Start,
                        )

                        Text(
                            text = DigitHelper.digitByLang("09203168496"),
                            fontSize = 12.sp,
                            color = Color.Black,
                            fontWeight = FontWeight.SemiBold,
                            modifier = Modifier
                                .padding(top = 8.dp),
                            textAlign = TextAlign.Start,
                            fontFamily = FontFamily(Font(R.font.sans))
                        )

                    }


                    Icon(
                        painter = painterResource(R.drawable.arrow),
                        contentDescription = "",
                        modifier = Modifier
                            .size(20.dp)
                            .weight(0.1f)
                    )
                }


            }
        }



        Spacer(
            modifier = Modifier
                .weight(0.1f)
                .background(Color.Transparent)
        )



        Card(
            modifier = Modifier
                .weight(0.2f)
                .padding(horizontal = 12.dp)
                .padding(vertical = 16.dp)
                .clickable {  },
            colors = CardDefaults.cardColors(containerColor = Color(0xffffbe1a)),
            shape = RoundedCornerShape(12.dp)

        ) {

            Box(
                contentAlignment = TopEnd,
                modifier = Modifier
                    .align(CenterHorizontally)
            ){



                Icon(
                    painter = painterResource(R.drawable.bell),
                    contentDescription = "",
                    modifier = Modifier
                        .size(50.dp)
                        .padding(10.dp),
                    tint = Color.Black

                )


                Box(
                    modifier = Modifier
                        .clip(
                            shape = CircleShape
                        )
                        .background(Color.Red)

                        .size(20.dp),
                    contentAlignment = Center
                ){

                    Text(
                        text = DigitHelper.digitByLang("5"),
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center
                    )

                }

            }



        }


    }

}