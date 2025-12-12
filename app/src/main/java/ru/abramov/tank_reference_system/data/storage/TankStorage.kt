package ru.abramov.tank_reference_system.data.storage

import ru.abramov.tank_reference_system.data.db.entity.*

object TankStorage {

    fun fullSetT72(): TankComplete =
        TankComplete(
            tank = TankModel(
                id = 2,
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
                tank_model_id = 2,
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
                    tank_model_id = 2,
                    development_history = "Разработка началась в 1967 г. на Уральском вагоностроительном заводе.",
                    production_history = "Серийное производство – с 1973 г., более 25 000 единиц.",
                    combat_use = "Широко применялся в локальных конфликтах, включая Афганистан, Чечню, Сирию.",
                    notable_features = "Автомат заряжания, композитная броня, низкий профиль.",
                    interesting_facts = "Т-72 до сих пор остаётся самым массовым танком СССР/РФ."
                )
            ),
            photos = listOf(
                Photos(
                    tank_model_id = 2,
                    filename = "t72_front.png",
                    description = "Общий вид",
                    photo_type = "general",
                    is_primary = true
                ),
                Photos(
                    tank_model_id = 2,
                    filename = "t72_gun.png",
                    description = "Пушка 2А46",
                    photo_type = "detail",
                    is_primary = false
                ),
                Photos(
                    tank_model_id = 2,
                    filename = "t72_plan.png",
                    description = "Чертёж танка",
                    photo_type = "plan",
                    is_primary = false
                )
            ),
            modifications = listOf(
                Modifications(
                    base_model_id = 2,
                    modification_name = "Т-72А",
                    description = "Усиленная броня, новый прицел.",
                    production_years = "1979-1985",
                    changes = "Добавлены экраны, обновлён прицел 1А40."
                ),
                Modifications(
                    base_model_id = 2,
                    modification_name = "Т-72Б",
                    description = "Пуск управляемых ракет, динамическая защита.",
                    production_years = "1985-1992",
                    changes = "Пуск 9К120 «Свирь», комплекс динамической защиты «Контакт-5»."
                )
            )
        )

    fun fullSetIS7(): TankComplete = TankComplete(
        tank = TankModel(
            id = 1,
            name = "ИС-7",
            official_name = "ИС-7 «Объект 260»",
            vehicle_class_id = 1,
            country_id = 1,
            development_start_year = 1945,
            production_start_year = 1948,
            production_end_year = 1949,
            description = "Тяжёлый танк последнего поколения СССР, прототип принят не был."
        ),
        specifications = Specifications(
            tank_model_id = 1,
            weight_kg = 68_000,
            length_m = 7.41,
            width_m = 3.4,
            height_m = 2.72,
            crew_count = 5,
            main_gun_caliber_mm = 130,
            secondary_weapons = "7,62-мм СГ-43, 14,5-мм КПВТ",
            armor_front_mm = 250,
            armor_side_mm = 100,
            armor_rear_mm = 60,
            engine_type = "М-50Т",
            engine_power_hp = 1050,
            max_speed_kmh = 60,
            range_km = 300
        ),
        history = listOf(
            History(
                tank_model_id = 1,
                development_history = "Разработка началась в 1945 г. на заводе №100.",
                production_history = "Собрано 7 прототипов, серия не пошла.",
                combat_use = "Не применялся в бою.",
                notable_features = "130-мм пушка, композитная броня, низкий профиль.",
                interesting_facts = "Самый тяжёлый танк СССР, масса 68 тонн."
            )
        ),
        photos = listOf(
            Photos(
                tank_model_id = 1,
                filename = "is7_front.png",
                description = "Общий вид ИС-7",
                photo_type = "general",
                is_primary = true
            ),
            Photos(
                tank_model_id = 1,
                filename = "is7_plan.png",
                description = "Чертёж",
                photo_type = "plan",
                is_primary = false
            )
        ),
        modifications = listOf(
            Modifications(
                base_model_id = 1,
                modification_name = "ИС-7М",
                description = "Улучшенная трансмиссия, новый прицел.",
                production_years = "1949-1950",
                changes = "Замена коробки передач, обновлён прицел."
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