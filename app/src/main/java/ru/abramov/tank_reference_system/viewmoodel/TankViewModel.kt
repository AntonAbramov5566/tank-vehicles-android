package ru.abramov.tank_reference_system.viewmoodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import ru.abramov.tank_reference_system.data.db.entity.History
import ru.abramov.tank_reference_system.data.db.entity.Modifications
import ru.abramov.tank_reference_system.data.db.entity.Photos
import ru.abramov.tank_reference_system.data.db.entity.Specifications
import ru.abramov.tank_reference_system.data.db.entity.TankModel
import ru.abramov.tank_reference_system.data.db.entity.User
import ru.abramov.tank_reference_system.data.repository.TankRepository

class TankViewModel(private val repo: TankRepository) : ViewModel() {
    private val _tanks = MutableStateFlow<List<TankModel>>(emptyList())
    val tanks: StateFlow<List<TankModel>> = _tanks
    private val _currentUser = MutableStateFlow<User?>(null)
    val currentUser: StateFlow<User?> = _currentUser

    fun login(username: String, password: String) {
        viewModelScope.launch {
            _currentUser.value = repo.login(username, password)
        }
    }

    fun logout() {
        _currentUser.value = null
    }

    val isFilterEnabled: StateFlow<Boolean> = currentUser.map { it != null }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(),
        initialValue = false
    )

    fun loadTanks() {
        viewModelScope.launch {
            repo.seedTanks()
            _tanks.value = repo.getAllTanks()
        }
    }
    fun searchTanks(query: String) {
        viewModelScope.launch {
            _tanks.value = if (query.isBlank()) {
                repo.getAllTanks()
            } else {
                repo.searchTanks(query)
            }
        }
    }
    fun getPhotos(tankId: Long): Flow<List<Photos>> =
        repo.getPhotosByTankId(tankId)
    fun getTankById(id: Long): Flow<TankModel?> = repo.getTankById(id)
    fun getSpecs(id: Long): Flow<Specifications?> = repo.getSpecsByTankId(id)
    fun getHistory(id: Long): Flow<List<History>> = repo.getHistoryByTankId(id)
    fun getModifications(id: Long): Flow<List<Modifications>> = repo.getModificationsByTankId(id)

    fun loadDetails(tankId: Long) {
        viewModelScope.launch {
            repo.seedTanks()
        }
    }
}