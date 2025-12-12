package ru.abramov.tank_reference_system.data.storage

import ru.abramov.tank_reference_system.data.db.entity.*

object TankStorage {

    /* один полный комплект – Т-72 */
    fun fullSet(): TankComplete =
        TankComplete(
            tank = TankModel(
                id = 0L,
                name = "Т-72",
                official_name = "Т-72 «Урал»",
                vehicle_class_id = 1,
                country_id = 1,
                development_start_year = 1967,
                production_start_year = 1973,
                production_end_year = null,
                description = "Основной боевой танк третьего поколения, принят на вооружение в 1973 году."
            ),
            specifications = Specifications(
                tank_model_id = 0L,
                weight_kg = 41_000,
                length_m = 6.41,
                width_m = 3.46,
                height_m = 2.19,
                crew_count = 3,
                main_gun_caliber_mm = 125,
                secondary_weapons = "7,62-мм пулемёт ПКТ, 12,7-мм пулемёт НСВТ",
                armor_front_mm = 200,
                armor_side_mm = 80,
                armor_rear_mm = 45,
                engine_type = "В-46",
                engine_power_hp = 780,
                max_speed_kmh = 60,
                range_km = 480
            ),
            history = listOf(
                History(
                    tank_model_id = 0L,
                    development_history = "Разработка началась в 1967 г. на Уральском вагоностроительном заводе.",
                    production_history = "Серийное производство – с 1973 г., более 25 000 единиц.",
                    combat_use = "Широко применялся в локальных конфликтах, включая Афганистан, Чечню, Сирию.",
                    notable_features = "Автомат заряжания, композитная броня, низкий профиль.",
                    interesting_facts = "Т-72 до сих пор остаётся самым массовым танком СССР/РФ."
                )
            ),
            photos = listOf(
                Photos(
                    tank_model_id = 0L,
                    filename = "t72_front.png",
                    description = "Общий вид",
                    photo_type = "general",
                    is_primary = true
                ),
                Photos(
                    tank_model_id = 0L,
                    filename = "t72_gun.png",
                    description = "Пушка 2А46",
                    photo_type = "detail",
                    is_primary = false
                ),
                Photos(
                    tank_model_id = 0L,
                    filename = "t72_plan.png",
                    description = "Чертёж танка",
                    photo_type = "plan",
                    is_primary = false
                )
            ),
            modifications = listOf(
                Modifications(
                    base_model_id = 0L,
                    modification_name = "Т-72А",
                    description = "Усиленная броня, новый прицел.",
                    production_years = "1979-1985",
                    changes = "Добавлены экраны, обновлён прицел 1А40."
                ),
                Modifications(
                    base_model_id = 0L,
                    modification_name = "Т-72Б",
                    description = "Пуск управляемых ракет, динамическая защита.",
                    production_years = "1985-1992",
                    changes = "Пуск 9К120 «Свирь», комплекс динамической защиты «Контакт-5»."
                )
            )
        )
    data class TankComplete(
        val tank: TankModel,
        val specifications: Specifications,
        val history: List<History>,
        val photos: List<Photos>,
        val modifications: List<Modifications>
    )
}