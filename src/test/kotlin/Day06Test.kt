import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class Day06Test {

    @Test
    fun `part 1, parse numbers from string`(){
        val input = readInput("Day06_part1_test")
        val result = Day06.parseRacePart1(input)

        assertEquals(3, result.size)
        assertEquals(7, result[0].totalTime)
        assertEquals(9, result[0].bestRecord)
    }

    @Test
    fun `part 1, sample input`() {
        val input = readInput("Day06_part1_test")
        val result = Day06.part1(input)

        assertEquals(288, result)
    }

    @Test
    fun `part 1, challenge input`() {
        val input = readInput("Day06_part1")
        val result = Day06.part1(input)

        assertEquals(500346, result)
    }

    @Test
    fun `part 2, sample input`() {
        val input = readInput("Day06_part1_test")
        val result = Day06.part2(input)

        assertEquals(71503, result)
    }

    @Test
    fun `part 2, challenge input`() {
        val input = readInput("Day06_part1")
        val result = Day06.part2(input)

        assertEquals(42515755, result)
    }
}