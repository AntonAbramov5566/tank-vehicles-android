package ru.abramov.tank_reference_system.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ru.abramov.tank_reference_system.data.db.entity.Photos

@Dao
interface PhotoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(photo: Photos)

    @Query("SELECT * FROM photos WHERE tank_model_id = :tankId")
    suspend fun getByTankId(tankId: Int): List<Photos>

    @Query("SELECT * FROM photos WHERE tank_model_id = :tankId AND is_primary = 1 LIMIT 1")
    suspend fun getPrimaryPhoto(tankId: Int): Photos?
}