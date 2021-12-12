import java.io.File
import java.math.BigInteger
import java.security.MessageDigest
import java.util.Collections.swap

/**
 * Reads lines from the given input txt file.
 */
fun readInput(name: String) = File("src", "$name.txt").readLines()

/**
 * Converts string to md5 hash.
 */
fun String.md5(): String = BigInteger(1, MessageDigest.getInstance("MD5").digest(toByteArray())).toString(16)

fun <K> MutableMap<K, Int>.incrementInMap(key: K) {
    if (this.contains(key)) {
        this[key] = this[key]!! + 1
    } else {
        this[key] = 1
    }
}

fun List<String>.toInts(): List<Int> {
    return this.map { it.toInt() }
}

fun String.splitByComa(): List<String> {
    return this.split(",")
}

fun String.splitBySpace(): List<String> {
    return this.split(" ")
}

fun String.splitByComaToInts(): MutableList<Int> {
    return this.splitByComa().toInts().toMutableList()
}

fun <T> List<T>.copy(): MutableList<T> {
    return this.toMutableList()
}

fun <V> List<V>.permutations(): List<List<V>> {
    val retVal: MutableList<List<V>> = mutableListOf()

    fun generate(k: Int, list: List<V>) {
        // If only 1 element, just output the array
        if (k == 1) {
            retVal.add(list.toList())
        } else {
            for (i in 0 until k) {
                generate(k - 1, list)
                if (k % 2 == 0) {
                    swap(list, i, k - 1)
                } else {
                    swap(list, 0, k - 1)
                }
            }
        }
    }

    generate(this.count(), this.toList())
    return retVal
}

fun String.splitAndTrim() = this.split("").drop(1).dropLast(1)
