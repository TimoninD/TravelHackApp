package com.travel.hack.viewmodel

import androidx.lifecycle.MutableLiveData
import com.travel.hack.entity.core.Place
import com.travel.hack.entity.core.PlaceIdList
import com.travel.hack.model.interactors.TravelHackInteractor
import com.travel.hack.viewmodel.common.BaseViewModel
import kotlinx.coroutines.launch
import org.koin.core.inject

class MapViewModel(private val placeIdList: PlaceIdList) : BaseViewModel() {

    private val interactor: TravelHackInteractor by inject()

    val places: MutableLiveData<List<Place>> = MutableLiveData()


    init {
        optimalRoute()
    }

    private fun optimalRoute() {
        coroutineScope.launch {
            val result = interactor.optimalRoute(placeIdList.list)
            places.postValue(result)
        }
    }
}