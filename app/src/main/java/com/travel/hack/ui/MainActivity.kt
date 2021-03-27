package com.travel.hack.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.travel.hack.R
import com.travel.hack.di.globalNavScopeId
import com.travel.hack.model.Prefs
import com.travel.hack.ui.common.BaseFragment
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.getKoin
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {

    private val prefs: Prefs by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navHostFragment = mainActivityNavHostFragment as NavHostFragment
        val graphInflater = navHostFragment.navController.navInflater
        val navGraph = graphInflater.inflate(R.navigation.main)
        val navController = navHostFragment.navController
        when {
            prefs.name != null && prefs.name.orEmpty().isNotBlank() -> navGraph.startDestination =
                R.id.citiesFragment
            else -> navGraph.startDestination = R.id.usernameFragment
        }
        navController.graph = navGraph
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