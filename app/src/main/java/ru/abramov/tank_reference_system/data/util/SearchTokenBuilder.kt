package ru.abramov.tank_reference_system.data.util

object SearchTokenBuilder {
    fun buildTokens(query: String, minTokenLen: Int): List<String> {
        require(minTokenLen > 0) { "minTokenLen must be > 0" }

        return query
            .trim()
            .lowercase()
            .split(Regex("\\s+"))
            .filter { it.length >= minTokenLen }
    }
}
