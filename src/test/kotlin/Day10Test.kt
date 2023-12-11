import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class Day10Test {

    @Test
    fun `part 1, sample input first example`() {
        val input = readInput("Day10_part1_1_test")
        val result = Day10.part1(input)

        assertEquals(4, result)
    }

    @Test
    fun `part 1, sample input second example`() {
        val input = readInput("Day10_part1_2_test")
        val result = Day10.part1(input)

        assertEquals(8, result)
    }

    @Test
    fun `part 1, challenge input`() {
        val input = readInput("Day10_part1")
        val result = Day10.part1(input)

        assertEquals(6886, result)
    }
}