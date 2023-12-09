import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class Day08Test {

    @Test
    fun `part 1, parse input to points`(){
        val input = readInput("Day08_part1_test")
        val result = Day08.parseInputPoints(input)

        assertEquals(3, result.size)
        assertEquals("BBB", result.entries.first().value.first)
        assertEquals("BBB", result.entries.first().value.second)
    }

    @Test
    fun `part 1, parse input to directions`(){
        val input = readInput("Day08_part1_test")
        val result = Day08.parseDirections(input)

        assertEquals("L", result[0])
    }

    @Test
    fun `part 1, sample input`() {
        val input = readInput("Day08_part1_test")
        val result = Day08.part1(input)

        assertEquals(6, result)
    }

    @Test
    fun `part 1, challenge input`() {
        val input = readInput("Day08_part1")
        val result = Day08.part1(input)

        assertEquals(20221, result)
    }

    @Test
    fun `part 2, sample input`() {
        val input = readInput("Day08_part2_test")
        val result = Day08.part2(input)

        assertEquals(6, result)
    }

    @Test
    fun `part 2, challenge input`() {
        val input = readInput("Day08_part1")
        val result = Day08.part2(input)

        assertEquals(14616363770447, result)
    }
}