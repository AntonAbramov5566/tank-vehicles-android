package ru.abramov.tank_reference_system.data.repository

import android.annotation.SuppressLint
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import ru.abramov.tank_reference_system.data.db.AppDatabase
import ru.abramov.tank_reference_system.data.db.entity.Country
import ru.abramov.tank_reference_system.data.db.entity.User
import ru.abramov.tank_reference_system.data.db.entity.VehicleClass
import ru.abramov.tank_reference_system.data.storage.TankStorage
import ru.abramov.tank_reference_system.data.storage.UserStorage

class TankRepository(private val db: AppDatabase) {
    @SuppressLint("SuspiciousIndentation")
    suspend fun seedTanks(){
        if (db.tankDao().getAll().isNotEmpty())return

        seedUsers()
        // Т-72
        insertFullSet(TankStorage.fullSetT72())
        // ИС-7
        insertFullSet(TankStorage.fullSetIS7())
    }

    suspend fun seedUsers() {
        if (db.userDao().getAll().isNotEmpty()) return
        UserStorage.defaultUsers().forEach { db.userDao().insert(it) }
    }

    private suspend fun insertFullSet(set: TankStorage.TankComplete) = coroutineScope {
        val classId = db.vehicleClassDao().insert(VehicleClass(name = "Основной боевой танк"))
        val countryId = db.countryDao().insert(Country(name = "СССР", code = "USSR"))

        val tankId = db.tankDao().insert(
            set.tank.copy(vehicle_class_id = classId, country_id = countryId)
        )

        launch { db.specificationsDao().insert(set.specifications.copy(tank_model_id = tankId)) }
        launch { set.history.forEach { db.historyDao().insert(it.copy(tank_model_id = tankId)) } }
        launch { set.photos.forEach { db.photoDao().insert(it.copy(tank_model_id = tankId)) } }
        launch { set.modifications.forEach { db.modificationsDao().insert(it.copy(base_model_id = tankId)) } }
    }
    suspend fun getAllTanks() = db.tankDao().getAll()
    fun getTankById(id: Long) = db.tankDao().getById(id)
    fun getSpecsByTankId(id: Long) = db.tankDao().getSpecsByTankId(id)
    fun getHistoryByTankId(id: Long) = db.historyDao().getHistoryByTankId(id)
    fun getModificationsByTankId(id: Long) = db.modificationsDao().getModificationsByTankId(id)
    fun getPhotosByTankId(id: Long) = db.photoDao().getByTankId(id)

    suspend fun login(username: String, password: String): User? {
        val hash = UserStorage.hash(password)
        return db.userDao().authenticate(username, hash)
    }

    suspend fun getPrimaryPhoto(id: Int) = db.photoDao().getPrimaryPhoto(id)
    suspend fun searchTanks(query: String) = db.tankDao().searchByName(query)
}