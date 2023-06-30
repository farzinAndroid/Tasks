package com.example.tasks.task4

import android.annotation.SuppressLint
import android.app.Activity
import android.location.Location
import android.preference.PreferenceManager
import android.view.View
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import org.osmdroid.config.Configuration
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.views.MapView

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
            // Initialize the OpenStreetMap library
            Configuration.getInstance()
                .load(context, PreferenceManager.getDefaultSharedPreferences(context))

            // Show the map and the current location, if available

            AndroidView(
                modifier = Modifier.weight(1f),
                factory = {
                    mapView
                })


        }
    }
}