package com.travel.hack.viewmodel

import com.travel.hack.model.interactors.TravelHackInteractor
import com.travel.hack.viewmodel.common.BaseViewModel
import org.koin.core.inject

class MapViewModel : BaseViewModel() {

    private val interactor: TravelHackInteractor by inject()

}