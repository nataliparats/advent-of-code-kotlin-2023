import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class Day02Test {

    @Test
    fun `part 1, parse the input file`() {
        val input = readInput("Day02_part1_test")
        val result = Day02.parseGames(input)

        assertEquals(5, result.size)
        assertEquals(2, result[4].subsets.size)
        assertEquals(listOf(
            Day02.Subset(3, 1, 6),
            Day02.Subset(6,3,0),
            Day02.Subset(14,3,15),
            ),
            result[3].subsets)
    }

    @Test
    fun `part 1, sample input`() {
        val input = readInput("Day02_part1_test")
        val result = Day02.part1(input)

        assertEquals(8, result)
    }

    @Test
    fun `part 1, challenge input`() {
        val input = readInput("Day02_part1")
        val result = Day02.part1(input)

        assertEquals(2685, result)
    }

    @Test
    fun `part 2, sample input`() {
        val input = readInput("Day02_part1_test")
        val result = Day02.part2(input)

        assertEquals(2286, result)
    }

    @Test
    fun `part 2, challenge input`() {
        val input = readInput("Day02_part1")
        val result = Day02.part2(input)

        assertEquals(83707, result)
    }
}