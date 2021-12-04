fun main() {
    fun part1(input: List<String>): Int {
        val pickedNumbers = input[0].split(",").map { it.toInt() }
        val boards = readBoards(input)

        var sumOfUnmarked = 0
        var winnigNumber = 0
        loop@ for (picked in pickedNumbers) {
            for (board in boards) {
                for (elems in board.possible) {
                    elems.remove(picked)
                    board.allNumbers.remove(picked)
                    if (elems.size == 0) {
                        sumOfUnmarked = sumUnmarked(board)
                        winnigNumber = picked
                        break@loop
                    }
                }
            }
        }

        return sumOfUnmarked * winnigNumber
    }

    fun part2(input: List<String>): Int {
        val pickedNumbers = input[0].split(",").map { it.toInt() }
        val boards = readBoards(input)

        var sumOfUnmarked = 0
        var winnigNumber = 0
        var bingo = false
        var winningBoard = mutableListOf<Board>()
        for (picked in pickedNumbers) {
            for (board in boards) {
                for (elems in board.possible) {
                    elems.remove(picked)
                    board.allNumbers.remove(picked)
                    if (elems.size == 0) {
                        sumOfUnmarked = sumUnmarked(board)
                        winnigNumber = picked
                        bingo = true
                        winningBoard.add(board)
                    }
                }
            }
            if (bingo) {
                boards.removeAll(winningBoard)
                winningBoard = mutableListOf()
                bingo = false
            }
        }

        return sumOfUnmarked * winnigNumber
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day04_test")
    check(part1(testInput) == 4512)
    check(part2(testInput) == 1924)

    val input = readInput("Day04")
    println(part1(input))
    println(part2(input))
}

data class Board(val possible: MutableList<MutableSet<Int>> = mutableListOf(), val allNumbers: MutableSet<Int> = mutableSetOf())

fun sumUnmarked(board: Board): Int {
    var sum = 0
    for (elems in board.allNumbers) {
        sum += elems
    }
    return sum
}

fun readBoards(input: List<String>): MutableList<Board> {
    val boards = mutableListOf<Board>()
    var i = 2
    while (i < input.size) {
        val board = Board()
        val columns = mutableListOf(
            mutableSetOf(),
            mutableSetOf(),
            mutableSetOf(),
            mutableSetOf(),
            mutableSetOf<Int>()
        )
        for (j in i..i + 4) {
            val row = mutableSetOf<Int>()
            for ((l, k) in input[j].trimStart().split("\\s+".toRegex()).withIndex()) {
                row.add(k.toInt())
                columns[l].add(k.toInt())
                board.allNumbers.add(k.toInt())
            }
            board.possible.add(row)
        }
        board.possible.addAll(columns)
        boards.add(board)
        i += 6
    }

    return boards
}