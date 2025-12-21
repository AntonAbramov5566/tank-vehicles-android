package ru.abramov.tank_reference_system

import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test
import ru.abramov.tank_reference_system.data.db.entity.User
import ru.abramov.tank_reference_system.viewmoodel.FilterAccess

class FilterAccessTest {
    @Test
    fun isFilterAllowed_blocksGuests() {
        val user = User(username = "user", password_hash = "hash")
        assertFalse(FilterAccess.isFilterAllowed(user, isGuest = true))
    }

    @Test
    fun isFilterAllowed_requiresUser() {
        assertFalse(FilterAccess.isFilterAllowed(null, isGuest = false))
        assertTrue(FilterAccess.isFilterAllowed(User(username = "user", password_hash = "hash"), isGuest = false))
    }

    @Test
    fun isFilterAllowed_guestWithoutUserIsBlocked() {
        assertFalse(FilterAccess.isFilterAllowed(null, isGuest = true))
    }

    @Test
    fun isFilterAllowed_nonGuestWithUserIsAllowed() {
        val user = User(username = "admin", password_hash = "hash")
        assertTrue(FilterAccess.isFilterAllowed(user, isGuest = false))
    }

    @Test
    fun isFilterAllowed_nonGuestWithoutUserIsBlocked() {
        assertFalse(FilterAccess.isFilterAllowed(null, isGuest = false))
    }

    @Test
        fun isFilterAllowed_guestWithUserStillBlocked() {
        val user = User(username = "user", password_hash = "hash")
        assertFalse(FilterAccess.isFilterAllowed(user, isGuest = true))
    }

    @Test
    fun isFilterAllowed_allowsOnlyAuthenticatedNonGuest() {
        val user = User(username = "user", password_hash = "hash")
        assertTrue(FilterAccess.isFilterAllowed(user, isGuest = false))
        assertFalse(FilterAccess.isFilterAllowed(user, isGuest = true))
    }

    @Test
    fun isFilterAllowed_handlesDifferentUsers() {
        val userA = User(username = "userA", password_hash = "hashA")
        val userB = User(username = "userB", password_hash = "hashB")
        assertTrue(FilterAccess.isFilterAllowed(userA, isGuest = false))
        assertTrue(FilterAccess.isFilterAllowed(userB, isGuest = false))
    }

    @Test
    fun isFilterAllowed_guestFlagTakesPriority() {
        val user = User(username = "user", password_hash = "hash")
        assertFalse(FilterAccess.isFilterAllowed(user, isGuest = true))
    }
}
