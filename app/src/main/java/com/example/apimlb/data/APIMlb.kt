package com.example.apimlb.data

import com.example.apimlb.model.ScheduleResponse
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://statsapi.mlb.com/api/v1/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val api: MlBApiService = retrofit.create(MlBApiService::class.java)
}

interface MlBApiService {
    @GET("schedule")
    suspend fun getGamesByDate(
        @Query("sportId") sportId: Int = 1,
        @Query("date") date: String
    ): ScheduleResponse
}
