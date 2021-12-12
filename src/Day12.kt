import org.apache.commons.lang3.StringUtils

fun main() {
    fun part1(input: List<String>): Int {
        val graph = mutableMapOf<String, MutableList<String>>()
        input.forEach { edge ->
            val (start, end) = edge.split("-")
            graph.addEdge(start, end)
            graph.addEdge(end, start)
        }

        return solution(graph, "start", setOf(), "start" to 0, "start").size
    }

    fun part2(input: List<String>): Int {
        val graph = mutableMapOf<String, MutableList<String>>()
        val smallCaves = mutableListOf<Pair<String, Int>>()
        input.forEach { edge ->
            val (start, end) = edge.split("-")
            graph.addEdge(start, end)
            graph.addEdge(end, start)
        }

        graph.forEach { (node, _) ->
            if (StringUtils.isAllLowerCase(node) && node != "start" && node != "end") {
                smallCaves.add(node to 1)
            }
        }

        var sum = mutableSetOf<String>()
        for (cave in smallCaves) {
            sum += solution(graph, "start", setOf(), cave, "start")
        }

        return sum.size
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day12_test")
//    check(part1(testInput) == 10)
    check(part2(testInput) == 36)

    val input = readInput("Day12")
    println(part1(input))
    println(part2(input))
}

typealias Graph = MutableMap<String, MutableList<String>>

fun Graph.addEdge(start: String, end: String) {
    if (this[start] == null) {
        this[start] = mutableListOf(end)
    } else {
        this[start].apply { this!!.add(end) }
    }
}

fun solution(graph: Graph, current: String, visitedSmall: Set<String>, repeat: Pair<String, Int>, path:String): Set<String> {
    if (current == "end") {
        return setOf(path)
    }
    if (current in visitedSmall) {
        return setOf()
    }

    val sum = mutableSetOf<String>()
    for (neighbour in graph[current]!!) {
        sum += if (StringUtils.isAllLowerCase(current)) {
            if (current == repeat.first && repeat.second > 0) {
                solution(graph, neighbour, visitedSmall, current to repeat.second - 1, "$path,$neighbour")
            } else {
                solution(graph, neighbour, visitedSmall + current, repeat, "$path,$neighbour")
            }
        } else {
            solution(graph, neighbour, visitedSmall, repeat, "$path,$neighbour")
        }
    }

    return sum
}