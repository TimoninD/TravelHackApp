package com.travel.hack.viewmodel

import androidx.lifecycle.MutableLiveData
import com.travel.hack.entity.core.City
import com.travel.hack.model.interactors.SightsInteractor
import com.travel.hack.viewmodel.common.BaseViewModel
import kotlinx.coroutines.launch
import org.koin.core.inject

class CitiesViewModel : BaseViewModel() {

    private val interactor: SightsInteractor by inject()

    val cities: MutableLiveData<List<City>> = MutableLiveData()

    var selectedCityId: Int? = null

    init {
        getCities()
    }

    private fun getCities() {
        coroutineScope.launch {
            val citiesList = interactor.getCities()
            cities.postValue(citiesList)
        }
    }
}