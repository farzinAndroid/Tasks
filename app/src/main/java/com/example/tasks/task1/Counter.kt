package com.example.tasks.task1

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@OptIn(DelicateCoroutinesApi::class)
@Composable
fun Counter() {
    var count1 by remember { mutableStateOf(0) }
    var count2 by remember { mutableStateOf(0) }


    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.SpaceAround,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(
                onClick = {

                    GlobalScope.launch(Dispatchers.Main) {
                        while (true) {
                            delay(1000)
                            count1++
                        }


                    }


                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Green
                )
            ) {
                Text(
                    text = "Start count 1",
                    color = Color.Black
                )
            }

            Text(
                text = count1.toString(),
                color = Color.Black
            )
        }



        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(
                onClick = {
                    GlobalScope.launch(Dispatchers.Main) {
                        while (true) {
                            delay(1000)
                            count2++
                        }


                    }
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Yellow
                )
            ) {
                Text(
                    text = "Start count 2",
                    color = Color.Black
                )
            }

            Text(
                text = count2.toString(),
                color = Color.Black
            )
        }

    }

}