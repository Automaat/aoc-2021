fun main() {
    fun part1(input: List<String>): Int {
        var lastDepth = 1000000
        var result = 0
        for (i in input) {
            val actualDepth = i.toInt()
            if (actualDepth > lastDepth) {
                result++
            }
            lastDepth = actualDepth
        }
        return result
    }

    fun part2(input: List<String>): Int {
        var lastDepth = 1000000
        var result = 0
        for (i in 0..input.size) {
            if (i < input.size - 2) {
                val depth = input[i].toInt()
                val depth1 = input[i+1].toInt()
                val depth2 = input[i+2].toInt()

                val window = depth + depth1 + depth2
                if (window > lastDepth) {
                    result++
                }
                lastDepth = window
            }
        }
        return result
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day01_test")
    check(part1(testInput) == 7)
    check(part2(testInput) == 5)

    val input = readInput("Day01")
    println(part1(input))
    println(part2(input))
}
