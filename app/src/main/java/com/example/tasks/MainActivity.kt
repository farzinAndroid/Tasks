package com.example.tasks

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.IntentSender
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.core.app.ActivityCompat
import com.example.tasks.task4.MyMap
import com.example.tasks.task4.PermissionConstants.PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION
import com.example.tasks.task4.PermissionConstants.REQUEST_CHECK_SETTINGS
import com.example.tasks.ui.theme.TasksTheme
import com.google.android.gms.common.api.ResolvableApiException
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.LocationSettingsRequest
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {


    private val locationRequest = LocationRequest.create().apply {
        interval = 5000
        fastestInterval = 3000
        priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        maxWaitTime = 10000
    }

    private var locationPermissionGranted by mutableStateOf(false)
    private var locationSettingsEnabled by mutableStateOf(false)

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "UnrememberedMutableState")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TasksTheme {

                checkLocationPermission()
                checkIfLocationEnabled()


                MyMap(
                    locationPermissionGranted = locationPermissionGranted,
                    locationSettingsEnabled = locationSettingsEnabled,
                    locationRequest = locationRequest
                )


            }
        }

    }

    private val locationSettingsLauncher =
        registerForActivityResult(ActivityResultContracts.StartIntentSenderForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                locationSettingsEnabled = true
            }
        }

    //for MyMap
    private fun checkIfLocationEnabled() {
        val locationSettingsRequest = LocationSettingsRequest.Builder()
            .addLocationRequest(locationRequest)
            .build()

        val settingsClient = LocationServices.getSettingsClient(this)
        val locationSettingsResponseTask =
            settingsClient.checkLocationSettings(locationSettingsRequest)

        // Check if the location services are enabled
        locationSettingsResponseTask.addOnSuccessListener {
            locationSettingsEnabled = true
        }
        locationSettingsResponseTask.addOnFailureListener { exception ->
            if (exception is ResolvableApiException) {
                try {

                    val intentSenderRequest =
                        IntentSenderRequest.Builder(exception.resolution).build()
                    locationSettingsLauncher.launch(intentSenderRequest)

                } catch (sendEx: IntentSender.SendIntentException) {

                }
            }
        }


    }

    private val requestPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted: Boolean ->
            if (isGranted) {
                locationPermissionGranted = true
            }
        }

    // for MyMap
    private fun checkLocationPermission() {


        // Check if the location permission is granted
        if (ActivityCompat.checkSelfPermission(
                this,
                android.Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            locationPermissionGranted = false
            requestPermissionLauncher.launch(
                android.Manifest.permission.ACCESS_FINE_LOCATION
            )
        } else {
            locationPermissionGranted = true

        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION -> {
                if (resultCode == Activity.RESULT_OK) {
                    locationPermissionGranted = true
                }
            }

            REQUEST_CHECK_SETTINGS -> {
                if (requestCode == Activity.RESULT_OK) {
                    locationSettingsEnabled = true
                }
            }
        }

    }
}









