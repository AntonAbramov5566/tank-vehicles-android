package ru.abramov.tank_reference_system.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import ru.abramov.tank_reference_system.data.db.entity.History

@Dao
interface HistoryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(h: History)

    @Query("SELECT * FROM history WHERE tank_model_id = :tankId")
    fun getHistoryByTankId(tankId: Long): Flow<List<History>>
}