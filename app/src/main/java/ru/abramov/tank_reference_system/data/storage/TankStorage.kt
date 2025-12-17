package ru.abramov.tank_reference_system.data.storage

import ru.abramov.tank_reference_system.data.db.entity.*

object TankStorage {

    fun fullSetIS7(): TankComplete = TankComplete(
        tank = TankModel(
            id = 1,
            name = "ИС-7",
            official_name = "ИС-7 «Объект 260»",
            vehicle_class_id = 4,
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

    fun fullSetT34(): TankComplete = TankComplete(
        tank = TankModel(
            id = 3,
            name = "Т-34",
            official_name = "Т-34 образца 1941 года",
            vehicle_class_id = 3,
            country_id = 1,
            development_start_year = 1937,
            production_start_year = 1940,
            production_end_year = 1958,
            description = "Средний танк СССР, ставший символом Победы."
        ),
        specifications = Specifications(
            tank_model_id = 3,
            weight_kg = 26_500,
            length_m = 6.68,
            width_m = 3.0,
            height_m = 2.45,
            crew_count = 4,
            main_gun_caliber_mm = 76,
            secondary_weapons = "7,62-мм ДТ",
            armor_front_mm = 45,
            armor_side_mm = 40,
            armor_rear_mm = 40,
            engine_type = "В-2",
            engine_power_hp = 500,
            max_speed_kmh = 53,
            range_km = 300
        ),
        history = listOf(
            History(
                tank_model_id = 3,
                development_history = "Разработан в КБ завода №183.",
                production_history = "Произведено более 57 000 единиц.",
                combat_use = "Основной танк РККА во Второй мировой войне.",
                notable_features = "Наклонная броня, дизельный двигатель.",
                interesting_facts = "Оказал огромное влияние на танкостроение."
            )
        ),
        photos = listOf(
            Photos(
                tank_model_id = 3,
                filename = "t34.png",
                description = "Т-34",
                photo_type = "general",
                is_primary = true
            )
        ),
        modifications = listOf(
            Modifications(
                base_model_id = 3,
                modification_name = "Т-34-85",
                description = "Модернизированный вариант с новой пушкой.",
                production_years = "1944-1950",
                changes = "85-мм пушка, новая башня."
            )
        )
    )

    fun fullSetKV1(): TankComplete = TankComplete(
        tank = TankModel(
            id = 4,
            name = "КВ-1",
            official_name = "КВ-1",
            vehicle_class_id = 4,
            country_id = 1,
            development_start_year = 1938,
            production_start_year = 1939,
            production_end_year = 1943,
            description = "Тяжёлый танк СССР начального периода войны."
        ),
        specifications = Specifications(
            tank_model_id = 4,
            weight_kg = 45_000,
            length_m = 6.75,
            width_m = 3.32,
            height_m = 2.71,
            crew_count = 5,
            main_gun_caliber_mm = 76,
            secondary_weapons = "7,62-мм ДТ",
            armor_front_mm = 90,
            armor_side_mm = 75,
            armor_rear_mm = 70,
            engine_type = "В-2К",
            engine_power_hp = 600,
            max_speed_kmh = 35,
            range_km = 250
        ),
        history = listOf(
            History(
                tank_model_id = 4,
                development_history = "Разработан в СКБ-2 Кировского завода.",
                production_history = "Выпускался серийно до 1943 года.",
                combat_use = "Применялся с первых дней войны.",
                notable_features = "Мощная броня.",
                interesting_facts = "Часто был неуязвим для немецких орудий."
            )
        ),
        photos = listOf(
            Photos(
                tank_model_id = 4,
                filename = "kv1.png",
                description = "КВ-1",
                photo_type = "general",
                is_primary = true
            )
        ),
        modifications = listOf(
            Modifications(
                base_model_id = 4,
                modification_name = "КВ-1С",
                description = "Облегчённая версия.",
                production_years = "1942-1943",
                changes = "Снижена броня, увеличена скорость."
            )
        )
    )

    fun fullSetSU85(): TankComplete = TankComplete(
        tank = TankModel(
            id = 5,
            name = "СУ-85",
            official_name = "СУ-85",
            vehicle_class_id = 6,
            country_id = 1,
            development_start_year = 1942,
            production_start_year = 1943,
            production_end_year = 1944,
            description = "Противотанковая самоходная артиллерийская установка."
        ),
        specifications = Specifications(
            tank_model_id = 5,
            weight_kg = 29_600,
            length_m = 8.15,
            width_m = 3.0,
            height_m = 2.45,
            crew_count = 4,
            main_gun_caliber_mm = 85,
            secondary_weapons = "7,62-мм ППШ",
            armor_front_mm = 45,
            armor_side_mm = 45,
            armor_rear_mm = 40,
            engine_type = "В-2-34",
            engine_power_hp = 500,
            max_speed_kmh = 55,
            range_km = 400
        ),
        history = listOf(
            History(
                tank_model_id = 5,
                development_history = "Создана на базе Т-34.",
                production_history = "Выпущено около 2 050 машин.",
                combat_use = "Эффективна против немецких тяжёлых танков.",
                notable_features = "Мощная пушка при компактных размерах.",
                interesting_facts = "Стала основой для СУ-100."
            )
        ),
        photos = listOf(
            Photos(
                tank_model_id = 5,
                filename = "su85.png",
                description = "СУ-85",
                photo_type = "general",
                is_primary = true
            )
        ),
        modifications = listOf(
            Modifications(
                base_model_id = 5,
                modification_name = "СУ-85М",
                description = "Бронированная версия.",
                production_years = "1944",
                changes = "Увеличенная лобовая броня, увеличен боезапас и добавили командирские башенки с пятью смотровыми щелями"
            )
        )
    )

    fun fullSetSU100(): TankComplete = TankComplete(
        tank = TankModel(
            id = 6,
            name = "СУ-100",
            official_name = "СУ-100",
            vehicle_class_id = 6,
            country_id = 1,
            development_start_year = 1943,
            production_start_year = 1944,
            production_end_year = 1957,
            description = "Противотанковая САУ с 100-мм пушкой."
        ),
        specifications = Specifications(
            tank_model_id = 6,
            weight_kg = 31_600,
            length_m = 9.45,
            width_m = 3.0,
            height_m = 2.25,
            crew_count = 4,
            main_gun_caliber_mm = 100,
            secondary_weapons = "7,62-мм ППШ",
            armor_front_mm = 75,
            armor_side_mm = 45,
            armor_rear_mm = 40,
            engine_type = "В-2-34",
            engine_power_hp = 500,
            max_speed_kmh = 48,
            range_km = 310
        ),
        history = listOf(
            History(
                tank_model_id = 6,
                development_history = "Развитие концепции СУ-85.",
                production_history = "Состояла на вооружении до 1970-х.",
                combat_use = "Применялась в ВОВ и послевоенных конфликтах.",
                notable_features = "Высокая бронепробиваемость.",
                interesting_facts = "Экспортировалась во многие страны."
            )
        ),
        photos = listOf(
            Photos(
                tank_model_id = 6,
                filename = "su100.png",
                description = "СУ-100",
                photo_type = "general",
                is_primary = true
            )
        ),
        modifications = listOf(
            Modifications(
                base_model_id = 6,
                modification_name = "СУ-100М1",
                description = "Бронированная версия с задним расположением рубки.",
                production_years = "1944",
                changes = "Увеличена броня и скорость."
            )
        )
    )

    fun fullSetKV2(): TankComplete = TankComplete(
        tank = TankModel(
            id = 7,
            name = "КВ-2",
            official_name = "КВ-2",
            vehicle_class_id = 4,
            country_id = 1,
            development_start_year = 1939,
            production_start_year = 1940,
            production_end_year = 1941,
            description = "Тяжёлый штурмовой танк с крупнокалиберной гаубицей."
        ),
        specifications = Specifications(
            tank_model_id = 7,
            weight_kg = 52_000,
            length_m = 6.95,
            width_m = 3.32,
            height_m = 3.25,
            crew_count = 6,
            main_gun_caliber_mm = 152,
            secondary_weapons = "7,62-мм ДТ",
            armor_front_mm = 75,
            armor_side_mm = 75,
            armor_rear_mm = 70,
            engine_type = "В-2К",
            engine_power_hp = 600,
            max_speed_kmh = 35,
            range_km = 250
        ),
        history = listOf(
            History(
                tank_model_id = 7,
                development_history = "Создан на базе КВ-1 для прорыва укреплений.",
                production_history = "Выпущено около 330 машин.",
                combat_use = "Применялся в 1941 году.",
                notable_features = "Огромная башня с 152-мм орудием.",
                interesting_facts = "Из-за массы часто ломалась трансмиссия."
            )
        ),
        photos = listOf(
            Photos(
                tank_model_id = 7,
                filename = "kv2.png",
                description = "КВ-2",
                photo_type = "general",
                is_primary = true
            )
        ),
        modifications = listOf(
            Modifications(
                base_model_id = 7,
                modification_name = "КВ-2 с «пониженной башней»",
                description = "Облегчённая версия.",
                production_years = "1940-1941",
                changes = "Снижена броня, уменьшена нагрузка на шасси."
            )
        )
    )

    fun fullSetIS2(): TankComplete = TankComplete(
        tank = TankModel(
            id = 8,
            name = "ИС-2",
            official_name = "ИС-2",
            vehicle_class_id = 4,
            country_id = 1,
            development_start_year = 1943,
            production_start_year = 1944,
            production_end_year = 1946,
            description = "Тяжёлый танк прорыва с мощным 122-мм орудием."
        ),
        specifications = Specifications(
            tank_model_id = 8,
            weight_kg = 46_000,
            length_m = 9.9,
            width_m = 3.09,
            height_m = 2.73,
            crew_count = 4,
            main_gun_caliber_mm = 122,
            secondary_weapons = "7,62-мм ДТ, 12,7-мм ДШК",
            armor_front_mm = 120,
            armor_side_mm = 90,
            armor_rear_mm = 60,
            engine_type = "В-2-ИС",
            engine_power_hp = 520,
            max_speed_kmh = 37,
            range_km = 240
        ),
        history = listOf(
            History(
                tank_model_id = 8,
                development_history = "Создан как ответ на немецкие тяжёлые танки.",
                production_history = "Произведено более 3 300 единиц.",
                combat_use = "Активно применялся в 1944–1945 гг.",
                notable_features = "Мощное орудие, лобовая броня «щучий нос».",
                interesting_facts = "Эффективно уничтожал Tiger и Panther."
            )
        ),
        photos = listOf(
            Photos(
                tank_model_id = 8,
                filename = "is2.png",
                description = "ИС-2",
                photo_type = "general",
                is_primary = true
            )
        ),
        modifications = listOf(
            Modifications(
                base_model_id = 8,
                modification_name = "ИС-2М",
                description = "Модернизированная версия.",
                production_years = "1943-1946",
                changes = "Увеличена скорость."
            )
        )
    )

    fun fullSetIS3(): TankComplete = TankComplete(
        tank = TankModel(
            id = 9,
            name = "ИС-3",
            official_name = "ИС-3",
            vehicle_class_id = 4,
            country_id = 1,
            development_start_year = 1944,
            production_start_year = 1945,
            production_end_year = 1946,
            description = "Послевоенный тяжёлый танк с характерной формой корпуса."
        ),
        specifications = Specifications(
            tank_model_id = 9,
            weight_kg = 46_500,
            length_m = 9.85,
            width_m = 3.23,
            height_m = 2.45,
            crew_count = 4,
            main_gun_caliber_mm = 122,
            secondary_weapons = "7,62-мм ДТ, 12,7-мм ДШК",
            armor_front_mm = 110,
            armor_side_mm = 90,
            armor_rear_mm = 60,
            engine_type = "В-2-ИС",
            engine_power_hp = 520,
            max_speed_kmh = 40,
            range_km = 300
        ),
        history = listOf(
            History(
                tank_model_id = 9,
                development_history = "Разработка велась в конце войны.",
                production_history = "Серийно выпускался недолго.",
                combat_use = "В боях ВОВ участия не принимал.",
                notable_features = "Корпус типа «щучий нос».",
                interesting_facts = "Произвёл сильное впечатление на Запад."
            )
        ),
        photos = listOf(
            Photos(
                tank_model_id = 9,
                filename = "is3.png",
                description = "ИС-3",
                photo_type = "general",
                is_primary = true
            )
        ),
        modifications = listOf(
            Modifications(
                base_model_id = 9,
                modification_name = "ИС-3М",
                description = "Модернизированная версия.",
                production_years = "1963",
                changes = "Улучшена центровка двигателя, счётчик моточасов, добавлены в ЗИП средства ПХЗ экипажа, дегазационный комплект."
            )
        )
    )

    fun fullSetSU152(): TankComplete = TankComplete(
        tank = TankModel(
            id = 10,
            name = "СУ-152",
            official_name = "СУ-152",
            vehicle_class_id = 6,
            country_id = 1,
            development_start_year = 1942,
            production_start_year = 1943,
            production_end_year = 1944,
            description = "Тяжёлая самоходная артиллерийская установка."
        ),
        specifications = Specifications(
            tank_model_id = 10,
            weight_kg = 45_500,
            length_m = 8.95,
            width_m = 3.25,
            height_m = 2.45,
            crew_count = 5,
            main_gun_caliber_mm = 152,
            secondary_weapons = "7,62-мм ДТ",
            armor_front_mm = 75,
            armor_side_mm = 60,
            armor_rear_mm = 60,
            engine_type = "В-2К",
            engine_power_hp = 600,
            max_speed_kmh = 43,
            range_km = 330
        ),
        history = listOf(
            History(
                tank_model_id = 10,
                development_history = "Создана на базе танка КВ.",
                production_history = "Производилась ограниченной серией.",
                combat_use = "Применялась против Tiger и Panther.",
                notable_features = "Мощное фугасное действие.",
                interesting_facts = "Получила прозвище «Зверобой»."
            )
        ),
        photos = listOf(
            Photos(
                tank_model_id = 10,
                filename = "su152.png",
                description = "СУ-152",
                photo_type = "general",
                is_primary = true
            )
        ),
        modifications = listOf(
            Modifications(
                base_model_id = 10,
                modification_name = "ИСУ-152",
                description = "Модернизированная версия.",
                production_years = "1943",
                changes = "Более мощное бронирование, увеличена скорость."
            )
        )
    )

    fun fullSetT90(): TankComplete = TankComplete(
        tank = TankModel(
            id = 11,
            name = "Т-90",
            official_name = "Т-90",
            vehicle_class_id = 1,
            country_id = 2,
            development_start_year = 1989,
            production_start_year = 1992,
            production_end_year = 2010,
            description = "Основной боевой танк РФ, развитие концепции Т-72."
        ),
        specifications = Specifications(
            tank_model_id = 11,
            weight_kg = 46_500,
            length_m = 9.53,
            width_m = 3.78,
            height_m = 2.23,
            crew_count = 3,
            main_gun_caliber_mm = 125,
            secondary_weapons = "7,62-мм ПКТ, 12,7-мм КОРД",
            armor_front_mm = 550,
            armor_side_mm = 350,
            armor_rear_mm = 90,
            engine_type = "В-92С2",
            engine_power_hp = 1000,
            max_speed_kmh = 65,
            range_km = 550
        ),
        history = listOf(
            History(
                tank_model_id = 11,
                development_history = "Создан в начале 1990-х.",
                production_history = "Стал основным танком РФ.",
                combat_use = "Применялся в локальных конфликтах.",
                notable_features = "КАЗ, современная СУО.",
                interesting_facts = "Экспортировался во многие страны."
            )
        ),
        photos = listOf(
            Photos(
                tank_model_id = 11,
                filename = "t90.png",
                description = "Т-90",
                photo_type = "general",
                is_primary = true
            )
        ),
        modifications = listOf(
            Modifications(
                base_model_id = 11,
                modification_name = "Т-90М",
                description = "Модернизированная версия.",
                production_years = "2020 — н.в.",
                changes = "Новая модель получила полностью переработанную башню, новое орудие с повышенной точностью, современную СУО «Калина», более мощный двигатель, улучшенную термозащиту и экранную защиту, а также обновлённый комплекс связи и навигации. "
            )
        )
    )

    fun fullSetT14(): TankComplete = TankComplete(
        tank = TankModel(
            id = 12,
            name = "Т-14",
            official_name = "Т-14 «Армата»",
            vehicle_class_id = 1,
            country_id = 2,
            development_start_year = 2010,
            production_start_year = 2015,
            production_end_year = null,
            description = "Перспективный основной боевой танк нового поколения."
        ),
        specifications = Specifications(
            tank_model_id = 12,
            weight_kg = 55_000,
            length_m = 10.8,
            width_m = 3.5,
            height_m = 3.3,
            crew_count = 3,
            main_gun_caliber_mm = 125,
            secondary_weapons = "7,62-мм ПКТМ, 12,7-мм КОРД",
            armor_front_mm = 900,
            armor_side_mm = 600,
            armor_rear_mm = 200,
            engine_type = "А-85-3А",
            engine_power_hp = 1500,
            max_speed_kmh = 75,
            range_km = 500
        ),
        history = listOf(
            History(
                tank_model_id = 12,
                development_history = "Создан на универсальной платформе «Армата».",
                production_history = "Находится в ограниченной серии.",
                combat_use = "Боевого применения не имел.",
                notable_features = "Необитаемая башня, капсула экипажа.",
                interesting_facts = "Самый технологичный танк РФ."
            )
        ),
        photos = listOf(
            Photos(
                tank_model_id = 12,
                filename = "t14.png",
                description = "Т-14 Армата",
                photo_type = "general",
                is_primary = true
            )
        ),
        modifications = emptyList()
    )
    data class TankComplete(
        val tank: TankModel,
        val specifications: Specifications,
        val history: List<History>,
        val photos: List<Photos>,
        val modifications: List<Modifications>
    )
}