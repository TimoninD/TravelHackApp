package com.travel.hack.di

import com.travel.hack.viewmodel.OnBoardingViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object ViewModelsModule {
    val module = module {
        viewModel { OnBoardingViewModel() }
    }
}