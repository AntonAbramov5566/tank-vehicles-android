package ru.abramov.tank_reference_system.viewmoodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import ru.abramov.tank_reference_system.data.db.entity.History
import ru.abramov.tank_reference_system.data.db.entity.Modifications
import ru.abramov.tank_reference_system.data.db.entity.Photos
import ru.abramov.tank_reference_system.data.db.entity.Specifications
import ru.abramov.tank_reference_system.data.db.entity.TankModel
import ru.abramov.tank_reference_system.data.db.entity.User
import ru.abramov.tank_reference_system.data.db.entity.VehicleClass
import ru.abramov.tank_reference_system.data.repository.TankRepository

class TankViewModel(private val repo: TankRepository) : ViewModel() {
    private val _searchQuery = MutableStateFlow("")
    private val _tanks = MutableStateFlow<List<TankModel>>(emptyList())
    private val _currentUser = MutableStateFlow<User?>(null)
    val currentUser: StateFlow<User?> = _currentUser
    private val _filterCaliber = MutableStateFlow<Int?>(null)
    private val _filterClassId = MutableStateFlow<Long?>(null)

    @OptIn(ExperimentalCoroutinesApi::class)
    val filteredTanks: Flow<List<TankModel>> =
        combine(_tanks, _searchQuery, _filterCaliber, _filterClassId) { tanks, query, cal, cls ->
            applyFilter(tanks, query, cal, cls)
        }

    suspend fun applyFilter(
        tanks: List<TankModel>,
        query: String,
        cal: Int?,
        cls: Long?
    ): List<TankModel> {
        var result = tanks.filter { query.isBlank() || it.name.contains(query, ignoreCase = true) }

        if (cal != null) {
            val byCaliber = repo.getByCaliber(cal).first()
            result = result.filter { it in byCaliber }
        }

        if (cls != null) {
            val byClass = repo.getByClass(cls).first()
            result = result.filter { it in byClass }
        }

        return result
    }

    fun getAllClasses(): Flow<List<VehicleClass>> = repo.getAllClasses()
    fun setFilterYear(year: Long?) { /* TODO */ }
    fun setFilterCaliber(caliber: Int?) { _filterCaliber.value = caliber }
    fun setFilterClass(classId: Long?) { _filterClassId.value = classId }
    fun clearFilters() {
        _filterCaliber.value = null
        _filterClassId.value = null
    }

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
            repo.seedAll()
            _tanks.value = repo.getAllTanks()
        }
    }
    fun setSearchQuery(query: String) {
        _searchQuery.value = query
    }
    fun getPhotos(tankId: Long): Flow<List<Photos>> =
        repo.getPhotosByTankId(tankId)
    fun getTankById(id: Long): Flow<TankModel?> = repo.getTankById(id)
    fun getSpecs(id: Long): Flow<Specifications?> = repo.getSpecsByTankId(id)
    fun getHistory(id: Long): Flow<List<History>> = repo.getHistoryByTankId(id)
    fun getModifications(id: Long): Flow<List<Modifications>> = repo.getModificationsByTankId(id)

    fun loadDetails(tankId: Long) {
        viewModelScope.launch {
            repo.seedAll()
        }
    }
}