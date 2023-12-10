object Day09 {

    fun part1(input: List<String>): Int =
        parseInput(input).sumOf { findNextItemInHistory(it) }

    fun part2(input: List<String>): Int =
        parseInput(input).sumOf { findNextItemInHistory(it.reversed()) }

    private fun findNextItemInHistory(histories: List<Int>): Int =
        if (histories.all { it == 0 })
            0
        else findNextItemInHistory(
            histories.windowed(2).map { (a, b) -> b - a}
        ) + histories.last()


    private fun parseInput(input: List<String>) =
        input.map { line -> line.split(" ").map { it.toInt() }}

}