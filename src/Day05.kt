fun main() {
    fun part1(input: List<String>): Int {
        val used = mutableMapOf<Pair<Int, Int>, Int>()
        for (i in input) {
            val coords = parseLine(i)
            val x = coords[0][0].toInt()
            val x2 = coords[1][0].toInt()
            val y = coords[0][1].toInt()
            val y2 = coords[1][1].toInt()

            if (x == x2) {
                val (from, to) = takeLower(y, y2)
                for (idx in from..to) {
                    val point = x to idx
                    used.incrementInMap(point)
                }
            } else if (y == y2) {
                val (from, to) = takeLower(x, x2)
                for (idx in from..to) {
                    val point = idx to y
                    used.incrementInMap(point)
                }
            }
        }
        
        return used.filter { it.value > 1 }.size
    }

    fun part2(input: List<String>): Int {
        val used = mutableMapOf<Pair<Int, Int>, Int>()
        
        for (i in input) {
            val coords = parseLine(i)
            val x = coords[0][0].toInt()
            val x2 = coords[1][0].toInt()
            val y = coords[0][1].toInt()
            val y2 = coords[1][1].toInt()
            
            if (x == x2) {
                var (from, to) = takeLower(y, y2)
                for (idx in from..to) {
                    val point = x to idx
                    used.incrementInMap(point)
                }
            } else if (y == y2) {
                var (from, to) = takeLower(x, x2)
                for (idx in from..to) {
                    val point = idx to y
                    used.incrementInMap(point)
                }
            } else {
                var startX = coords[0][0].toInt()
                var endX = coords[1][0].toInt()
                var startY = coords[0][1].toInt()
                var endY = coords[1][1].toInt()

                while (true) {
                    val point = startX to startY
                    used.incrementInMap(point)

                    if (startX == endX) {
                        break
                    } else {
                        if (startX < endX) {
                            startX++
                        } else {
                            startX--
                        }

                        if (startY < endY) {
                            startY++
                        } else {
                            startY--
                        }
                    }
                }
            }
        }
        return used.filter { it.value > 1 }.size
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day05_test")
    check(part1(testInput) == 5)
    check(part2(testInput) == 12)

    val input = readInput("Day05")
    println(part1(input))
    println(part2(input))
}

fun takeLower(a: Int, b: Int): Pair<Int, Int> {
    return if (a < b) Pair(a, b) else Pair(b, a)
}

fun parseLine(i: String) = i.split(" -> ").map { it.splitByComa() }