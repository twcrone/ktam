import kotlinx.cinterop.ByteVar
import kotlinx.cinterop.allocArray
import kotlinx.cinterop.memScoped
import kotlinx.cinterop.toKString
import platform.posix.fclose
import platform.posix.fgets
import platform.posix.fopen

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

    companion object {
        fun from(filename: String): Aliases {
            val aliases = Aliases()
            val file = fopen(filename, "r")
            try {
                memScoped {
                    val readBufferLength = 64 * 1024
                    val buffer = allocArray<ByteVar>(readBufferLength)
                    var line = fgets(buffer, readBufferLength, file)?.toKString()
                    while (line != null) {
                        aliases.add(line)
                        line = fgets(buffer, readBufferLength, file)?.toKString()
                    }
                }
            } finally {
                fclose(file)
            }
            return aliases
        }
    }
}
