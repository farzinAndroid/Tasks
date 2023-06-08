package com.example.tasks

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.LayoutDirection
import com.example.tasks.task2.Content
import com.example.tasks.task2.TopBar
import com.example.tasks.ui.theme.TasksTheme


class MainActivity : ComponentActivity() {

    @OptIn(ExperimentalMaterial3Api::class)
    @SuppressLint("CoroutineCreationDuringComposition", "UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TasksTheme {
                // A surface container using the 'background' color from the theme



                CompositionLocalProvider(LocalLayoutDirection.provides(LayoutDirection.Rtl)) {
                    Scaffold(
                        modifier = Modifier
                            .fillMaxSize(),
                        content = {
                            TopBar()
                            Content()
                        }
                    )
                }


            }
        }

    }
}





