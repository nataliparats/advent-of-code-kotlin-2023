object Day04 {
    fun part1(input: List<String>): Int {
        val cards = parseCards(input)
        return cards.sumOf { card ->
            val cardMatchesCount = numberOfMatches(card)
            if (cardMatchesCount != 0)
                power(cardMatchesCount-1)
            else
                0
        }
    }

    fun part2(input: List<String>): Int {
        val cards = parseCards(input)
        val cardToMatchesCountMap = cards.associate { card ->
            card.number to numberOfMatches(card)
        }

        fun addCardPlusDependencies(cardId: Int): List<Int> {
            val matchesForThisCard = cardToMatchesCountMap[cardId] ?: 0
            return if (matchesForThisCard == 0) listOf(cardId)
            else listOf(cardId) + (1..matchesForThisCard).flatMap {
                addCardPlusDependencies(cardId + it)
            }
        }

        return cardToMatchesCountMap.keys.flatMap { cardNumber ->
            addCardPlusDependencies(cardNumber)
        }.size
    }

    private fun numberOfMatches(card: Card) = card.numbersYouHave.toSet()
        .intersect(card.winningNumbers.toSet()).count()

    fun parseCards(input: List<String>): List<Card> {
        return input.map { cardString ->
            val (cardNumberString, numbersString) = cardString.split(":")

            val regex = Regex("\\d+")
            val cardNumber = regex.find(cardNumberString)!!.value.toInt()

            val (winningNumbersString, numbersYouHaveString) = numbersString.split("|")
            val winningNumbers = winningNumbersString.trim().split(" ")
                .filter{it.isNotBlank()}
                .map { it.toInt() }
            val numbersYouHave = numbersYouHaveString.trim().split(" ")
                .filter{it.isNotBlank()}
                .map { it.toInt() }
            Card(cardNumber, winningNumbers, numbersYouHave)
        }.toList()
    }

    private fun power(exponentVal: Int): Int =
        when (exponentVal) {
            0 -> 1
            else -> 2  * power(exponentVal - 1)
        }

    data class Card(val number: Int, val winningNumbers: List<Int>, val numbersYouHave: List<Int>)
}