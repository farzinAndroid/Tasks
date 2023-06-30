package com.example.tasks

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import android.os.Bundle
import android.preference.PreferenceManager
import android.provider.Settings
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
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
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import com.example.tasks.task2_and_task3.ui.Content
import com.example.tasks.task2_and_task3.ui.TopBar
import com.example.tasks.task4.MyLocationManager
import com.example.tasks.ui.theme.TasksTheme
import dagger.hilt.android.AndroidEntryPoint
import org.osmdroid.config.Configuration
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.mylocation.GpsMyLocationProvider
import org.osmdroid.views.overlay.mylocation.MyLocationNewOverlay


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
                // A surface container using the 'background' color from the theme



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







