import java.io.File
import java.math.BigInteger
import java.security.MessageDigest

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