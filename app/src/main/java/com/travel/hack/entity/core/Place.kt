package com.travel.hack.entity.core

data class Place(
    val id: Int,
    val title: String,
    val type: String,
    val description: String,
    val lon: Float,
    val lat: Float,
    val image: String,
)

fun getEmptyPlace() = Place(0, "title", "type", "description", 0f, 0f, "")