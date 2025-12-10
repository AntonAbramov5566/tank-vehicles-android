package ru.abramov.tank_reference_system.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "vehicle_classes")
data class VehicleClass(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val created_at: Long = System.currentTimeMillis()
)