package com.travel.hack.ui

import android.Manifest
import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.google.android.gms.location.LocationServices
import com.travel.hack.R
import com.travel.hack.di.globalNavScopeId
import com.travel.hack.model.Prefs
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.getKoin
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {

    private val prefs: Prefs by inject()

    private val fusedLocationClient by lazy {
        LocationServices.getFusedLocationProviderClient(baseContext)
    }

    @SuppressLint("MissingPermission")
    private val requestPermissionLauncher =
        registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { isGranted ->

            if (isGranted) {
                fusedLocationClient.lastLocation.addOnSuccessListener {
                    prefs.lat = it.latitude.toFloat()
                    prefs.lng = it.longitude.toFloat()
                }
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.mainActivityNavHostFragment) as NavHostFragment
        val graphInflater = navHostFragment.navController.navInflater
        val navGraph = graphInflater.inflate(R.navigation.main)
        val navController = navHostFragment.navController
        when {
            prefs.name != null && prefs.name.orEmpty().isNotBlank() -> navGraph.startDestination =
                R.id.citiesFragment
            else -> navGraph.startDestination = R.id.usernameFragment
        }
        navController.graph = navGraph
        requestPermissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
    }


    override fun onStop() {
        getKoin().deleteScope(globalNavScopeId)
        super.onStop()
    }

    override fun onBackPressed() {
        if (mainActivityNavHostFragment.findNavController().currentDestination?.id == R.id.citiesFragment) {
            return
        } else {
            super.onBackPressed()
        }
    }


}