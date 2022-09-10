open class Alias(val name: String, val filepath: String) {
    override fun toString() = "alias $name=$filepath"
}

fun aliasFrom(line: String): Alias? {
    val tokens = line.split("=")
    if(tokens.size == 1) {
        return NoAlias
    }
    val first = tokens[0].substring(6)
    val second = tokens[1]
    return Alias(first, second)
}

object NoAlias : Alias("", "")