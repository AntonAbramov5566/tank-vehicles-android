package ru.abramov.tank_reference_system.data.db.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "tank_models",
    foreignKeys = [
        ForeignKey(
            entity = VehicleClass::class,
            parentColumns = ["id"],
            childColumns = ["vehicle_class_id"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = Country::class,
            parentColumns = ["id"],
            childColumns = ["country_id"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [
        Index("vehicle_class_id"),
        Index("country_id")
    ]
)
data class TankModel(
    @PrimaryKey(autoGenerate = true) val id: Long = 0L,
    val name: String,
    val official_name: String?,
    val vehicle_class_id: Long,
    val country_id: Long,
    val development_start_year: Int?,
    val production_start_year: Int?,
    val production_end_year: Int?,
    val description: String?,
    val created_at: Long = System.currentTimeMillis(),
    val updated_at: Long = System.currentTimeMillis()
)