package com.example.tasks.task4

import android.Manifest
import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.content.IntentSender
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.provider.Settings
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.tasks.task4.PermissionConstants.LOCATION_SETTINGS_REQUEST_CODE
import com.google.android.gms.common.api.ResolvableApiException
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.LocationSettingsRequest
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.mylocation.GpsMyLocationProvider
import org.osmdroid.views.overlay.mylocation.MyLocationNewOverlay
import com.google.android.gms.location.LocationRequest

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
            ActivityCompat.requestPermissions(
                activity,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                PermissionConstants.REQUEST_CODE_PERMISSION
            )
            return
        }



        if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            onLocationError("Location services disabled")
            AlertDialog.Builder(context)
                .setTitle("Location services disabled")
                .setMessage("Do you want to enable location services?")
                .setPositiveButton("Yes") { _, _ ->
                    val intent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
                    activity.startActivityForResult(intent, PermissionConstants.REQUEST_CODE_PERMISSION)
                }
                .setNegativeButton("No") { _, _ -> }
                .show()
            return
        }


        val locationOverlay = MyLocationNewOverlay(GpsMyLocationProvider(context), mapView)


        mapView.overlays.add(locationOverlay)
        mapView.controller.setZoom(10.0)
        locationOverlay.enableMyLocation()


        locationManager.requestLocationUpdates(
            LocationManager.GPS_PROVIDER,
            0L,
            0f,
            locationListener
        )
    }

    fun stopLocationUpdates() {
        locationManager.removeUpdates(locationListener)
    }


    fun isGpsEnabled(): Boolean {
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
    }

    fun enableLocationServices(activity: Activity, mapView: MapView) {
        val locationRequest = LocationRequest.create()
            .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
            .setInterval(10 * 1000)
            .setFastestInterval(5 * 1000)
        val builder = LocationSettingsRequest.Builder()
            .addLocationRequest(locationRequest)
        val client = LocationServices.getSettingsClient(activity)
        val task = client.checkLocationSettings(builder.build())
        task.addOnSuccessListener {

            startLocationUpdates(mapView, activity)
        }
        task.addOnFailureListener { exception ->
            if (exception is ResolvableApiException) {

                try {
                    exception.startResolutionForResult(activity, LOCATION_SETTINGS_REQUEST_CODE)
                } catch (sendEx: IntentSender.SendIntentException) {

                }
            } else {

                onLocationError("Location services disabled")
            }
        }
    }
}
