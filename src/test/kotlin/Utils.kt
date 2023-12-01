
/**
 * Reads lines from the given input txt file.
 */
fun readInput(name: String) = object {}.javaClass.getResource("/${name}.txt")!!.readText().lines()

