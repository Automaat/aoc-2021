fun main() {
    fun part1(input: List<String>): Int {
        var fishes = input[0].splitByComaToInts()

        for (day in 1..80) {
            val newFishes = fishes.toMutableList()
            for (i in fishes.indices) {
                if (fishes[i] == 0) {
                    newFishes.add(8)
                    newFishes[i] = 6
                } else {
                    newFishes[i]--
                }
            }
            fishes = newFishes
        }

        return fishes.size
    }

    fun part2(input: List<String>): Long {
        val fishes = input[0].splitByComaToInts()
        val experimentLength = 256

        val timers = mutableListOf<Long>(0, 0, 0, 0, 0, 0, 0, 0, 0, 0)
        for (f in fishes) {
            timers[f] = timers[f] + 1
        }

        for (i in 1..experimentLength) {
            val birthing = timers[0]

            for (j in 0..7) {
                timers[j] = timers[j + 1]
            }
            timers[8] = birthing
            timers[6] = timers[6] + birthing

        }
        return timers.sum()
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day06_test")
    check(part1(testInput) == 5934)
    check(part2(testInput) == 26984457539)

    val input = readInput("Day06")
    println(part1(input))
    println(part2(input))
}

data class Fish(val state: Int, val firstDay: Int)