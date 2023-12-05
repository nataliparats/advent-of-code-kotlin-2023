import java.util.concurrent.*

object Day05 {

    fun part1(input: List<String>): Long {
        val seeds = parseSeeds(input[0])
        val seedsMaps = parseMaps(input)

        return seeds.minOfOrNull { seed ->
            findSeedLocation(seed, seedsMaps)
        } ?: 0L
    }
    private val worker = Executors.newFixedThreadPool(7)
    fun part2(input: List<String>): Long {
        val seedRanges = parseSeedsPart2(input[0])
        val seedsMaps = parseMaps(input)


        val futures: List<Future<Long>> = seedRanges.map { seedRange ->
            val callable: Callable<Long> = Callable {
                seedRange.minOf { seed ->
                   findSeedLocation(seed, seedsMaps)
               }
            }
            worker.submit(callable)
        }
        return futures.minOf { it.get() }

    }

    private fun parseSeedsPart2(seedsInput: String): List<LongRange> =
        seedsInput.split(" ")
            .drop(1)
            .chunked(2)
            .map { seedRange -> seedRange[0].toLong() until  seedRange[0].toLong() + seedRange[1].toLong() }
            .toList()

    private fun findSeedLocation(
        seed: Long,
        seedsMaps: List<List<RangeMap>>
    ): Long {
        var location = seed
        seedsMaps.map {
            location = it.resolveLocation(location)
        }
        return location
    }

    fun parseMaps(input: List<String>): List<List<RangeMap>> {
        val allOfThem: MutableList<List<RangeMap>> = mutableListOf()
        var currentMap = mutableListOf<RangeMap>()
        input.filter { it.isNotEmpty() }
            .drop(1)
            .map { line ->
                if (line[0].isDigit()) {
                    val (d, s, l) = line.split(" ").map { it.toLong() }
                    currentMap.add(RangeMap(d, s, l))
                } else {
                    allOfThem.add(currentMap)
                    currentMap = mutableListOf()
                }
            }
        allOfThem.add(currentMap)
        return allOfThem.drop(1).toList()
    }

    fun parseSeeds(seedsInput: String): List<Long> =
        seedsInput.split(" ")
            .drop(1)
            .map { it.toLong() }

    fun List<RangeMap>.resolveLocation(number: Long): Long =
        this.firstOrNull{ it.isNumberInRange(number) }
            ?.resolveLocation(number) ?: number


    data class RangeMap (val destinationRangeStart: Long, val sourceRangeStart: Long, val rangeLength: Long) {
        fun isNumberInRange(number: Long): Boolean =
            (sourceRangeStart until  sourceRangeStart+rangeLength).contains(number)

        fun resolveLocation(number: Long): Long =
            destinationRangeStart + (number - sourceRangeStart)
    }


}