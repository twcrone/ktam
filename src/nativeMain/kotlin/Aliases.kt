class Aliases {
    private val map = mutableMapOf<String, Alias>()

    fun add(line: String) {
        val alias = Alias.from(line)
        map[alias.name] = alias
    }

    fun remove(name: String) {
        map.remove(name)
    }

    fun size() = map.size

    fun list() = map.values.toList().sortedBy { it.name }
}
