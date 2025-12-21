package ru.abramov.tank_reference_system

import org.junit.Assert.assertEquals
import org.junit.Assert.assertThrows
import org.junit.Test
import ru.abramov.tank_reference_system.data.util.ArmorCalculator

class ArmorCalculatorTest {
    @Test
    fun effectiveArmorMm_validInputs() {
        val result = ArmorCalculator.effectiveArmorMm(baseMm = 100, slopeDeg = 60.0, addOnMm = 20)
        assertEquals(240.0, result, 0.001)
    }

    @Test
    fun effectiveArmorMm_zeroSlopeEqualsSum() {
        val result = ArmorCalculator.effectiveArmorMm(baseMm = 120, slopeDeg = 0.0, addOnMm = 30)
        assertEquals(150.0, result, 0.001)
    }

    @Test
    fun effectiveArmorMm_noAddOn() {
        val result = ArmorCalculator.effectiveArmorMm(baseMm = 100, slopeDeg = 45.0, addOnMm = 0)
        assertEquals(141.421, result, 0.01)
    }

    @Test
    fun effectiveArmorMm_smallSlope() {
        val result = ArmorCalculator.effectiveArmorMm(baseMm = 80, slopeDeg = 10.0, addOnMm = 10)
        assertEquals(91.388, result, 0.001)
    }

    @Test
    fun effectiveArmorMm_largeSlopeStillValid() {
        val result = ArmorCalculator.effectiveArmorMm(baseMm = 100, slopeDeg = 89.0, addOnMm = 0)
        assertEquals(5729.6, result, 1.0)
    }

    @Test
    fun effectiveArmorMm_rejectsInvalidInputs() {
        assertThrows(IllegalArgumentException::class.java) {
            ArmorCalculator.effectiveArmorMm(baseMm = 0, slopeDeg = 10.0, addOnMm = 0)
        }
        assertThrows(IllegalArgumentException::class.java) {
            ArmorCalculator.effectiveArmorMm(baseMm = 100, slopeDeg = 90.0, addOnMm = 0)
        }
    }

    @Test
    fun effectiveArmorMm_rejectsNegativeBase() {
        assertThrows(IllegalArgumentException::class.java) {
            ArmorCalculator.effectiveArmorMm(baseMm = -1, slopeDeg = 10.0, addOnMm = 0)
        }
    }

    @Test
    fun effectiveArmorMm_rejectsNegativeAddon() {
        assertThrows(IllegalArgumentException::class.java) {
            ArmorCalculator.effectiveArmorMm(baseMm = 100, slopeDeg = 10.0, addOnMm = -5)
        }
    }

    @Test
    fun effectiveArmorMm_rejectsNegativeSlope() {
        assertThrows(IllegalArgumentException::class.java) {
            ArmorCalculator.effectiveArmorMm(baseMm = 100, slopeDeg = -0.1, addOnMm = 0)
        }
    }

    @Test
    fun effectiveArmorMm_rejectsSlopeAtNinety() {
        assertThrows(IllegalArgumentException::class.java) {
            ArmorCalculator.effectiveArmorMm(baseMm = 100, slopeDeg = 90.0, addOnMm = 10)
        }
    }
}
