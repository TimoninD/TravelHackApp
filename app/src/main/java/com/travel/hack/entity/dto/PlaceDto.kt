package com.travel.hack.entity.dto

import com.google.gson.annotations.SerializedName
import com.travel.hack.entity.core.Place

data class PlaceDto(
    @SerializedName("id") val id: Int,
    @SerializedName("title") val title: String,
    @SerializedName("type") val type: String,
    @SerializedName("description") val description: String,
    @SerializedName("lon") val lon: Float,
    @SerializedName("lat") val lat: Float,
    @SerializedName("image_url") val image: String,
)

fun PlaceDto.toPlace() = Place(
    id = this.id,
    title = this.title,
    type = this.type,
    description = this.description,
    lon = this.lon,
    lat = this.lat,
    image = this.image
)