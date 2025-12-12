package ru.abramov.tank_reference_system.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import ru.abramov.tank_reference_system.data.db.entity.Modifications

@Dao
interface ModificationsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(m: Modifications)
}