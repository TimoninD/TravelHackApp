package com.travel.hack.di

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.travel.hack.BuildConfig.BASE_URL
import com.travel.hack.model.Prefs
import com.travel.hack.model.server.TravelHackApi
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object NetworkModule {
    val module = module {
        factory { provideDefaultOkhttpClient(androidContext()) }
        single { provideTravelHackApi(provideRetrofit(androidContext(), BASE_URL)) }
    }

    private fun provideDefaultOkhttpClient(context: Context): OkHttpClient.Builder {
        return OkHttpClient.Builder()
            .addInterceptor(ChuckerInterceptor(context))
            .readTimeout(90, TimeUnit.SECONDS)
            .connectTimeout(90, TimeUnit.SECONDS)
    }

    private fun provideRetrofit(context: Context, baseUrl: String): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(provideDefaultOkhttpClient(context).build())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun provideTravelHackApi(retrofit: Retrofit): TravelHackApi =
        retrofit.create(TravelHackApi::class.java)
}