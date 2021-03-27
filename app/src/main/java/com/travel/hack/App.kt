package com.travel.hack

import android.app.Application
import com.travel.hack.di.AppModule
import com.travel.hack.di.InteractorModule
import com.travel.hack.di.NetworkModule
import com.travel.hack.di.ViewModelsModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(
                listOf(
                    AppModule.module,
                    InteractorModule.module,
                    NetworkModule.module,
                    ViewModelsModule.module
                )
            )
        }
    }
}