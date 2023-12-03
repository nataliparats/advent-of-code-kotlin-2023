import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class Day03Test {

    @Test
    fun `part 1, sample input`() {
        val input = readInput("Day03_part1_test")
        val result = Day03.part1(input)

        assertEquals(4361, result)
    }

    @Test
    fun `part 1, challenge input`() {
        val input = readInput("Day03_part1")
        val result = Day03.part1(input)

        assertEquals(539433, result)
    }

    @Test
    fun `part 2, sample input`() {
        val input = readInput("Day03_part1_test")
        val result = Day03.part2(input)

        assertEquals(467835, result)
    }

    @Test
    fun `part 2, challenge input`() {
        val input = readInput("Day03_part1")
        val result = Day03.part2(input)

        assertEquals(75847567, result)
    }
}