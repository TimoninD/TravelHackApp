package com.travel.hack.viewmodel

import androidx.lifecycle.MutableLiveData
import com.travel.hack.entity.core.Place
import com.travel.hack.model.interactors.SightsInteractor
import com.travel.hack.viewmodel.common.BaseViewModel
import kotlinx.coroutines.launch
import org.koin.core.inject

class OnBoardingViewModel : BaseViewModel() {

    private val interactor: SightsInteractor by inject()

    val listSelectedPlace: MutableList<Place> = mutableListOf()

    val sightsLiveData: MutableLiveData<List<Place>> = MutableLiveData()

    init {
        getSights()
    }

    private fun getSights() {
        coroutineScope.launch {
            sightsLiveData.postValue(interactor.getAllSights())
        }
    }

}