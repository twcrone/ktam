import kotlinx.cinterop.ByteVar
import kotlinx.cinterop.allocArray
import kotlinx.cinterop.memScoped
import kotlinx.cinterop.toKString
import platform.posix.fclose
import platform.posix.fgets
import platform.posix.fopen
import platform.posix.getenv

fun main() {
    val aliases = Aliases()
    val homeDir = getenv("HOME")?.toKString() ?: "~"
    val file = fopen("$homeDir/.alias", "r")
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
    val maxNameLength = aliases.list().maxBy { it.name.length }.name.length

    aliases.list().forEach {
        val dots = maxNameLength - it.name.length + 3
        print(it.name)
        (1..dots).forEach { _ -> print(".") }
        println(it.filepath.replace(homeDir, "~"))
    }
}