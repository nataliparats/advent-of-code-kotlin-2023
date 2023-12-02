import kotlin.math.max

object Day02 {
    fun part1(input: List<String>): Int {
        val games = parseGames(input)
        return games.sumOf { game -> game.possibleGameId() }
    }

    fun part2(input: List<String>): Int {
        val games = parseGames(input)
        return games.sumOf { game ->
            game.subsets.fold(Subset(0,0,0)) { acc, subset ->
                Subset(
                    red = max(acc.red, subset.red),
                    blue = max(acc.blue, subset.blue),
                    green = max(acc.green, subset.green),
                    )
            }.let {
                it.blue * it.red * it.green
            }
        }
    }

    data class Game(val id: Int, val subsets: List<Subset>)

    private fun Game.possibleGameId(): Int {
        if (subsets.all {
            it.red<=12 && it.green<=13 && it.blue<=14} ) return id
        return 0
    }

    data class Subset(val red: Int, val green: Int, val blue: Int)

    fun parseGames(input: List<String>): List<Game> =
        input.map { line ->
            val gameRegex = Regex("""Game (\d+): (.*)""")
            val (gameId, rest) = gameRegex.find(line)!!.destructured
            val subsets = rest.split("; ").map { subset ->
                subset.split(", ").associate { colorDraw ->
                    val (number, color) = colorDraw.split(" ")
                    color to number.toInt()
                }.let {
                    Subset(red = it["red"] ?: 0, blue = it["blue"] ?: 0, green = it["green"] ?: 0)
                }
            }.toList()
            Game(gameId.toInt(), subsets)
        }.toList()

}

