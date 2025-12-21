package ru.abramov.tank_reference_system.data.util

import kotlin.math.cos

object ArmorCalculator {
    fun effectiveArmorMm(baseMm: Int, slopeDeg: Double, addOnMm: Int): Double {
        require(baseMm > 0) { "baseMm must be > 0" }
        require(addOnMm >= 0) { "addOnMm must be >= 0" }
        require(slopeDeg >= 0.0 && slopeDeg < 90.0) { "slopeDeg must be in [0, 90)" }

        val radians = Math.toRadians(slopeDeg)
        return (baseMm + addOnMm) / cos(radians)
    }
}
