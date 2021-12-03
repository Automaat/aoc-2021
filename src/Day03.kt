fun main() {
    fun part1(input: List<String>): Int {
        val ones = IntArray(input[0].split("").size - 2)
        val zeros = IntArray(input[0].split("").size - 2)

        for (i in input) {
            val elems = i.split("").dropLast(1).drop(1)
            elems.forEachIndexed { index, e ->
                val num = e.toInt()
                when (num) {
                    0 -> zeros[index]++
                    1 -> ones[index]++
                }
            }
        }

        var gamma = ""
        var epsilon = ""
        for (i in ones.indices) {
            if (ones[i] > zeros[i]) {
                gamma = "${gamma}1"
                epsilon = "${epsilon}0"
            } else {
                gamma = "${gamma}0"
                epsilon = "${epsilon}1"
            }
        }
        return Integer.parseInt(gamma, 2) * Integer.parseInt(epsilon, 2)
    }

    fun part2(input: List<String>): Int {
        var oxygen = input.toList()
        var i = 0
        while (oxygen.size > 1) {
            val pair = countZerosAndOnes(oxygen, i)
            val ones = pair.second
            val zeros = pair.first

            oxygen = if (ones == zeros) {
                ArrayList(oxygen.filter { it[i] == '1' })
            } else if (ones > zeros) {
                ArrayList(oxygen.filter { it[i] == '1' })
            } else {
                ArrayList(oxygen.filter { it[i] == '0' })
            }
            i++
        }

        var co2 = input.toList()
        var j = 0
        while (co2.size > 1) {
            val pair = countZerosAndOnes(co2, j)
            var ones = pair.second
            var zeros = pair.first
            for (c in co2) {
                val elems = c.split("").dropLast(1).drop(1)
                when(elems[j].toInt()) {
                    0 -> zeros++
                    1 -> ones++
                }
            }
            co2 = if (ones == zeros) {
                ArrayList(co2.filter { it[j] == '0' })
            } else if (ones < zeros) {
                ArrayList(co2.filter { it[j] == '1' })
            } else {
                ArrayList(co2.filter { it[j] == '0' })
            }
            j++
        }

        return Integer.parseInt(oxygen[0], 2) * Integer.parseInt(co2[0], 2)
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day03_test")
    check(part1(testInput) == 198)
    check(part2(testInput) == 230)

    val input = readInput("Day03")
    println(part1(input))
    println(part2(input))
}

fun countZerosAndOnes(oxygen: List<String>, i: Int): Pair<Int, Int> {
    var zeros = 0
    var ones = 0
    for (o in oxygen) {
        val elems = o.split("").dropLast(1).drop(1)
        when (elems[i].toInt()) {
            0 -> zeros++
            1 -> ones++
        }
    }
    return Pair(zeros, ones)
}