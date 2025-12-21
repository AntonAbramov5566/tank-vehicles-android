package ru.abramov.tank_reference_system.ui.format

object TankTitleFormatter {
    fun buildTitle(name: String, officialName: String?, year: Int?): String {
        val base = officialName?.takeIf { it.isNotBlank() } ?: name
        val suffix = year?.let { " ($it)" } ?: ""
        return base + suffix
    }
}
