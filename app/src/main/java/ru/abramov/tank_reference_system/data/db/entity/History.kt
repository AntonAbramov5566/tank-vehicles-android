package ru.abramov.tank_reference_system.data.db.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "history",
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
data class History(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val tank_model_id: Long,
    val development_history: String,
    val production_history: String,
    val combat_use: String,
    val notable_features: String,
    val interesting_facts: String,
    val created_at: Long = System.currentTimeMillis(),
    val updated_at: Long = System.currentTimeMillis()
)