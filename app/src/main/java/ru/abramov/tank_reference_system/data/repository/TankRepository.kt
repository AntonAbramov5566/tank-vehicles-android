package ru.abramov.tank_reference_system.data.repository

import ru.abramov.tank_reference_system.data.db.AppDatabase

class TankRepository(private val db: AppDatabase) {
    suspend fun getAllTanks() = db.tankDao().getAll()
    suspend fun getTankById(id: Int) = db.tankDao().getById(id)
    suspend fun getSpecsByTankId(id: Int) = db.tankDao().getSpecsByTankId(id)
    suspend fun getPhotosByTankId(id: Int) = db.photoDao().getByTankId(id)
    suspend fun getPrimaryPhoto(id: Int) = db.photoDao().getPrimaryPhoto(id)
    suspend fun searchTanks(query: String) = db.tankDao().searchByName(query)
}