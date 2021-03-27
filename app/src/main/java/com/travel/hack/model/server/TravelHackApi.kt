package com.travel.hack.model.server

import com.travel.hack.entity.dto.CityDto
import com.travel.hack.entity.dto.PlaceDto
import retrofit2.http.*

interface TravelHackApi {

    @GET("cities")
    suspend fun getCities(): List<CityDto>

    @GET("places")
    suspend fun getAllSights(): List<PlaceDto>

    @POST("places/{username}")
    suspend fun saveSelectedSights(
        @Path("username") name: String,
        @Body body: List<Int>
    )

    @GET("places/")
    suspend fun getRecommendedSights(
        @Query("city_id") cityId: Int
    ): List<PlaceDto>

    @POST("routes/{username}")
    suspend fun optimalRoute(
        @Path("username") name: String,
        @Body body: List<Int>
    ): List<PlaceDto>

}