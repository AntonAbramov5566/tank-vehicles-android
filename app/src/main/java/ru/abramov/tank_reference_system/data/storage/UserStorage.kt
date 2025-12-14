package ru.abramov.tank_reference_system.data.storage

import ru.abramov.tank_reference_system.data.db.entity.User

object UserStorage {
    fun defaultUsers() = listOf(
        User(username = "user", password_hash = hash("1234")),
        User(username = "admin", password_hash = hash("admin"))
    )

    fun hash(password: String): String =
        java.security.MessageDigest.getInstance("SHA-256")
            .digest(password.toByteArray())
            .fold("") { str, it -> str + "%02x".format(it) }
}