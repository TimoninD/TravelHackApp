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

    @GET("places/{username}")
    suspend fun getRecommendedSights(
        @Path("username") name: String,
        @Query("city_id") cityId: Int
    ): List<PlaceDto>

    @POST("routes")
    suspend fun optimalRoute(
        @Body body: List<Int>,
        @Query("lat") lat: Float,
        @Query("lon") lng: Float
    ): List<PlaceDto>

}