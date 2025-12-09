package com.example.apimlb.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.apimlb.data.ApiService
import com.example.apimlb.data.RetrofitClient
import com.example.apimlb.model.StandingRecord
import com.example.apimlb.model.StandingTeam
import com.example.apimlb.model.StandingResponse
import kotlinx.coroutines.launch

class StandingsViewModel : ViewModel() {

    var standingsRecords by mutableStateOf<List<StandingRecord>>(emptyList())
        private set

    var isLoading by mutableStateOf(false)
    var errorMessage by mutableStateOf<String?>(null)

    fun loadStandings(season: Int) {
        viewModelScope.launch {
            isLoading = true
            errorMessage = null
            try {
                val response: StandingResponse = RetrofitClient.api.getStandings(season = season)

                // Guardamos todos los registros tal cual vienen
                standingsRecords = response.records

            } catch (e: Exception) {
                errorMessage = "Error al cargar standings: ${e.message}"
                e.printStackTrace()
            } finally {
                isLoading = false
            }
        }
    }


}
