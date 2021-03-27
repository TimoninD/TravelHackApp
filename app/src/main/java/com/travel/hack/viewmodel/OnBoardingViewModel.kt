package com.travel.hack.viewmodel

import androidx.lifecycle.MutableLiveData
import com.travel.hack.entity.core.Place
import com.travel.hack.model.interactors.TravelHackInteractor
import com.travel.hack.util.SingleLiveData
import com.travel.hack.viewmodel.common.BaseViewModel
import kotlinx.coroutines.launch
import org.koin.core.inject

class OnBoardingViewModel : BaseViewModel() {

    private val interactor: TravelHackInteractor by inject()

    val listSelectedPlace: MutableList<Place> = mutableListOf()

    val sightsLiveData: MutableLiveData<List<Place>> = MutableLiveData()

    val navigateToCity: SingleLiveData<Boolean> = SingleLiveData()

    val progress: SingleLiveData<Boolean> = SingleLiveData()

    init {
        getSights()
    }

    fun saveSights() {
        coroutineScope.launch {
            try {
                interactor.saveSelectedSights(listSelectedPlace)
                navigateToCity.postValue(true)
            } catch (t: Throwable) {
                t.printStackTrace()
            }
        }
    }

    private fun getSights() {
        coroutineScope.launch {
            try {
                progress.postValue(true)
                val result = interactor.getAllSights()
                sightsLiveData.postValue(result)
                progress.postValue(false)
            } catch (t: Throwable) {
                progress.postValue(false)
                t.printStackTrace()
            }
        }
    }

}