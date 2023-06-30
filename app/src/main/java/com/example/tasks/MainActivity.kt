package com.example.tasks

import android.annotation.SuppressLint
import android.content.IntentSender
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.core.app.ActivityCompat
import com.example.tasks.task4.MyMap
import com.example.tasks.task4.PermissionConstants.PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION
import com.example.tasks.task4.PermissionConstants.REQUEST_CHECK_SETTINGS
import com.example.tasks.ui.theme.TasksTheme
import com.google.android.gms.common.api.ResolvableApiException
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.LocationSettingsRequest
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {


    private val locationRequest = LocationRequest.create().apply {
        interval = 10000
        fastestInterval = 5000
        priority = LocationRequest.PRIORITY_HIGH_ACCURACY
    }

    private var locationPermissionGranted by mutableStateOf(false)
    private var locationSettingsEnabled by mutableStateOf(false)
    private var showLocation by mutableStateOf(false)


    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "UnrememberedMutableState")
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TasksTheme{

                checkIfLocationEnabled()
                checkLocationPermission()


                Scaffold(
                    modifier = Modifier
                        .fillMaxSize()
                ) {

                    if (!showLocation){
                        Button(
                            onClick = { showLocation = true }
                        ) {
                            Text(text = "Show Location")
                        }
                    }else{
                        MyMap(
                            locationPermissionGranted = locationPermissionGranted,
                            locationSettingsEnabled = locationSettingsEnabled,
                            locationRequest = locationRequest
                        )
                    }

                }





            }
        }

    }

    private fun checkIfLocationEnabled() {
        val locationSettingsRequest = LocationSettingsRequest.Builder()
            .addLocationRequest(locationRequest)
            .build()

        val settingsClient = LocationServices.getSettingsClient(this)
        val locationSettingsResponseTask = settingsClient.checkLocationSettings(locationSettingsRequest)

        // Check if the location services are enabled
        locationSettingsResponseTask.addOnSuccessListener {
            locationSettingsEnabled = true
        }
        locationSettingsResponseTask.addOnFailureListener { exception ->
            if (exception is ResolvableApiException) {
                try {
                    exception.startResolutionForResult(this, REQUEST_CHECK_SETTINGS)
                } catch (sendEx: IntentSender.SendIntentException) {
                    // Ignore the error.
                }
            }
        }


    }

    private fun checkLocationPermission(){


            // Check if the location permission is granted
            if (ActivityCompat.checkSelfPermission(
                    this,
                    android.Manifest.permission.ACCESS_FINE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                locationPermissionGranted = false
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),
                    PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION
                )
            } else {
                locationPermissionGranted = true

        }

    }
}









