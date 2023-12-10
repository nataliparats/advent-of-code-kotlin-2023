import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class Day09Test {

    @Test
    fun `part 1, parse input to points`(){
        val input = readInput("Day08_part1_test")
        val result = Day08.parseInputPoints(input)

        assertEquals(3, result.size)
        assertEquals("BBB", result.entries.first().value.first)
        assertEquals("BBB", result.entries.first().value.second)
    }

    @Test
    fun `part 1, sample input`() {
        val input = readInput("Day09_part1_test")
        val result = Day09.part1(input)

        assertEquals(114, result)
    }

    @Test
    fun `part 1, challenge input`() {
        val input = readInput("Day09_part1")
        val result = Day09.part1(input)

        assertEquals(1731106378, result)
    }

    @Test
    fun `part 2, sample input`() {
        val input = readInput("Day09_part1_test")
        val result = Day09.part2(input)

        assertEquals(2, result)
    }

    @Test
    fun `part 2, challenge input`() {
        val input = readInput("Day09_part1")
        val result = Day09.part2(input)

        assertEquals(1087, result)
    }
}