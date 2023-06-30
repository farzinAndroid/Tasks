package com.example.tasks

import android.annotation.SuppressLint
import android.app.Activity
import android.location.Location
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.View
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import com.example.tasks.task4.MyLocationManager
import com.example.tasks.ui.theme.TasksTheme
import dagger.hilt.android.AndroidEntryPoint
import org.osmdroid.config.Configuration
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.views.MapView


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @OptIn(ExperimentalMaterial3Api::class)
    @SuppressLint(
        "CoroutineCreationDuringComposition", "UnusedMaterial3ScaffoldPaddingParameter",
        "SetTextI18n"
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TasksTheme {




                Scaffold(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    MapScreen()
                }


               /* CompositionLocalProvider(LocalLayoutDirection.provides(LayoutDirection.Rtl)) {
                    Scaffold(
                        modifier = Modifier
                            .fillMaxSize(),
                        content = {
                            TopBar()
                            Content()
                        }
                    )
                }*/


            }
        }

    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MapScreen() {
    var myLocation by remember { mutableStateOf("") }
    var currentLocation by remember { mutableStateOf<Location?>(null) }

    val context = LocalContext.current

    val myLocationManager = remember {
        MyLocationManager(
            context = context,
            onLocationChangedEvent = { location ->
                currentLocation = location
                myLocation = "Lat: ${location.latitude}, Long: ${location.longitude}"
            },
            onLocationError = { error ->
                currentLocation = null
                myLocation = ""
            }
        )

    }

    val mapView = remember {
        MapView(context).apply {
            setTileSource(TileSourceFactory.MAPNIK)
            this.visibility = View.VISIBLE
            setMultiTouchControls(true)
        }
    }

    SideEffect {
        myLocationManager.enableLocationServices(context as Activity, mapView)
    }

    Scaffold(
        topBar = {
            TopAppBar(title = {
                if (myLocation.isNullOrBlank()) {
                    Text(
                        text = "Please Activate Your Location",
                        fontSize = 12.sp,
                        color = Color.Yellow
                    )
                } else {
                    Text(
                        text = "Location state : Lat ${currentLocation?.latitude} Long ${currentLocation?.longitude}",
                        fontSize = 12.sp,
                    )
                }
            })
        }
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {

            Configuration.getInstance()
                .load(context, PreferenceManager.getDefaultSharedPreferences(context))



                AndroidView(
                    modifier = Modifier.weight(1f),
                    factory = {
                        mapView
                    })


        }
    }
}







