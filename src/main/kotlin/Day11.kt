import kotlin.math.abs

object Day11 {
    fun part1(input: List<String>): Int {
        val galaxies = getGalaxies(input)
        val galaxiesExpanded = expandUniverse(Point(input[0].length, input.size), galaxies)
        val pairs = findPairs(galaxiesExpanded)
        return pairs.sumOf { (point1, point2) ->
            findDistance(point1, point2)
        }.toInt()
    }

    fun part2(input: List<String>, expansionRate: Int): Long {
        val galaxies = getGalaxies(input)
        val galaxiesExpanded = expandUniverse(Point(input[0].length, input.size), galaxies, expansionRate)
        val pairs = findPairs(galaxiesExpanded)
        return pairs.sumOf { (point1, point2) ->
            findDistance(point1, point2)
        }
    }

    private fun expandUniverse(endPoint: Point, galaxies: List<Point>, expansionRate: Int = 2): List<Point> {
        val emptyColumns = findEmptyColumns(endPoint, galaxies)
        val emptyRows = findEmptyRows(endPoint, galaxies)

        return galaxies.map { point ->
            val addX = emptyColumns.count { point.x > it }
            val addY = emptyRows.count { point.y > it }
            Point (point.x + addX * (expansionRate - 1),
                point.y + addY * (expansionRate - 1))
        }
    }

    private fun findEmptyRows(endPoint: Point, galaxies: List<Point>): List<Int> {
        val missingRows = mutableListOf<Int>()
        for (i in 0 until endPoint.y) {
            if (galaxies.none { point -> point.y == i })
                missingRows.add(i)
        }
        return missingRows.toList()
    }

    private fun findEmptyColumns(endPoint: Point, galaxies: List<Point>): List<Int> {
        val missingColumns = mutableListOf<Int>()
        for (i in 0 until endPoint.x) {
            if (galaxies.none { point -> point.x == i })
                missingColumns.add(i)
        }
        return missingColumns.toList()
    }

    private fun findDistance(point1: Point, point2: Point): Long =
        (abs(point1.x - point2.x) + abs(point1.y - point2.y)).toLong()

    private fun findPairs(galaxies: List<Point>): List<Pair<Point, Point>> {
        val pairs = mutableListOf<Pair<Point, Point>>()

        for (i in galaxies.indices) {
            for (j in i + 1 until galaxies.size) {
                val point1 = galaxies[i]
                val point2 = galaxies[j]

                if (point1 != point2) {
                    pairs.add(Pair(point1, point2))
                }
            }
        }

        return pairs
    }

    private fun getGalaxies(input: List<String>): List<Point> =
        input.flatMapIndexed { y, line ->
            line.mapIndexed { x, char ->
                if (char == '#') Point(x, y) else null
            }.filterNotNull()
        }

    data class Point(val x: Int, val y: Int)

}