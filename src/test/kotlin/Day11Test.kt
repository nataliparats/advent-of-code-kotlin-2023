import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class Day11Test {

    @Test
    fun `part 1, sample input`() {
        val input = readInput("Day11_part1_test")
        val result = Day11.part1(input)

        assertEquals(374, result)
    }

    @Test
    fun `part 1, challenge input`() {
        val input = readInput("Day11_part1")
        val result = Day11.part1(input)

        assertEquals(10494813, result)
    }

    @Test
    fun `part 2, sample input with expansion rate 10`() {
        val input = readInput("Day11_part1_test")
        val result = Day11.part2(input, 10)

        assertEquals(1030, result)
    }

    @Test
    fun `part 2, sample input with expansion rate 100`() {
        val input = readInput("Day11_part1_test")
        val result = Day11.part2(input, 100)

        assertEquals(8410, result)
    }

    @Test
    fun `part 2, sample input with expansion rate 1000000`() {
        val input = readInput("Day11_part1")
        val result = Day11.part2(input, 1000000)

        assertEquals(840988812853, result)
    }
}