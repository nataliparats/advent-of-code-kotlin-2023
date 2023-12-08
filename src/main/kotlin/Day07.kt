import Day07.HandType.*

object Day07 {
    fun part1(input: List<String>): Int =
        calculateTotalWinnings(input)

    fun part2(input: List<String>): Int {
        val withJoker = true
        return calculateTotalWinnings(input, withJoker)
    }

    private fun calculateTotalWinnings(input: List<String>, withJoker: Boolean = false): Int {
        val pairs = parseInput(input, withJoker)

        val reversed = sortHands(pairs, withJoker)
            .reversed()
        return reversed
            .mapIndexed { index, (_, bid) ->
                bid * (index + 1)
            }.sum()
    }

    fun sortHands(pairs: List<Pair<Hand, Int>>, withJoker: Boolean = false) =
        pairs.sortedWith(
            compareBy<Pair<Hand, Int>> { it.first.handType }
                .then { t1, t2 -> t1.first.hand.compareHandTo(t2.first.hand, withJoker) })

    private fun String.compareHandTo(other: String, withJoker: Boolean = false): Int {
        val t1 = this[0]
        val o1 = other[0]
        val cards = if (withJoker)
            cardValuesWithJoker
        else
            cardValues
        val firstCharCompared = cards.indexOf(o1).compareTo(cards.indexOf(t1))
        return if (firstCharCompared == 0) {
            return this.drop(1).compareHandTo(other.drop(1), withJoker)
        } else firstCharCompared
    }

    fun groupPairsByHand(pairs: List<Pair<Hand, Int>>): List<Pair<Hand, Int>> =
        pairs.sortedBy { it.first.handType }

    fun parseInput(input: List<String>, withJoker: Boolean = false): List<Pair<Hand, Int>> =
        input.map { line ->
            val (hand, bid) = line.split(" ")
            if (withJoker)
                Pair(Hand(hand, getHandTypeWithJoker(hand)), bid.toInt())
            else
                Pair(Hand(hand, getHandType(hand)), bid.toInt())
        }

    data class Hand (val hand: String, val handType: HandType)

    private fun getHandType(hand: String): HandType =
        when {
        hand.isFiveOfAKind() -> FIVE_OF_A_KIND
        hand.isFourOfAKind() -> FOUR_OF_A_KIND
        hand.isFullHouse() -> FULL_HOUSE
        hand.isThreeOfAKind() -> THREE_OF_A_KIND
        hand.isTwoPair() -> TWO_PAIR
        hand.isOnePair() -> ONE_PAIR
        else -> HIGH_CARD
    }

    private fun getHandTypeWithJoker(hand: String): HandType =
        when {
            hand.isFiveOfAKind() -> FIVE_OF_A_KIND
            hand.isFourOfAKind() -> {
                if (hand.contains('J')) FIVE_OF_A_KIND
                else FOUR_OF_A_KIND
            }
            hand.isFullHouse() -> {
                if (hand.contains('J')) FIVE_OF_A_KIND
                else FULL_HOUSE
            }
            hand.isThreeOfAKind() -> {
                if (hand.contains('J')) FOUR_OF_A_KIND
                else THREE_OF_A_KIND
            }
            hand.isTwoPair() -> {
                when(hand.count { it == 'J' }) {
                    2 -> FOUR_OF_A_KIND
                    1 -> FULL_HOUSE
                    else -> TWO_PAIR
                }
            }
            hand.isOnePair() -> {
                if (hand.contains('J')) THREE_OF_A_KIND
                else ONE_PAIR
            }
            hand.contains('J') -> ONE_PAIR
            else -> HIGH_CARD
        }

    fun String.isFiveOfAKind(): Boolean = this.cardCount().maxOf { it.value == 5 }

    fun String.isFourOfAKind(): Boolean = this.cardCount().maxOf { it.value == 4 }

    fun String.isFullHouse(): Boolean = this.cardCount().values.sorted() == listOf(2,3)

    fun String.isThreeOfAKind(): Boolean = this.cardCount().maxOf { it.value == 3 } && this.cardCount().size == 3
    fun String.isTwoPair(): Boolean = this.cardCount().values.sorted() == listOf(1,2,2)
    fun String.isOnePair(): Boolean = this.cardCount().maxOf { it.value == 2 }
    private fun String.cardCount(): Map<Char, Int>
            = this.groupingBy { it }
        .eachCount()

    private val cardValues = listOf('2', '3', '4', '5', '6', '7', '8', '9', 'T', 'J', 'Q', 'K', 'A')

    private val cardValuesWithJoker = listOf('J', '2', '3', '4', '5', '6', '7', '8', '9', 'T', 'Q', 'K', 'A')

    enum class HandType {
        FIVE_OF_A_KIND,
        FOUR_OF_A_KIND,
        FULL_HOUSE,
        THREE_OF_A_KIND,
        TWO_PAIR,
        ONE_PAIR,
        HIGH_CARD
    }
}
