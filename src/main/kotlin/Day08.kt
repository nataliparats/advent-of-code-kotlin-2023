object Day08 {

    fun part1(input: List<String>): Int {
        val directions = parseDirections(input)
        val points = parseInputPoints(input)

        val startingPoint = "AAA"

        return points.travelUntil(startingPoint, directions) { it == "ZZZ" }
    }

    fun part2(input: List<String>): Long {
        val directions = parseDirections(input)
        val points = parseInputPoints(input)

        return points.keys.filter { it.endsWith('A') }
            .map { startingPoint ->
                points.travelUntil(startingPoint, directions) { it.endsWith('Z') }
            }.map { it.toLong() }.lcm()
    }

    private fun Map<String, Pair<String, String>>.travelUntil(
        startingPoint: String,
        directions: CircularList<String>,
        endCondition: (String) -> Boolean
    ): Int {
        var currentPoint = startingPoint
        var step = 0
        while (!endCondition(currentPoint)) {
            currentPoint = if (directions[step] == "L")
                this[currentPoint]!!.first
            else
                this[currentPoint]!!.second
            step++
        }
        return step
    }

    private fun List<Long>.lcm(): Long {
        return fold(1) {acc, i -> lcm(acc, i) }
    }

    private fun lcm(a: Long, b: Long) =
        a / gcd(a, b) * b

    private fun gcd(a: Long, b: Long): Long =
        if(b == 0L) a else gcd(b, a % b)

    fun parseInputPoints(input: List<String>): Map<String, Pair<String, String>> =
        input.drop(2).associate { line ->
            val (point, directions) = line.split("=").map { it.trim() }
            val regex = Regex("\\w+")
            val pairOfDirectionPoints =
                regex.findAll(directions).let { Pair(it.first().value, it.last().value) }
            point to Pair(pairOfDirectionPoints.first, pairOfDirectionPoints.second)
        }

    fun parseDirections(input: List<String>): CircularList<String> =
        input[0].chunked(1).circular()

    // taken from https://todd.ginsberg.com/post/cirkle/
    private fun <T> List<T>.circular(): CircularList<T> = CircularList(this)

    class CircularList<out T>(private val list: List<T>) : List<T> by list {

        override fun get(index: Int): T =
            list[index.safely()]

        // Other overrides removed for brevity.

        private fun Int.safely(): Int =
            if (this < 0) (this % size + size) % size
            else this % size

    }
}