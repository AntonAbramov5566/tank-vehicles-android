package ru.abramov.tank_reference_system.viewmoodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.abramov.tank_reference_system.data.db.entity.TankModel
import ru.abramov.tank_reference_system.data.repository.TankRepository

class TankViewModel(private val repo: TankRepository) : ViewModel() {
    var tanks by mutableStateOf<List<TankModel>>(emptyList())
        private set

    var searchQuery by mutableStateOf("")
        private set

    fun loadTanks() {
        viewModelScope.launch {
            tanks = repo.getAllTanks()
        }
    }

    fun searchTanks(query: String) {
        viewModelScope.launch {
            tanks = if (query.isBlank()) {
                repo.getAllTanks()
            } else {
                repo.searchTanks(query)
            }
        }
    }
}