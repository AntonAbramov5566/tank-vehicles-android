package ru.abramov.tank_reference_system.data.db.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "photos",
    foreignKeys = [
        ForeignKey(
            entity = TankModel::class,
            parentColumns = ["id"],
            childColumns = ["tank_model_id"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class Photos(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val tank_model_id: Int,
    val filename: String,
    val description: String?,
    val photo_type: String?,
    val is_primary: Boolean = false,
    val created_at: Long = System.currentTimeMillis()
)