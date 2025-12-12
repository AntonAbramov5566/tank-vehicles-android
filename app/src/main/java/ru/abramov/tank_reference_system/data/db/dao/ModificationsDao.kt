package ru.abramov.tank_reference_system.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import ru.abramov.tank_reference_system.data.db.entity.Modifications

@Dao
interface ModificationsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(m: Modifications)

    @Query("SELECT * FROM modifications WHERE base_model_id = :tankId")
    fun getModificationsByTankId(tankId: Long): Flow<List<Modifications>>
}