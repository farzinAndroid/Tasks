package com.example.tasks.task2_and_task3.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun TopTwinCardSection() {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        Row(
            modifier = Modifier
                .weight(0.5f)
        ) {
            PayCards(
                price = 0,
                title = "کاردکرد کل",
                color = Color(0xfff8b300),
                buttonTxt = "پرداخت میان دوره",
                shouldHaveArrow = true
            )
        }

        Spacer(modifier = Modifier.width(10.dp))

        Row(
            modifier = Modifier
                .weight(0.5f)
        ) {
            PayCards(
                price = 0,
                title = "موجودی کل جیب چت",
                color = Color(0xFF00D89B),
                buttonTxt = "مدیریت جیب چت",

                )
        }


    }

}