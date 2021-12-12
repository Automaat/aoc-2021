import org.jetbrains.kotlinx.multik.api.mk
import org.jetbrains.kotlinx.multik.api.ndarray
import org.jetbrains.kotlinx.multik.ndarray.data.get
import org.jetbrains.kotlinx.multik.ndarray.data.set
import org.jetbrains.kotlinx.multik.ndarray.operations.forEachMultiIndexed
import org.jetbrains.kotlinx.multik.ndarray.operations.map
import org.jetbrains.kotlinx.multik.ndarray.operations.mapMultiIndexed

fun main() {
    fun part1(input: List<String>): Int {
        val neighbours = listOf(1 to 0, 1 to -1, 0 to -1, -1 to -1, -1 to 0, -1 to 1, 0 to 1, 1 to 1)
        val array = mutableListOf<MutableList<Int>>()
        input.forEach { line -> array.add(line.splitAndTrim().map { it.toInt() }.toMutableList()) }

        var octopuses = mk.ndarray(array)

        var allTimeFlashed = 0
        repeat(100) {
            octopuses = octopuses.map { it + 1 }

            val flashed = mutableSetOf<Pair<Int, Int>>()
            var changed = true
            while (changed) {
                changed = false
                for (i in 0..9) {
                    for (j in 0..9) {
                        if (octopuses[i, j] > 9 && (i to j !in flashed)) {
                            flashed.add(i to j)
                            changed = true
                            neighbours.forEach { nb ->
                                val newX = i + nb.first
                                val newY = j + nb.second
                                if (newX in 0..9 && newY in 0..9) {
                                    octopuses[newX, newY] += 1
                                }
                            }
                        }
                    }
                }
            }
            allTimeFlashed += flashed.size
            octopuses = octopuses.map { if (it > 9) 0 else it }
        }

        return allTimeFlashed
    }

    fun part2(input: List<String>): Int {
        val neighbours = listOf(1 to 0, 1 to -1, 0 to -1, -1 to -1, -1 to 0, -1 to 1, 0 to 1, 1 to 1)
        val array = mutableListOf<MutableList<Int>>()
        input.forEach { line -> array.add(line.splitAndTrim().map { it.toInt() }.toMutableList()) }

        var octopuses = mk.ndarray(array)

        var allTimeFlashed = 0
        var step = 0
        while (allTimeFlashed != 100) {
            octopuses = octopuses.map { it + 1 }

            val flashed = mutableSetOf<Pair<Int, Int>>()
            var changed = true
            while (changed) {
                changed = false
                for (i in 0..9) {
                    for (j in 0..9) {
                        if (octopuses[i, j] > 9 && (i to j !in flashed)) {
                            flashed.add(i to j)
                            changed = true
                            neighbours.forEach { nb ->
                                val newX = i + nb.first
                                val newY = j + nb.second
                                if (newX in 0..9 && newY in 0..9) {
                                    octopuses[newX, newY] += 1
                                }
                            }
                        }
                    }
                }
            }
            allTimeFlashed = flashed.size
            octopuses = octopuses.map { if (it > 9) 0 else it }
            step++
        }

        return step
    }

// test if implementation meets criteria from the description, like:
    val testInput = readInput("Day11_test")
    check(part1(testInput) == 1656)
    check(part2(testInput) == 195)

    val input = readInput("Day11")
    println(part1(input))
    println(part2(input))
}
