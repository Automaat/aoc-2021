fun main() {
    fun part1(input: List<String>): Int {
        val digitsSize = mapOf(2 to 1, 4 to 4, 3 to 7, 7 to 8)
        return input
            .map { it.split(" | ")[1] }
            .flatMap { it.splitBySpace() }
            .filter { digitsSize[it.length] != null }
            .size
    }

    fun part2(input: List<String>): Int {
        return input
            .map {
                val line = it.split(" | ")
                line[0].splitBySpace() to line[1].splitBySpace()
            }
            .sumOf { (input, output) -> mapToProperDigit(input, output) }
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day08_test")
    check(part1(testInput) == 26)
    check(part2(testInput) == 61229)

    val input = readInput("Day08")
    println(part1(input))
    println(part2(input))
}

fun mapToProperDigit(input: List<String>, output: List<String>): Int {
    val ok = listOf("abcefg", "cf", "acdeg", "acdfg", "bcdf", "abdfg", "abdefg", "acf", "abcdefg", "abcdfg")
    val correctLIst = "abcdefg".splitAndTrim()

    var digits = ""
    "abcdefg"
        .splitAndTrim()
        .permutations()
        .forEach perm@{ permutation ->
            val map = mutableMapOf<String, String>()
            for (i in permutation.indices) {
                map[correctLIst[i]] = permutation[i]
            }

            var found = true

            for (word in input) {
                val new = mapWord(word, map)
                if (!ok.contains(new)) {
                    found = false
                    break
                }
            }
            if (found) {
                for (word in output) {
                    val realOut = mapWord(word, map)
                    digits += "${ok.indexOf(realOut)}"
                }
                return@perm
            }
        }
    return digits.toInt()
}

private fun mapWord(
    word: String,
    map: MutableMap<String, String>
) = word.splitAndTrim().map { map[it] }.sortedBy { it }.joinToString("")

fun String.splitAndTrim() = this.split("").drop(1).dropLast(1)