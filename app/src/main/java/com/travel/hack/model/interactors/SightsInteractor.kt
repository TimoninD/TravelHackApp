package com.travel.hack.model.interactors

import com.travel.hack.entity.core.Place
import com.travel.hack.entity.core.getEmptyPlace

class SightsInteractor {

    fun getAllSights(): List<Place> {
        return listOf(
            getEmptyPlace(),
            getEmptyPlace(),
            getEmptyPlace(),
            getEmptyPlace(),
            getEmptyPlace(),
            getEmptyPlace()
        )
    }
}