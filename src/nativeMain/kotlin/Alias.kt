open class Alias(val name: String, val filepath: String) {
    override fun toString() = "alias $name=$filepath"

    companion object {
        fun from(line: String): Alias {
            val tokens = line.replace("\n", "").split("=")
            if(tokens.size == 1) {
                return NoAlias
            }
            val first = tokens[0].substring(6)
            val second = tokens[1]
            return Alias(first, second)
        }
    }
}

class Aliases {
    private val map = mutableMapOf<String, Alias>()

    fun add(line: String) {
        val alias = Alias.from(line)
        map[alias.name] = alias
    }

    fun size() = map.size

    fun list() = map.values.toList().sortedBy { it.name }
}

object NoAlias : Alias("", "")