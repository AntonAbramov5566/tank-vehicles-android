package ru.abramov.tank_reference_system.data.repository

import android.annotation.SuppressLint
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import ru.abramov.tank_reference_system.data.db.AppDatabase
import ru.abramov.tank_reference_system.data.db.entity.Country
import ru.abramov.tank_reference_system.data.db.entity.TankModel
import ru.abramov.tank_reference_system.data.db.entity.User
import ru.abramov.tank_reference_system.data.db.entity.VehicleClass
import ru.abramov.tank_reference_system.data.storage.TankStorage
import ru.abramov.tank_reference_system.data.storage.UserStorage
import ru.abramov.tank_reference_system.data.storage.VehicleClassStorage

class TankRepository(private val db: AppDatabase) {
    @SuppressLint("SuspiciousIndentation")
    suspend fun seedAll(){
        if (db.tankDao().getAll().isNotEmpty())return

        seedVehicleClasses()
        seedCountries()
        seedUsers()

        insertFullSet(TankStorage.fullSetT72())
        insertFullSet(TankStorage.fullSetIS7())
        insertFullSet(TankStorage.fullSetIS2())
        insertFullSet(TankStorage.fullSetIS3())
        insertFullSet(TankStorage.fullSetKV1())
        insertFullSet(TankStorage.fullSetKV2())
        insertFullSet(TankStorage.fullSetSU100())
        insertFullSet(TankStorage.fullSetSU152())
        insertFullSet(TankStorage.fullSetSU85())
        insertFullSet(TankStorage.fullSetT14())
        insertFullSet(TankStorage.fullSetT34())
        insertFullSet(TankStorage.fullSetT90())
    }

    suspend fun seedUsers() {
        if (db.userDao().getAll().isNotEmpty()) return
        UserStorage.defaultUsers().forEach { db.userDao().insert(it) }
    }

    suspend fun seedVehicleClasses() {
        VehicleClassStorage.defaultClasses().forEach { db.vehicleClassDao().insert(it) }
    }
    private suspend fun seedCountries() {
        if (db.countryDao().getAll().isNotEmpty()) return
        db.countryDao().insert(Country(name = "СССР", code = "USSR", id = 1))
        db.countryDao().insert(Country(name = "РФ", code = "RF", id = 2))
    }

    private suspend fun insertFullSet(set: TankStorage.TankComplete) = coroutineScope {

        val classId: Int = db.vehicleClassDao().getAll().first().first { it.id == set.tank.vehicle_class_id }.id
        val countryId: Int = db.countryDao().getAll().first { it.id == set.tank.country_id }.id

        val tankId = db.tankDao().insert(
            set.tank.copy(vehicle_class_id = classId, country_id = countryId)
        )

        launch { db.specificationsDao().insert(set.specifications.copy(tank_model_id = tankId)) }
        launch { set.history.forEach { db.historyDao().insert(it.copy(tank_model_id = tankId)) } }
        launch { set.photos.forEach { db.photoDao().insert(it.copy(tank_model_id = tankId)) } }
        launch { set.modifications.forEach { db.modificationsDao().insert(it.copy(base_model_id = tankId)) } }
    }
    fun getByCaliber(caliber: Int): Flow<List<TankModel>> = db.tankDao().getByCaliber(caliber)
    fun getByClass(classId: Long): Flow<List<TankModel>> = db.tankDao().getByClass(classId)
    fun getByCaliberAndClass(caliber: Int, classId: Long): Flow<List<TankModel>> =
        db.tankDao().getByCaliberAndClass(caliber, classId)
    suspend fun getAllTanks() = db.tankDao().getAll()
    fun getTankById(id: Long) = db.tankDao().getById(id)
    fun getSpecsByTankId(id: Long) = db.tankDao().getSpecsByTankId(id)
    fun getHistoryByTankId(id: Long) = db.historyDao().getHistoryByTankId(id)
    fun getModificationsByTankId(id: Long) = db.modificationsDao().getModificationsByTankId(id)
    fun getPhotosByTankId(id: Long) = db.photoDao().getByTankId(id)
    fun getAllClasses(): Flow<List<VehicleClass>> = db.vehicleClassDao().getAll()
    suspend fun login(username: String, password: String): User? {
        val hash = UserStorage.hash(password)
        return db.userDao().authenticate(username, hash)
    }

    suspend fun getPrimaryPhoto(id: Int) = db.photoDao().getPrimaryPhoto(id)
    suspend fun searchTanks(query: String) = db.tankDao().searchByName(query)
}