data class Alias(val name: String, val filepath: String)

fun aliasFrom(line: String): Alias? {
    val tokens = line.split("=")
    val first = tokens[0].substring(6)
    val second = tokens[1]
    return Alias(first, second)
}