fun main() {
    fun part1(input: List<String>): Int {
        var horizontalPosition = 0
        var depth = 0

        for (i in input) {
            val procedure = i.split(" ")
            when (procedure[0]) {
                "up" -> depth -= procedure[1].toInt()
                "down" -> depth += procedure[1].toInt()
                "forward" -> horizontalPosition += procedure[1].toInt()
            }
        }
        return horizontalPosition * depth
    }

    fun part2(input: List<String>): Int {
        var horizontalPosition = 0
        var depth = 0
        var aim = 0

        for (i in input) {
            val procedure = i.split(" ")
            when (procedure[0]) {
                "up" -> aim -= procedure[1].toInt()
                "down" -> aim += procedure[1].toInt()
                "forward" -> {
                    horizontalPosition += procedure[1].toInt()
                    depth += aim * procedure[1].toInt()
                }
            }
        }
        return horizontalPosition * depth
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day02_test")
    println(part2(testInput))
    check(part1(testInput) == 150)
    check(part2(testInput) == 900)

    val input = readInput("Day02")
    println(part1(input))
    println(part2(input))
}
