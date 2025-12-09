package com.example.apimlb.model

data class ScheduleResponse(
    val dates: List<ScheduleDate>
)

data class ScheduleDate(
    val date: String,
    val games: List<MlbGame>
)

data class MlbGame(
    val gamePk: Int,
    val gameDate: String,
    val status: GameStatus,
    val teams: Teams
)

data class GameStatus(
    val detailedState: String
)

data class Teams(
    val away: TeamInfo,
    val home: TeamInfo
)

data class TeamInfo(
    val team: MlbTeam,
    val score: Int?
)

data class MlbTeam(
    val id: Int,
    val name: String
)
