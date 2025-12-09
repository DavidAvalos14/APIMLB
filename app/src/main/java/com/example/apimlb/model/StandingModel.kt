package com.example.apimlb.model

import com.google.gson.annotations.SerializedName

// --- MODELOS COMPLETOS Y CORRECTOS PARA STANDINGS ---

// 1. El objeto principal de la respuesta de la API
data class StandingResponse(
    val records: List<StandingRecord>
)

// 2. Cada "record" en la lista representa una división
data class StandingRecord(
    val division: DivisionInfo,
    val teamRecords: List<StandingTeam>
)

// 3. Este es el modelo de equipo, que ahora contiene objetos anidados
data class StandingTeam(
    @SerializedName("team")
    val team: TeamInfo2, // <- Aquí pones el modelo que sí tiene `name`
    @SerializedName("leagueRecord")
    val leagueRecord: LeagueRecord,
    @SerializedName("gamesBack")
    val gamesBack: String
)


// 4. Modelo para el objeto anidado "team"
data class TeamInfo2(
    @SerializedName("id")
    val id: Int,

    @SerializedName("name")
    val name: String
)

// 5. Modelo para el objeto anidado "leagueRecord"
data class LeagueRecord(
    @SerializedName("wins")
    val wins: Int,

    @SerializedName("losses")
    val losses: Int
)

data class DivisionInfo(
    val id: Int,
    val name: String?,
    val league: LeagueInfo,
    val customLeague: League? = null
)

data class LeagueInfo(
    val id: Int,
    val name: String    // "American League", "National League"
)
