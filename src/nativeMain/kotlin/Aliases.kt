import kotlinx.cinterop.ByteVar
import kotlinx.cinterop.allocArray
import kotlinx.cinterop.memScoped
import kotlinx.cinterop.toKString
import platform.posix.fclose
import platform.posix.fgets
import platform.posix.fopen
import platform.posix.fputs

class Aliases {
    private val map = mutableMapOf<String, Alias>()

    fun add(line: String) {
        val alias = Alias.from(line)
        if(alias == NoAlias) {
            println("No alias")
        } else {
            map[alias.name] = alias
        }
    }

    fun add(name: String, filepath: String) {
        val alias = Alias(name, filepath)
        map[alias.name] = alias
    }

    fun remove(name: String) {
        map.remove(name)
    }

    fun size() = map.size

    fun list() = map.values.toList().sortedBy { it.name }

    fun writeTo(filename: String) {
        val file = fopen(filename, "w") ?:
            throw IllegalArgumentException("Cannot open file $filename for output")
        try {
            memScoped {
              list().forEach {
                  fputs(it.toString(), file)
                  fputs("\n", file)
              }
            }
        } finally {
            fclose(file)
        }
    }

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
