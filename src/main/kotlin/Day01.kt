object Day01 {
    fun part1(input: List<String>): Int =
        input.map { line ->
            line.filter { it.isDigit() }
                .let { digitsString ->
                    val first = digitsString.first().toString()
                    val last = digitsString.last().toString()
                    first + last
                }
        }.sumOf { it.toInt() }


    fun part2(input: List<String>): Int =
        input.sumOf { line ->
            line.findFirstDigit() * 10 + line.findLastDigit()
        }

    private fun String.findFirstDigit() =
        this.findAnyOf(numberMap.keys)!!.second.let { numberMap[it]!! }

    private fun String.findLastDigit() =
        this.findLastAnyOf(numberMap.keys)!!.second.let { numberMap[it]!! }

    private val numberMap =
        (0..9).associateBy { it.toString() } +
        mapOf(
        "one" to 1,
        "two" to 2,
        "three" to 3,
        "four" to 4,
        "five" to 5,
        "six" to 6,
        "seven" to 7,
        "eight" to 8,
        "nine" to 9,
    )

}
