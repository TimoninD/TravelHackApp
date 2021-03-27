package com.travel.hack.entity.core

import com.travel.hack.entity.dto.PlaceDto

data class Place(
    val id: Int,
    val title: String,
    val type: String,
    val description: String,
    val lon: Float,
    val lat: Float,
    val image: String,
)

fun Place.toPlaceDto() = PlaceDto(
    id = this.id,
    title = this.title,
    type = this.type,
    description = this.description,
    lon = this.lon,
    lat = this.lat,
    image = this.description
)

fun getEmptyPlace() = Place(0, "title", "type", "description", 0f, 0f, "")