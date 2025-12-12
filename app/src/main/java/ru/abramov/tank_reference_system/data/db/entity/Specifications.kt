package ru.abramov.tank_reference_system.data.db.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "specifications",
    foreignKeys = [
        ForeignKey(
            entity = TankModel::class,
            parentColumns = ["id"],
            childColumns = ["tank_model_id"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index("tank_model_id")]
)
data class Specifications(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val tank_model_id: Long,
    val weight_kg: Int?,
    val length_m: Double?,
    val width_m: Double?,
    val height_m: Double?,
    val crew_count: Int?,
    val main_gun_caliber_mm: Int?,
    val secondary_weapons: String?,
    val armor_front_mm: Int?,
    val armor_side_mm: Int?,
    val armor_rear_mm: Int?,
    val engine_type: String?,
    val engine_power_hp: Int?,
    val max_speed_kmh: Int?,
    val range_km: Int?,
    val created_at: Long = System.currentTimeMillis(),
    val updated_at: Long = System.currentTimeMillis()
)