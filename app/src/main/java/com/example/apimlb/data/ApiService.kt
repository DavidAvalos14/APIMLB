package com.example.apimlb.data

import com.example.apimlb.model.StandingResponse
import com.example.apimlb.model.StandingTeam
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://statsapi.mlb.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val api: ApiService = retrofit.create(ApiService::class.java)
}

interface ApiService {
    // ... tus otras llamadas a la API

    @GET("api/v1/standings")
    suspend fun getStandings(
        @Query("leagueId") leagueId: String = "103,104",
        @Query("season") season: Int,
        @Query("standingsType") standingsType: String = "regularSeason"
    ): StandingResponse
}
