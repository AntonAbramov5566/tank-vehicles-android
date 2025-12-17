package ru.abramov.tank_reference_system.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import ru.abramov.tank_reference_system.data.db.entity.VehicleClass

@Dao
interface VehicleClassDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(vehicleClass: VehicleClass): Long

    @Query("SELECT * FROM vehicle_classes ORDER BY name")
    fun getAll(): Flow<List<VehicleClass>>
}