package ru.abramov.tank_reference_system.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class User(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val username: String,
    val password_hash: String,
    val created_at: Long = System.currentTimeMillis()
)