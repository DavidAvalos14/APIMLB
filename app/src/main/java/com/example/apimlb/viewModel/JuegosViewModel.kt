package com.example.apimlb.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.apimlb.data.RetrofitInstance
import com.example.apimlb.model.MlbGame
import kotlinx.coroutines.launch

class JuegosViewModel : ViewModel() {

    var games by mutableStateOf<List<MlbGame>>(emptyList())
        private set

    fun loadGames(date: String) {
        viewModelScope.launch {
            try {
                val response = RetrofitInstance.api.getGamesByDate(date = date)
                games = response.dates.firstOrNull()?.games ?: emptyList()
            } catch (e: Exception) {
                games = emptyList()
            }
        }
    }
}
