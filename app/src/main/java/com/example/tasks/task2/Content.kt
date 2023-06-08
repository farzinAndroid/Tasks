package com.example.tasks.task2

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun Content() {


    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.LightGray)
    ) {


        TopBar()


        Card(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 80.dp),
            shape = RoundedCornerShape(
                topEnd = 30.dp,
                topStart = 30.dp,
            ),
            colors = CardDefaults.cardColors(
                containerColor = Color.LightGray
            )


        ) {


            LazyColumn() {


                item {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight(),
                                colors = CardDefaults.cardColors(
                            containerColor = Color.White
                        )
                    ) {
                        TopTwinCardSection()

                        Spacer(modifier = Modifier.height(16.dp))

                        SuggestionRow()


                    }
                }

                item { Spacer(modifier = Modifier.height(8.dp)) }

                item {
                    RancelGameSection()
                }

                item { Spacer(modifier = Modifier.height(8.dp)) }

                item {
                    SuggestionTabRows()
                }
            }


        }





    }


}