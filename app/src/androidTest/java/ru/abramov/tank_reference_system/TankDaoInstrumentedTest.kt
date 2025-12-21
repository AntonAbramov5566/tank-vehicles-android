package ru.abramov.tank_reference_system

import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import ru.abramov.tank_reference_system.data.db.AppDatabase
import ru.abramov.tank_reference_system.data.db.entity.Country
import ru.abramov.tank_reference_system.data.db.entity.Specifications
import ru.abramov.tank_reference_system.data.db.entity.TankModel
import ru.abramov.tank_reference_system.data.db.entity.VehicleClass

@RunWith(AndroidJUnit4::class)
class TankDaoInstrumentedTest {
    private lateinit var db: AppDatabase

    @Before
    fun setUp() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        db = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java)
            .allowMainThreadQueries()
            .build()
    }

    @After
    fun tearDown() {
        db.close()
    }

    @Test
    fun getByCaliberAndClass_returnsMatchingTanks() = runBlocking {
        val countryId = db.countryDao().insert(Country(name = "TestLand", code = "TL")).toInt()
        val classId = db.vehicleClassDao().insert(VehicleClass(name = "TestClass")).toInt()

        insertTankWithSpec(name = "T-100", classId = classId, countryId = countryId, caliber = 125)
        insertTankWithSpec(name = "T-200", classId = classId, countryId = countryId, caliber = 100)

        val result = db.tankDao().getByCaliberAndClass(125, classId.toLong()).first()

        assertEquals(1, result.size)
        assertTrue(result.first().name == "T-100")
    }

    @Test
    fun getByCaliberAndClass_returnsEmptyWhenNoMatch() = runBlocking {
        val countryId = db.countryDao().insert(Country(name = "TestLand", code = "TL")).toInt()
        val classId = db.vehicleClassDao().insert(VehicleClass(name = "TestClass")).toInt()

        insertTankWithSpec(name = "T-100", classId = classId, countryId = countryId, caliber = 100)

        val result = db.tankDao().getByCaliberAndClass(125, classId.toLong()).first()

        assertTrue(result.isEmpty())
    }

    @Test
    fun getByCaliberAndClass_filtersByClass() = runBlocking {
        val countryId = db.countryDao().insert(Country(name = "TestLand", code = "TL")).toInt()
        val classIdA = db.vehicleClassDao().insert(VehicleClass(name = "ClassA")).toInt()
        val classIdB = db.vehicleClassDao().insert(VehicleClass(name = "ClassB")).toInt()

        insertTankWithSpec(name = "T-100", classId = classIdA, countryId = countryId, caliber = 125)
        insertTankWithSpec(name = "T-200", classId = classIdB, countryId = countryId, caliber = 125)

        val result = db.tankDao().getByCaliberAndClass(125, classIdA.toLong()).first()

        assertEquals(1, result.size)
        assertEquals("T-100", result.first().name)
    }

    @Test
    fun getByCaliberAndClass_returnsMultipleMatches() = runBlocking {
        val countryId = db.countryDao().insert(Country(name = "TestLand", code = "TL")).toInt()
        val classId = db.vehicleClassDao().insert(VehicleClass(name = "TestClass")).toInt()

        insertTankWithSpec(name = "T-100", classId = classId, countryId = countryId, caliber = 125)
        insertTankWithSpec(name = "T-110", classId = classId, countryId = countryId, caliber = 125)
        insertTankWithSpec(name = "T-200", classId = classId, countryId = countryId, caliber = 100)

        val result = db.tankDao().getByCaliberAndClass(125, classId.toLong()).first()

        assertEquals(2, result.size)
    }

    private suspend fun insertTankWithSpec(
        name: String,
        classId: Int,
        countryId: Int,
        caliber: Int
    ) {
        val tankId = db.tankDao().insert(
            TankModel(
                name = name,
                official_name = null,
                vehicle_class_id = classId,
                country_id = countryId,
                development_start_year = null,
                production_start_year = null,
                production_end_year = null,
                description = null
            )
        )
        db.specificationsDao().insert(
            Specifications(
                tank_model_id = tankId,
                weight_kg = null,
                length_m = null,
                width_m = null,
                height_m = null,
                crew_count = null,
                main_gun_caliber_mm = caliber,
                secondary_weapons = null,
                armor_front_mm = null,
                armor_side_mm = null,
                armor_rear_mm = null,
                engine_type = null,
                engine_power_hp = null,
                max_speed_kmh = null,
                range_km = null
            )
        )
    }
}
