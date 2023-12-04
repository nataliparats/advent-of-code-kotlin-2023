import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class Day04Test {

    @Test
    fun `part 1, parse input to list of Card` () {
        val input = readInput("Day04_part1_test")
        val result = Day04.parseCards(input)

        assertEquals(1, result[0].number)
        assertTrue(result[0].winningNumbers.containsAll(listOf(41, 48, 83, 86, 17)))
        assertTrue(result[0].numbersYouHave.containsAll(listOf(83, 86, 6, 31, 17, 9, 48, 53)))
    }

    @Test
    fun `part 1, sample input`() {
        val input = readInput("Day04_part1_test")
        val result = Day04.part1(input)

        assertEquals(13, result)
    }

    @Test
    fun `part 1, challenge input`() {
        val input = readInput("Day04_part1")
        val result = Day04.part1(input)

        assertEquals(28538, result)
    }

    @Test
    fun `part 2, sample input`() {
        val input = readInput("Day04_part1_test")
        val result = Day04.part2(input)

        assertEquals(30, result)
    }

    @Test
    fun `part 2, challenge input`() {
        val input = readInput("Day04_part1")
        val result = Day04.part2(input)

        assertEquals(9425061, result)
    }
}