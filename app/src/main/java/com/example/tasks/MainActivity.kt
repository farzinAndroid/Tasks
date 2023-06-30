package com.example.tasks

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import android.os.Bundle
import android.preference.PreferenceManager
import android.util.Log
import android.view.View
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
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
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
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
                        .fillMaxSize(),
                    content = {
                        MapScreen()
                    }
                )


                /*CompositionLocalProvider(LocalLayoutDirection.provides(LayoutDirection.Rtl)) {
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

    val mapView = remember {
        MapView(context).apply {
            setTileSource(TileSourceFactory.MAPNIK)
            this.visibility = View.VISIBLE
            setMultiTouchControls(true)
        }
    }


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

    // Use the ActivityResultContracts.RequestPermission contract to request location permission
    val requestPermissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            // Permission granted, start location updates
            myLocationManager.startLocationUpdates(mapView,context as Activity)

            currentLocation?.let { location ->
                mapView.controller.setZoom(12.0)
                mapView.controller.setCenter(GeoPoint(location))
            }

        } else {
            // Permission not granted, update the text with an error message
            myLocation = "Permission not granted"
        }
    }

    //requestLocationSettingsLauncher
    // Use the ActivityResultContracts.StartActivityForResult contract to request the location settings activity
    rememberLauncherForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        // Check if location services are now enabled
        if (myLocationManager.isGpsEnabled()) {
            myLocationManager.startLocationUpdates(mapView, context as Activity)

            currentLocation?.let { location ->
                mapView.controller.setZoom(12.0)
                mapView.controller.setCenter(GeoPoint(location))
            }

        } else {
            // Location services still disabled
            myLocation = "Location services disabled"
        }
    }

    // Request location permission and check location services when the composable is first displayed

    LaunchedEffect(true) {
        if (context.checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            myLocationManager.startLocationUpdates(mapView,context as Activity)

            currentLocation?.let { location ->
                mapView.controller.setZoom(12.0)
                mapView.controller.setCenter(GeoPoint(location))
            }

        } else {
            requestPermissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
        }
    }



    Scaffold(
        topBar = {
            TopAppBar(title = {





                    if (myLocation.isNullOrBlank()){
                        Text(
                            text = "Please Activate Your Location",
                            fontSize = 12.sp,
                            color = Color.Yellow
                        )


                    }else{
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

            // Create a new MapView
            AndroidView(
                modifier = Modifier.weight(1f),
                factory = {
                    mapView
                    /*MapView(it).apply {

                        setTileSource(TileSourceFactory.MAPNIK)


                        this.visibility = View.VISIBLE
                        setMultiTouchControls(true)


                        val myLocationOverlay =
                            MyLocationNewOverlay(GpsMyLocationProvider(context), this)
                        myLocationOverlay.enableMyLocation()
                        overlays.add(myLocationOverlay)


                        currentLocation?.let { map ->
                            controller.setZoom(16.0)
                            controller.setCenter(
                                org.osmdroid.util.GeoPoint(
                                    map.latitude,
                                    map.longitude
                                )
                            )
                        }
                    }*/

                })
        }
        Log.e("TAG", myLocation)
    }
}







