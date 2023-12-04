import kotlin.math.abs

object Day03 {
    fun part1(input: List<String>): Int {
        val symbols = findSymbols(input)
        val numbers = findNumbers(input)

        return numbers.filter { number ->
            number.points.any { numberPoint ->
                symbols.any { symbolPoint ->
                    isAdjacent(numberPoint, symbolPoint)
                }
            }
        }.sumOf { number -> number.value }
    }

    fun part2(input: List<String>): Int {
        val symbols = findSymbols(input).filter { it.value == '*' }
        val numbers = findNumbers(input)


        return symbols.sumOf { symbol ->
            val adjacentNumbers = numbers.filter { number ->
                number.points.any { numberPoint ->
                    isAdjacent(numberPoint, symbol)
                }
            }
            if (adjacentNumbers.size == 2) {
                val (n1, n2) = adjacentNumbers
                n1.value * n2.value
            } else 0
        }
    }

    private fun findNumbers(input: List<String>): Set<Number> {
        val numbers = mutableSetOf<Number>()
        input.forEachIndexed { index, line ->
            val numberRegex = Regex("\\d+")
            numberRegex.findAll(line).forEach { match ->
                numbers.add(Number(match.value.toInt(), match.range.map { Point(it, index) }))
            }
        }
        return numbers.toSet()
    }

    private fun findSymbols(input: List<String>): Set<Symbol> {
        val symbols = mutableSetOf<Symbol>()

        input.forEachIndexed { y, line ->
            line.forEachIndexed { charIndex, char ->
                if (!char.isDigit() && char != '.') {
                    symbols.add(Symbol(char, Point(charIndex, y)))
                }
            }
        }

        return symbols.toSet()
    }

    private fun isAdjacent(numberPoint: Point, symbolPoint: Symbol) = (abs(numberPoint.x - symbolPoint.point.x) <= 1
            &&
            abs(numberPoint.y - symbolPoint.point.y) <= 1)

    data class Point (val x: Int, val y: Int)
    data class Number(val value: Int, val points: List<Point>)

    data class Symbol(val value: Char, val point: Point)
}