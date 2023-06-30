package com.example.tasks.task2_and_task3.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tasks.R

@Composable
fun SuggestionTabRows() {

    var selectedTabIndex by remember {
        mutableStateOf(0)
    }

    val tabTitles = listOf(
        TabItems(
            name = "داغ",
            icon = painterResource(R.drawable.fire)
        ),
        TabItems(
            name = "طلایی",
            icon = painterResource(R.drawable.medal)
        ),
        TabItems(
            name = "مالی",
            icon = painterResource(R.drawable.money_bag)
        )
    )


    Card(
        modifier = Modifier
            .fillMaxSize()
            .fillMaxHeight(1f),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ), shape = RoundedCornerShape(20.dp)
    ) {

        Column {

            TabRow(
                selectedTabIndex = selectedTabIndex,
                modifier = Modifier
                    .padding(horizontal = 16.dp),
                indicator = { line ->
                    Box(
                        modifier = Modifier
                            .tabIndicatorOffset(line[selectedTabIndex])
                            .height(3.dp)
                            .background(Color(0xfff8b300))

                    )
                },
                divider = {
                    Divider(color = Color.Transparent)
                }

            ) {

                tabTitles.forEachIndexed { index, tabItems ->

                    Tab(
                        selected = selectedTabIndex == index,
                        onClick = { selectedTabIndex = index },
                        text = {
                            Row(
                                verticalAlignment = CenterVertically,
                                horizontalArrangement = Arrangement.Center
                            ) {

                                Text(
                                    text = tabItems.name,
                                    fontSize = 10.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.Black,
                                    modifier = Modifier
                                        .padding(horizontal = 4.dp),
                                    fontFamily = FontFamily(Font(R.font.sans))
                                )

                                Image(
                                    painter = tabItems.icon,
                                    contentDescription = "",
                                    modifier = Modifier
                                        .size(16.dp)
                                )

                            }
                        }

                    )

                }

            }

            when (selectedTabIndex) {
                0 -> {
                    HotSection()
                }

                1 -> {
                    GoldSection()
                }

                2 -> {
                    HotSection()
                }
            }

        }

    }

}