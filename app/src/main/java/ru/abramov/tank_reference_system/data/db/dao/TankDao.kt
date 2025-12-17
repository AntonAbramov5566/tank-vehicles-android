package ru.abramov.tank_reference_system.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import ru.abramov.tank_reference_system.data.db.entity.Specifications
import ru.abramov.tank_reference_system.data.db.entity.TankModel

@Dao
interface TankDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(tank: TankModel): Long

    @Query("SELECT * FROM tank_models")
    suspend fun getAll(): List<TankModel>

    @Query("SELECT * FROM tank_models WHERE id = :id")
    fun getById(id: Long): Flow<TankModel?>

    @Query("SELECT * FROM tank_models WHERE name LIKE '%' || :query || '%'")
    suspend fun searchByName(query: String): List<TankModel>

    @Query("SELECT * FROM tank_models WHERE vehicle_class_id = :classId")
    fun getByClass(classId: Long): Flow<List<TankModel>>

    @Query(
        """
    SELECT tm.* FROM tank_models tm
    INNER JOIN specifications s ON tm.id = s.tank_model_id
    WHERE s.main_gun_caliber_mm = :caliber AND tm.vehicle_class_id = :classId
    """
    )
    fun getByCaliberAndClass(caliber: Int, classId: Long): Flow<List<TankModel>>

    @Query(
        """
    SELECT tm.* FROM tank_models tm
    INNER JOIN specifications s ON tm.id = s.tank_model_id
    WHERE s.main_gun_caliber_mm = :caliber
    """
    )
    fun getByCaliber(caliber: Int): Flow<List<TankModel>>

    @Query("SELECT * FROM specifications WHERE tank_model_id = :tankId")
    fun getSpecsByTankId(tankId: Long): Flow<Specifications?>
}