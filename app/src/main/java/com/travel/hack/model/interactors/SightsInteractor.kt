package com.travel.hack.model.interactors

import com.travel.hack.entity.core.Place
import com.travel.hack.entity.dto.toCity
import com.travel.hack.entity.dto.toPlace
import com.travel.hack.model.Prefs
import com.travel.hack.model.server.TravelHackApi

class SightsInteractor(private val api: TravelHackApi, private val prefs: Prefs) {

    private val username = prefs.name ?: ""
    private val cityId = prefs.cityId


    suspend fun getAllSights() = api.getAllSights().map {
        it.toPlace()
    }

    suspend fun saveSelectedSights(list: List<Place>) =
        api.saveSelectedSights(list.map { it.id }, "username")

    suspend fun getCities() = api.getCities().map { it.toCity() }

    suspend fun getRecommendedSights() = api.getRecommendedSights(cityId, username)

    suspend fun optimalRoute(list: List<Place>) = api.optimalRoute(list.map { it.id }, username)
}