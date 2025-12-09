package com.example.apimlb.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.apimlb.data.RetrofitClient
import com.example.apimlb.model.League
import com.example.apimlb.model.LeagueInfo
import com.example.apimlb.model.StandingRecord
import kotlinx.coroutines.launch

class StandingsViewModel : ViewModel() {

    private fun getLeagueNameByDivision(divisionId: Int?): String {
        return when (divisionId) {
            200, 201, 202 -> "American League"
            203, 204, 205 -> "National League"
            else -> "Unknown League"
        }
    }


    var standingsRecords by mutableStateOf<List<StandingRecord>>(emptyList())
        private set

    var isLoading by mutableStateOf(false)
    var errorMessage by mutableStateOf<String?>(null)

    fun loadStandings(season: Int) {
        viewModelScope.launch {
            try {
                isLoading = true
                errorMessage = null

                val response = RetrofitClient.api.getStandings(season = season)

                val processed = response.records.map { record ->

                    val leagueName = getLeagueNameByDivision(record.division?.id)

                    record.copy(
                        division = record.division.copy(
                            name = record.division.name ?: "-- - --",
                            league = record.division?.league ?: LeagueInfo(
                                id = 0,
                                name = leagueName
                            )
                        )
                    )
                }



                standingsRecords = processed

            } catch (e: Exception) {
                errorMessage = e.message ?: "Unexpected error"
                e.printStackTrace()
            } finally {
                isLoading = false
            }
        }
    }

}
