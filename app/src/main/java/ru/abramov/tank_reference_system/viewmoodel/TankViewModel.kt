package ru.abramov.tank_reference_system.viewmoodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import ru.abramov.tank_reference_system.data.db.entity.Photos
import ru.abramov.tank_reference_system.data.db.entity.TankModel
import ru.abramov.tank_reference_system.data.repository.TankRepository

class TankViewModel(private val repo: TankRepository) : ViewModel() {
    private val _tanks = MutableStateFlow<List<TankModel>>(emptyList())
    val tanks: StateFlow<List<TankModel>> = _tanks

    var searchQuery by mutableStateOf("")
        private set

    fun loadTanks() {
        viewModelScope.launch {
            repo.seedSingleTank()
            _tanks.value = repo.getAllTanks()
        }
    }

    fun getPhotos(tankId: Long): Flow<List<Photos>> =
        repo.getPhotosByTankId(tankId)
    fun searchTanks(query: String) {
        viewModelScope.launch {
            _tanks.value = if (query.isBlank()) {
                repo.getAllTanks()
            } else {
                repo.searchTanks(query)
            }
        }
    }
}