package ru.abramov.tank_reference_system.viewmoodel

import ru.abramov.tank_reference_system.data.db.entity.User

object FilterAccess {
    fun isFilterAllowed(currentUser: User?, isGuest: Boolean): Boolean {
        if (isGuest) return false
        return currentUser != null
    }
}
