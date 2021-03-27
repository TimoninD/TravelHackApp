package com.travel.hack.di

import com.travel.hack.model.Prefs
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

object AppModule {
    val module = module {
        single { Prefs(androidContext()) }
    }
}