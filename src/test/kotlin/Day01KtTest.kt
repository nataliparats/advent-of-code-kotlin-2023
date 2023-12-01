import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class Day01KtTest {

    @Test
    fun `part 1, sample input`() {
        val input = readInput("Day01_part1_test")
        val result = Day01.part1(input)

        assertEquals(142, result)
    }

    @Test
    fun `part 1, challenge input`() {
        val input = readInput("Day01_part1")
        val result = Day01.part1(input)

        assertEquals(54081, result)
    }

    @Test
    fun `part 2, sample input`() {
        val input = readInput("Day01_part2_test")
        val result = Day01.part2(input)

        assertEquals(281, result)
    }

    @Test
    fun `part 2, challenge input`() {
        val input = readInput("Day01_part2")
        val result = Day01.part2(input)

        assertEquals(54649, result)
    }
}