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
        @Body body: List<Int>,
        @Path("username") name: String
    )

    @GET("places/{username}")
    suspend fun getRecommendedSights(
        @Query("city_id") cityId: Int,
        @Path("username") name: String
    ): List<PlaceDto>

    @POST("routes/{username}")
    suspend fun optimalRoute(
        @Body body: List<Int>,
        @Path("username") name: String
    ): List<PlaceDto>

}