package ru.abramov.tank_reference_system.data.db.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "modifications",
    foreignKeys = [
        ForeignKey(
            entity = TankModel::class,
            parentColumns = ["id"],
            childColumns = ["base_model_id"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index("base_model_id")]
)
data class Modifications(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val base_model_id: Long,
    val modification_name: String,
    val description: String,
    val production_years: String,
    val changes: String,
    val created_at: Long = System.currentTimeMillis()
)