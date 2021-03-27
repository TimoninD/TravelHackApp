package com.travel.hack.di

import com.travel.hack.viewmodel.CitiesViewModel
import com.travel.hack.viewmodel.MapViewModel
import com.travel.hack.viewmodel.OnBoardingViewModel
import com.travel.hack.viewmodel.SightsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object ViewModelsModule {
    val module = module {
        viewModel { OnBoardingViewModel() }
        viewModel { CitiesViewModel() }
        viewModel { MapViewModel() }
        viewModel { SightsViewModel() }
    }
}