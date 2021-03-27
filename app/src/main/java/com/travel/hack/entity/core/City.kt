package com.travel.hack.entity.core

data class City(
    val cityId: Int,
    val name: String,
    val image: String,
    var isSelected: Boolean = false
)