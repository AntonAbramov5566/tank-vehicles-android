package ru.abramov.tank_reference_system.data.repository

import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import ru.abramov.tank_reference_system.data.db.AppDatabase
import ru.abramov.tank_reference_system.data.db.entity.Country
import ru.abramov.tank_reference_system.data.db.entity.VehicleClass
import ru.abramov.tank_reference_system.data.storage.TankStorage

class TankRepository(private val db: AppDatabase) {
    suspend fun seedSingleTank() = coroutineScope {
        if (db.tankDao().getAll().isNotEmpty()) return@coroutineScope

        val set = TankStorage.fullSet()

        // 1. справочники
        val classId = async { db.vehicleClassDao().insert(VehicleClass(name = "Основной боевой танк")) }.await()
        val countryId = async { db.countryDao().insert(Country(name = "СССР", code = "USSR")) }.await()

        // 2. танк
        val tankId = db.tankDao().insert(set.tank.copy(vehicle_class_id = classId, country_id = countryId))

        // 3. зависимые таблицы
        launch { db.specificationsDao().insert(set.specifications.copy(tank_model_id = tankId)) }
        launch { set.history.forEach { db.historyDao().insert(it.copy(tank_model_id = tankId)) } }
        launch { set.photos.forEach { db.photoDao().insert(it.copy(tank_model_id = tankId)) } }
        launch { set.modifications.forEach { db.modificationsDao().insert(it.copy(base_model_id = tankId)) } }
    }

    suspend fun getAllTanks() = db.tankDao().getAll()
    suspend fun getTankById(id: Int) = db.tankDao().getById(id)
    suspend fun getSpecsByTankId(id: Int) = db.tankDao().getSpecsByTankId(id)
    suspend fun getPhotosByTankId(id: Int) = db.photoDao().getByTankId(id)
    suspend fun getPrimaryPhoto(id: Int) = db.photoDao().getPrimaryPhoto(id)
    suspend fun searchTanks(query: String) = db.tankDao().searchByName(query)
}