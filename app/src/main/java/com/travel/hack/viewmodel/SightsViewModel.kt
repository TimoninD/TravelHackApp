package com.travel.hack.viewmodel

import androidx.lifecycle.MutableLiveData
import com.travel.hack.entity.core.Place
import com.travel.hack.model.interactors.TravelHackInteractor
import com.travel.hack.viewmodel.common.BaseViewModel
import kotlinx.coroutines.launch
import org.koin.core.inject

class SightsViewModel : BaseViewModel() {

    private val interactor: TravelHackInteractor by inject()

    val sights: MutableLiveData<List<Place>> = MutableLiveData()

    val selectedSightsId: MutableList<Int> = mutableListOf()

    init {
        getRecommendedSights()
    }

    private fun getRecommendedSights() {
        coroutineScope.launch {
            try {
                val listPlaces = interactor.getRecommendedSights()
                selectedSightsId.addAll(listPlaces.map { it.id })
                sights.postValue(listPlaces)
            } catch (t: Throwable) {
                t.printStackTrace()
            }
        }
    }
}