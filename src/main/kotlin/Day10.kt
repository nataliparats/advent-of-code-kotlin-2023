import Day10.Direction.*
import java.lang.IllegalStateException

object Day10 {

    fun part1(input: List<String>): Int {

        val grid = parseInput(input)
        val startPoint = grid.filterValues { it == 'S' }.keys.first()
        val visitedPoints = mutableListOf<Point>()

        val firstPointFromStart = grid.findFirstPointFromStart(startPoint)
        visitedPoints.add(startPoint)
        visitedPoints.add(firstPointFromStart)

        while (true){
            val (point1, point2) = possibleMoves(visitedPoints, grid)
            val nextPoint = listOf(point1, point2).firstOrNull { it !in visitedPoints } ?: break
            visitedPoints.add(nextPoint)
        }

        return visitedPoints.size/2
    }

    private fun possibleMoves(
        visitedPoints: MutableList<Point>,
        grid: Map<Point, Char>
    ): Pair<Point, Point> {
        val visitedLast = visitedPoints.last()
        return when (grid[visitedLast]) {
            '|' -> NORTH to SOUTH
            '-' -> EAST to WEST
            'L' -> NORTH to EAST
            'J' -> NORTH to WEST
            '7' -> SOUTH to WEST
            'F' -> SOUTH to EAST
            else -> throw IllegalStateException("Erroneous character")
        }.let { visitedLast.move(it.first) to visitedLast.move(it.second) }
    }

    private fun parseInput(input: List<String>): Map<Point, Char> =
        input.flatMapIndexed { y, line ->
            line.toCharArray().mapIndexed { x, char ->
                Point(x, y) to char
            }
        }.toMap()

    private fun Map<Point, Char>.findFirstPointFromStart(point: Point): Point =
        when {
            this[point.move(WEST)] in listOf('F', 'L', '-') -> point.move(WEST)
            this[point.move(NORTH)] in listOf('|', 'J', '7') -> point.move(NORTH)
            this[point.move(EAST)] in listOf('-', 'J', '7') -> point.move(EAST)
            else -> throw IllegalStateException("You should have found one point already")
        }


    data class Point(val x: Int, val y: Int) {
        fun move(direction: Direction) =
            when (direction) {
                NORTH -> this.copy(y = y - 1)
                EAST -> this.copy(x = x + 1)
                SOUTH -> this.copy(y = y + 1)
                WEST -> this.copy(x = x - 1)
            }
    }

    enum class Direction {
        NORTH,
        EAST,
        SOUTH,
        WEST
    }

}