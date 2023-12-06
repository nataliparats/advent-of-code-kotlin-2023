object Day06 {
    fun part1(input: List<String>): Int {
        val races = parseRacePart1(input)

        return races.map { race ->
            (0..race.totalTime).map { holdButtonTime ->
                holdButtonTime * (race.totalTime - holdButtonTime)
            }.count { it > race.bestRecord }
        }.fold(1) {acc, i -> acc * i }
    }

    fun parseRacePart1(input: List<String>): List<RacePart1> {
        val racesTime = parseNumbersPart1(input[0])
        val bestRecords = parseNumbersPart1(input[1])
        return racesTime.zip(bestRecords).map { (time, bestRecord) -> RacePart1 (time, bestRecord) }
    }

    private fun parseNumbersPart1(
        input: String
    ): List<Int> = Regex("\\d+").findAll(input).map { it.value.toInt() }.toList()

    fun part2(input: List<String>): Int {
        val race = parseRacePart2(input)

        // here I tried first to map and then to count, but my machine run out of heap memory,
        // so I have decided to count immediately
        return (0..race.totalTime).count { holdButtonTime ->
            (holdButtonTime * (race.totalTime - holdButtonTime)) > race.bestRecord
        }
    }

    private fun parseRacePart2(input: List<String>): RacePart2 {
        val parsedNumbers = input.map { it ->
            Regex("\\d+").findAll(it).joinToString("") { it.value}
        }.toList()

        return RacePart2(parsedNumbers[0].toLong(), parsedNumbers[1].toLong())
    }

    data class RacePart1(val totalTime: Int, val bestRecord: Int)
    data class RacePart2(val totalTime: Long, val bestRecord: Long)
}