package ru.abramov.tank_reference_system.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import ru.abramov.tank_reference_system.data.db.dao.CountryDao
import ru.abramov.tank_reference_system.data.db.dao.PhotoDao
import ru.abramov.tank_reference_system.data.db.dao.TankDao
import ru.abramov.tank_reference_system.data.db.dao.UserDao
import ru.abramov.tank_reference_system.data.db.dao.VehicleClassDao
import ru.abramov.tank_reference_system.data.db.entity.Country
import ru.abramov.tank_reference_system.data.db.entity.Photos
import ru.abramov.tank_reference_system.data.db.entity.Specifications
import ru.abramov.tank_reference_system.data.db.entity.TankModel
import ru.abramov.tank_reference_system.data.db.entity.User
import ru.abramov.tank_reference_system.data.db.entity.VehicleClass

@Database(
    entities = [
        TankModel::class,
        Specifications::class,
        Photos::class,
        VehicleClass::class,
        Country::class,
        User::class
    ],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun tankDao(): TankDao
    abstract fun photoDao(): PhotoDao
    abstract fun userDao(): UserDao
    abstract fun vehicleClassDao(): VehicleClassDao
    abstract fun countryDao(): CountryDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "tank_reference.db"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}