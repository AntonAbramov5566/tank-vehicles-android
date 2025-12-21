package ru.abramov.tank_reference_system.data.util

import ru.abramov.tank_reference_system.data.db.entity.TankModel

object YearRangeFilter {
    fun filterByProductionYear(
        tanks: List<TankModel>,
        startYear: Int?,
        endYear: Int?
    ): List<TankModel> {
        if (startYear != null && endYear != null && startYear > endYear) {
            return emptyList()
        }

        return tanks.filter { tank ->
            val year = tank.production_start_year ?: return@filter false
            val afterStart = startYear?.let { year >= it } ?: true
            val beforeEnd = endYear?.let { year <= it } ?: true
            afterStart && beforeEnd
        }
    }
}
