package ru.abramov.tank_reference_system

import org.junit.Assert.assertEquals
import org.junit.Assert.assertThrows
import org.junit.Test
import ru.abramov.tank_reference_system.data.util.SearchTokenBuilder

class SearchTokenBuilderTest {
    @Test
    fun buildTokens_filtersByMinLength() {
        val tokens = SearchTokenBuilder.buildTokens("  T-72  IS-7  ", minTokenLen = 3)
        assertEquals(listOf("t-72", "is-7"), tokens)
    }

    @Test
    fun buildTokens_trimsAndSplitsByWhitespace() {
        val tokens = SearchTokenBuilder.buildTokens("  T-90\tT-14  ", minTokenLen = 1)
        assertEquals(listOf("t-90", "t-14"), tokens)
    }

    @Test
    fun buildTokens_emptyQueryReturnsEmptyList() {
        val tokens = SearchTokenBuilder.buildTokens("   ", minTokenLen = 1)
        assertEquals(emptyList<String>(), tokens)
    }

    @Test
    fun buildTokens_keepsOrder() {
        val tokens = SearchTokenBuilder.buildTokens("IS-7 T-72 T-34", minTokenLen = 1)
        assertEquals(listOf("is-7", "t-72", "t-34"), tokens)
    }

    @Test
    fun buildTokens_filtersOutShortTokens() {
        val tokens = SearchTokenBuilder.buildTokens("a bb ccc dddd", minTokenLen = 3)
        assertEquals(listOf("ccc", "dddd"), tokens)
    }

    @Test
    fun buildTokens_lowercasesTokens() {
        val tokens = SearchTokenBuilder.buildTokens("T-34 Is-2", minTokenLen = 1)
        assertEquals(listOf("t-34", "is-2"), tokens)
    }

    @Test
    fun buildTokens_minTokenLenOneKeepsAll() {
        val tokens = SearchTokenBuilder.buildTokens("A B C", minTokenLen = 1)
        assertEquals(listOf("a", "b", "c"), tokens)
    }

    @Test
    fun buildTokens_minTokenLenEqualsLengthKeepsToken() {
        val tokens = SearchTokenBuilder.buildTokens("tank", minTokenLen = 4)
        assertEquals(listOf("tank"), tokens)
    }

    @Test
    fun buildTokens_minTokenLenGreaterThanLengthDropsToken() {
        val tokens = SearchTokenBuilder.buildTokens("tank", minTokenLen = 5)
        assertEquals(emptyList<String>(), tokens)
    }

    @Test
    fun buildTokens_rejectsInvalidMinLength() {
        assertThrows(IllegalArgumentException::class.java) {
            SearchTokenBuilder.buildTokens("t-72", minTokenLen = 0)
        }
    }

    @Test
    fun buildTokens_rejectsNegativeMinLength() {
        assertThrows(IllegalArgumentException::class.java) {
            SearchTokenBuilder.buildTokens("t-72", minTokenLen = -1)
        }
    }
}
