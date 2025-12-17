package ru.abramov.tank_reference_system.data.storage

import ru.abramov.tank_reference_system.data.db.entity.VehicleClass

object VehicleClassStorage {
    fun defaultClasses() = listOf(
        VehicleClass(name = "Основной боевой танк", id = 1),
        VehicleClass(name = "Легкий танк", id = 2),
        VehicleClass(name = "Средний танк", id = 3),
        VehicleClass(name = "Тяжёлый танк", id = 4),
        VehicleClass(name = "Самоходная артиллерийская установка", id = 5),
        VehicleClass(name = "Противотанковя самоходная артиллерийская установка", id = 6),
        VehicleClass(name = "Боевая машина пехоты", id = 7),
        VehicleClass(name = "Бронетранспортер", id = 8)
    )
}