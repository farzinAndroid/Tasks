package com.example.tasks.task2

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.tasks.R

@Composable
fun SuggestionRow() {

    Row(
        modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {


        Icon(
            painter = painterResource(R.drawable.right_arrow),
            contentDescription ="",
            modifier = Modifier
                .size(10.dp)
                .weight(0.05f)
        )

        LazyRow(
            modifier = Modifier
                .weight(0.9f)
        ){

            item {
                SuggestionRowItem(
                    icon = painterResource(R.drawable.email),
                    title = "ارسال\u200Cپیامک"
                )
            }

            item {
                SuggestionRowItem(
                    icon = painterResource(R.drawable.people),
                    title = "خریدبسته\u200Cچندکاربره"
                )
            }

            item {
                SuggestionRowItem(
                    icon = painterResource(R.drawable.percent),
                    title = "خریدبسته\u200Cپیشنهادی"
                )
            }

            item {
                SuggestionRowItem(
                    icon = painterResource(R.drawable.phone_call),
                    title = "خریدبسته\u200Cمکالمه"
                )
            }

            item {
                SuggestionRowItem(
                    icon = painterResource(R.drawable.global),
                    title = "خریدبسته\u200Cاینترنت",
                    isProgressBarEnabled = true
                )
            }

        }

        Icon(
            painter = painterResource(R.drawable.left_arrow),
            contentDescription ="",
            modifier = Modifier
                .size(10.dp)
                .weight(0.05f)
        )


    }

}

