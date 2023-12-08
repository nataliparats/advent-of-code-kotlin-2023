import Day07.isFiveOfAKind
import Day07.isFourOfAKind
import Day07.isFullHouse
import Day07.isOnePair
import Day07.isThreeOfAKind
import Day07.isTwoPair
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class Day07Test {

    @Test
    fun `part 1, parse numbers from string`(){
        val input = readInput("Day07_part1_test")
        val result = Day07.parseInput(input)

        assertEquals(5, result.size)
        assertEquals("T55J5", result[1].first.hand)
        assertEquals(684, result[1].second)
    }

    @Test
    fun `determine handType from string`() {
        val fiveOfAKindSample = "AAAAA"
        val fourOfAKindExample = "AA8AA"
        val fullHouseExample = "23332"
        val threeOfAKindExample = "TTT98"
        val twoPairExample = "23432"
        val onePairExample = "A23A4"

        assertTrue(fiveOfAKindSample.isFiveOfAKind())
        assertTrue(fourOfAKindExample.isFourOfAKind())
        assertTrue(fullHouseExample.isFullHouse())
        assertTrue(threeOfAKindExample.isThreeOfAKind())
        assertTrue(twoPairExample.isTwoPair())
        assertTrue(onePairExample.isOnePair())

        assertFalse(fiveOfAKindSample.isFourOfAKind())
        assertFalse(threeOfAKindExample.isFullHouse())
        assertFalse(fullHouseExample.isThreeOfAKind())
    }

    @Test
    fun `group pair of hand and bid, by hand type`(){
        val input = readInput("Day07_part1_test")
        val parsedInput = Day07.parseInput(input)
        val result = Day07.groupPairsByHand(parsedInput)

        assertEquals("T55J5", result[0].first.hand)
        assertEquals("QQQJA", result[1].first.hand)
        assertEquals("KK677", result[2].first.hand)
        assertEquals("KTJJT", result[3].first.hand)
        assertEquals("32T3K", result[4].first.hand)
    }

    @Test
    fun `sort hands based on handType and card number`(){
        val input = readInput("Day07_part1_test")
        val parsedInput = Day07.parseInput(input)
        val result = Day07.sortHands(parsedInput)

        assertEquals("QQQJA", result[0].first.hand)
        assertEquals("T55J5", result[1].first.hand)
        assertEquals("KK677", result[2].first.hand)
        assertEquals("KTJJT", result[3].first.hand)
        assertEquals("32T3K", result[4].first.hand)
    }

    @Test
    fun `part 1, sample input`() {
        val input = readInput("Day07_part1_test").reversed()
        val result = Day07.part1(input)

        assertEquals(6440, result)
    }

    @Test
    fun `part 1, challenge input`() {
        val input = readInput("Day07_part1")
        val result = Day07.part1(input)

        assertEquals(251058093, result)
    }

    @Test
    fun `part 2, sample input`() {
        val input = readInput("Day07_part1_test")
        val result = Day07.part2(input)

        assertEquals(5905, result)
    }

    @Test
    fun `part 2, challenge input`() {
        val input = readInput("Day07_part1")
        val result = Day07.part2(input)

        //high
        assertEquals(249781879, result)
    }
}