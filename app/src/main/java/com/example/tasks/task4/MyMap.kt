package com.example.tasks.task4

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.os.Looper
import android.preference.PreferenceManager
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import org.osmdroid.api.IMapController
import org.osmdroid.config.Configuration
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.Marker

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnrememberedMutableState", "UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MyMap(
    locationPermissionGranted: Boolean,
    locationSettingsEnabled: Boolean,
    locationRequest:LocationRequest
) {
    if (locationPermissionGranted && locationSettingsEnabled) {

        var mapView: MapView? by mutableStateOf(null)
        var mapController: IMapController? by mutableStateOf(null)
        var currentLocation by mutableStateOf(GeoPoint(0.0, 0.0))

        var lat by remember {
            mutableStateOf("loading")
        }

        var long by remember {
            mutableStateOf("loading")
        }

        val context = LocalContext.current
        LaunchedEffect(Unit) {
            Configuration.getInstance().load(context, PreferenceManager.getDefaultSharedPreferences(context))
            mapView = MapView(context).apply {
                setMultiTouchControls(true)
                isTilesScaledToDpi = true
            }
            mapController = mapView?.controller.apply {
                this?.setZoom(15.0)
                this?.setCenter(currentLocation)
            }
        }

        Scaffold(
            modifier = Modifier
                .fillMaxSize(),
            topBar = {
                Column {
                    Text(text = "lat : $lat", color = Color.Black)
                    Text(text = "long : $long",color = Color.Black)
                }
            }
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
            ) {

                AndroidView(
                    modifier = Modifier.fillMaxSize(),
                    factory = { mapView ?: MapView(context).apply { setMultiTouchControls(true) } },
                    update = { map ->
                        map.overlays.clear()
                        val marker = Marker(map)
                        marker.position = currentLocation
                        map.overlays.add(marker)
                        map.invalidate()
                    }
                )
            }

        }




        LaunchedEffect(Unit) {
            val fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(context)
            val locationCallback = object : LocationCallback() {
                override fun onLocationResult(locationResult: LocationResult) {
                    locationResult.locations.forEach { location ->
                        currentLocation = GeoPoint(location.latitude, location.longitude)
                        mapController?.animateTo(currentLocation)
                        lat = location.latitude.toString()
                        long = location.longitude.toString()
                    }
                }
            }
            if (ActivityCompat.checkSelfPermission(
                    context,
                    Manifest.permission.ACCESS_FINE_LOCATION
                ) == PackageManager.PERMISSION_GRANTED
            ) {
                fusedLocationProviderClient.requestLocationUpdates(
                    locationRequest,
                    locationCallback,
                    Looper.getMainLooper()
                )
            }
        }
    }
}