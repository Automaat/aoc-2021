import kotlin.math.abs

fun main() {
    fun part1(input: List<String>): Int {
        val positions = input[0].splitByComaToInts()
        val max = positions.maxOrNull() ?: 0
        val min = positions.minOrNull() ?: 0

        return (min..max).map { position ->
            positions.fold(0) { acc, crab -> acc + abs(position - crab) }
        }
            .minOrNull() ?: 0
    }

    fun part2(input: List<String>): Int {
        val positions = input[0].splitByComaToInts()
        val max = positions.maxOrNull() ?: 0
        val min = positions.minOrNull() ?: 0

        return (min..max).map { position ->
            positions.fold(0) { acc, crab -> acc + stepsToFuel(abs(position - crab)) }
        }
            .minOrNull() ?: 0
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day07_test")
    check(part1(testInput) == 37)
    check(part2(testInput) == 168)

    val input = readInput("Day07")
    println(part1(input))
    println(part2(input))
}

fun stepsToFuel(steps: Int): Int {
    return generateSequence(1, Int::inc).take(steps).sum()
}