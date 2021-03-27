package com.travel.hack.di

import com.travel.hack.model.interactors.SightsInteractor
import org.koin.dsl.module

object InteractorModule {
    val module = module {
        single { SightsInteractor(get(), get()) }
    }
}