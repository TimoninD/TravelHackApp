package com.travel.hack.entity.dto

import com.google.gson.annotations.SerializedName
import com.travel.hack.entity.core.City

data class CityDto(
    @SerializedName("id") val id: Int,
    @SerializedName("title") val name: String,
    @SerializedName("image_url") val image: String
)

fun CityDto.toCity() = City(
    id = id,
    name = name,
    image = image
)