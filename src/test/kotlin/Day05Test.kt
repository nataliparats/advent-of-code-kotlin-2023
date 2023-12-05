import Day05.resolveLocation
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class Day05Test {

    @Test
    fun `part 1, parse seeds to list` () {
        val input = readInput("Day05_part1_test")
        val result = Day05.parseSeeds(input[0])

        assertEquals(listOf(79L, 14L, 55L, 13L), result)
    }

    @Test
    fun `part 1, parse maps to list` () {
        val input = readInput("Day05_part1_test")
        val result = Day05.parseMaps(input)

        assertEquals(7, result.size)
        assertEquals(Day05.RangeMap(88L, 18L, 7L),result[3][0])
    }

    @Test
    fun `part 1, find destination for seed 98 using seed-to-soil map only` () {
        val input = readInput("Day05_part1_test")
        val maps = Day05.parseMaps(input)

        assertTrue( maps[0][0].isNumberInRange(98))
        assertEquals( 50, maps[0][0].resolveLocation(98))
    }

    @Test
    fun `part 1, find destination for seed 79 using all maps` () {
        val input = readInput("Day05_part1_test")
        val maps = Day05.parseMaps(input)

        var number = 79L
        maps.map { it ->
            val resolvedLocation = it.resolveLocation(number)
            number = resolvedLocation
        }

        assertEquals( 82, number)
    }

    @Test
    fun `part 1, sample input`() {
        val input = readInput("Day05_part1_test")
        val result = Day05.part1(input)

        assertEquals(35, result)
    }

    @Test
    fun `part 1, challenge input`() {
        val input = readInput("Day05_part1")
        val result = Day05.part1(input)

        assertEquals(214922730, result)
    }

    @Test
    fun `part 2, sample input`() {
        val input = readInput("Day05_part1_test")
        val result = Day05.part2(input)

        assertEquals(46, result)
    }

    @Test
    fun `part 2, challenge input`() {
        val input = readInput("Day05_part1")
        val result = Day05.part2(input)

        assertEquals(148041808, result)
    }
}