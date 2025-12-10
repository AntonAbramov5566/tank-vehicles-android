package ru.abramov.tank_reference_system.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ru.abramov.tank_reference_system.data.db.entity.TankModel

@Dao
interface TankDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(tank: TankModel): Long

    @Query("SELECT * FROM tank_models")
    suspend fun getAll(): List<TankModel>

    @Query("SELECT * FROM tank_models WHERE id = :id")
    suspend fun getById(id: Int): TankModel?

    @Query("SELECT * FROM tank_models WHERE name LIKE '%' || :query || '%'")
    suspend fun searchByName(query: String): List<TankModel>

    @Query("SELECT * FROM tank_models WHERE vehicle_class_id = :classId")
    suspend fun getByClass(classId: Int): List<TankModel>
}