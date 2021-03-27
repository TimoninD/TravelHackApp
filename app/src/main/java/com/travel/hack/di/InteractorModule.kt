package com.travel.hack.di

import com.travel.hack.model.interactors.TravelHackInteractor
import org.koin.dsl.module

object InteractorModule {
    val module = module {
        single { TravelHackInteractor(get(), get()) }
    }
}