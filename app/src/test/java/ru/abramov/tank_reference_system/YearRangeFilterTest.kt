package ru.abramov.tank_reference_system

import org.junit.Assert.assertEquals
import org.junit.Test
import ru.abramov.tank_reference_system.data.db.entity.TankModel
import ru.abramov.tank_reference_system.data.util.YearRangeFilter

class YearRangeFilterTest {
    @Test
    fun filterByProductionYear_keepsOnlyWithinRange() {
        val tanks = listOf(
            tank(id = 1, name = "t34", year = 1940),
            tank(id = 2, name = "t72", year = 1973),
            tank(id = 3, name = "t14", year = null)
        )

        val result = YearRangeFilter.filterByProductionYear(tanks, startYear = 1950, endYear = 2000)

        assertEquals(listOf(tanks[1]), result)
    }

    @Test
    fun filterByProductionYear_startOnly() {
        val tanks = listOf(
            tank(id = 1, name = "t34", year = 1940),
            tank(id = 2, name = "t72", year = 1973)
        )

        val result = YearRangeFilter.filterByProductionYear(tanks, startYear = 1950, endYear = null)

        assertEquals(listOf(tanks[1]), result)
    }

    @Test
    fun filterByProductionYear_endOnly() {
        val tanks = listOf(
            tank(id = 1, name = "t34", year = 1940),
            tank(id = 2, name = "t72", year = 1973)
        )

        val result = YearRangeFilter.filterByProductionYear(tanks, startYear = null, endYear = 1950)

        assertEquals(listOf(tanks[0]), result)
    }

    @Test
    fun filterByProductionYear_nullRangeKeepsAllWithYear() {
        val tanks = listOf(
            tank(id = 1, name = "t34", year = 1940),
            tank(id = 2, name = "t72", year = 1973),
            tank(id = 3, name = "t14", year = null)
        )

        val result = YearRangeFilter.filterByProductionYear(tanks, startYear = null, endYear = null)

        assertEquals(listOf(tanks[0], tanks[1]), result)
    }

    @Test
    fun filterByProductionYear_excludesNullYear() {
        val tanks = listOf(
            tank(id = 1, name = "t14", year = null)
        )

        val result = YearRangeFilter.filterByProductionYear(tanks, startYear = 2000, endYear = 2020)

        assertEquals(emptyList<TankModel>(), result)
    }

    @Test
    fun filterByProductionYear_includesBoundaryYears() {
        val tanks = listOf(
            tank(id = 1, name = "t34", year = 1940),
            tank(id = 2, name = "t72", year = 1973)
        )

        val result = YearRangeFilter.filterByProductionYear(tanks, startYear = 1940, endYear = 1973)

        assertEquals(listOf(tanks[0], tanks[1]), result)
    }

    @Test
    fun filterByProductionYear_emptyInput() {
        val result = YearRangeFilter.filterByProductionYear(emptyList(), startYear = 1940, endYear = 1973)

        assertEquals(emptyList<TankModel>(), result)
    }

    @Test
    fun filterByProductionYear_invalidRangeReturnsEmpty() {
        val tanks = listOf(
            tank(id = 1, name = "t34", year = 1940),
            tank(id = 2, name = "t72", year = 1973)
        )

        val result = YearRangeFilter.filterByProductionYear(tanks, startYear = 2000, endYear = 1950)

        assertEquals(emptyList<TankModel>(), result)
    }

    @Test
    fun filterByProductionYear_endEqualsStart() {
        val tanks = listOf(
            tank(id = 1, name = "t34", year = 1940),
            tank(id = 2, name = "t72", year = 1973)
        )

        val result = YearRangeFilter.filterByProductionYear(tanks, startYear = 1973, endYear = 1973)

        assertEquals(listOf(tanks[1]), result)
    }

    @Test
    fun filterByProductionYear_startAfterAllYears() {
        val tanks = listOf(
            tank(id = 1, name = "t34", year = 1940),
            tank(id = 2, name = "t72", year = 1973)
        )

        val result = YearRangeFilter.filterByProductionYear(tanks, startYear = 2001, endYear = null)

        assertEquals(emptyList<TankModel>(), result)
    }

    private fun tank(id: Long, name: String, year: Int?): TankModel {
        return TankModel(
            id = id,
            name = name,
            official_name = null,
            vehicle_class_id = 1,
            country_id = 1,
            development_start_year = null,
            production_start_year = year,
            production_end_year = null,
            description = null
        )
    }
}
