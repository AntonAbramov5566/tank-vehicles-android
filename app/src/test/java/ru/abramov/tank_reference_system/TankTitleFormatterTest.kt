package ru.abramov.tank_reference_system

import org.junit.Assert.assertEquals
import org.junit.Test
import ru.abramov.tank_reference_system.ui.format.TankTitleFormatter

class TankTitleFormatterTest {
    @Test
    fun buildTitle_usesOfficialNameWhenPresent() {
        val title = TankTitleFormatter.buildTitle(
            name = "T-72",
            officialName = "Ural",
            year = 1973
        )
        assertEquals("Ural (1973)", title)
    }

    @Test
    fun buildTitle_fallsBackToNameWithoutYear() {
        val title = TankTitleFormatter.buildTitle(
            name = "T-34",
            officialName = null,
            year = null
        )
        assertEquals("T-34", title)
    }

    @Test
    fun buildTitle_usesNameWhenOfficialBlank() {
        val title = TankTitleFormatter.buildTitle(
            name = "IS-7",
            officialName = "   ",
            year = 1945
        )
        assertEquals("IS-7 (1945)", title)
    }

    @Test
    fun buildTitle_usesOfficialWithoutYear() {
        val title = TankTitleFormatter.buildTitle(
            name = "KV-1",
            officialName = "Klim Voroshilov",
            year = null
        )
        assertEquals("Klim Voroshilov", title)
    }

    @Test
    fun buildTitle_yearZeroIsIncluded() {
        val title = TankTitleFormatter.buildTitle(
            name = "Prototype",
            officialName = null,
            year = 0
        )
        assertEquals("Prototype (0)", title)
    }

    @Test
    fun buildTitle_preservesNameWhenOfficialNull() {
        val title = TankTitleFormatter.buildTitle(
            name = "T-90",
            officialName = null,
            year = 1992
        )
        assertEquals("T-90 (1992)", title)
    }

    @Test
    fun buildTitle_handlesLongOfficialName() {
        val title = TankTitleFormatter.buildTitle(
            name = "T-14",
            officialName = "Armata Main Battle Tank",
            year = 2015
        )
        assertEquals("Armata Main Battle Tank (2015)", title)
    }

    @Test
    fun buildTitle_handlesEmptyNameWithOfficial() {
        val title = TankTitleFormatter.buildTitle(
            name = "",
            officialName = "Test",
            year = 2000
        )
        assertEquals("Test (2000)", title)
    }

    @Test
    fun buildTitle_emptyOfficialAndNullYearUsesNameOnly() {
        val title = TankTitleFormatter.buildTitle(
            name = "SU-100",
            officialName = "",
            year = null
        )
        assertEquals("SU-100", title)
    }

    @Test
    fun buildTitle_nullOfficialAndYearUsesNameOnly() {
        val title = TankTitleFormatter.buildTitle(
            name = "IS-2",
            officialName = null,
            year = null
        )
        assertEquals("IS-2", title)
    }
}
