package com.example.tasks.task4

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.provider.Settings
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.core.content.ContextCompat
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.mylocation.GpsMyLocationProvider
import org.osmdroid.views.overlay.mylocation.MyLocationNewOverlay

class MyLocationManager(
    private val context: Context,
    private val onLocationChangedEvent: (Location) -> Unit,
    private val onLocationError: (String) -> Unit
) {

    private val locationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager

    private val locationListener = object : LocationListener {
        override fun onLocationChanged(location: Location) {
            onLocationChangedEvent(location)
        }

        @Deprecated("Deprecated in Java")
        override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {}

    }

    fun startLocationUpdates(mapView: MapView, activity: Activity) {
        if (ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            onLocationError("Permission not granted")
            return
        }



        if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            onLocationError("Location services disabled")
            // Create a new intent to open the location settings
            val intent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
            // Start the activity for result, pass an arbitrary request code
            activity.startActivityForResult(intent, 1234)
            return
        }

        // Create a new LocationOverlay
        val locationOverlay = MyLocationNewOverlay(GpsMyLocationProvider(context), mapView)

        // Add the LocationOverlay to the MapView and enable it
        mapView.overlays.add(locationOverlay)
        mapView.controller.setZoom(12.0)
        locationOverlay.enableMyLocation()


        locationManager.requestLocationUpdates(
            LocationManager.GPS_PROVIDER,
            0L,
            0f,
            object : LocationListener {
                override fun onLocationChanged(location: Location) {
                    onLocationChangedEvent(location)
//                    mapView.controller.setCenter(GeoPoint(location))
                }

                override fun onProviderEnabled(provider: String) {}
                override fun onProviderDisabled(provider: String) {}
                override fun onStatusChanged(provider: String, status: Int, extras: Bundle) {}
            }
        )
    }

    fun stopLocationUpdates() {
        locationManager.removeUpdates(locationListener)
    }


    fun isGpsEnabled(): Boolean {
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
    }
}
