import kotlinx.cinterop.toKString
import platform.posix.getenv

fun main() {
    val homeDir = getenv("HOME")?.toKString() ?: "~"
    val aliases = Aliases.from("$homeDir/.alias")

    // https://en.wikipedia.org/wiki/ANSI_escape_code#Colors
    val brightBlue = "\u001b[94m"
    val blue = "\u001b[34m"
    val cyan = "\u001b[36m"
    val green = "\u001b[32m"
    val brightRed = "\u001b[91m"
    val red = "\u001b[31m"
    val gray = "\u001b[90m"
    val reset = "\u001b[0m"

    val maxNameLength = aliases.list().maxBy { it.name.length }.name.length

    aliases.list().forEach {
        val dots = maxNameLength - it.name.length + 3
        print(brightRed + it.name + gray)
        (1..dots).forEach { _ -> print(".") }
        println(cyan + it.filepath.replace(homeDir, "~") + reset)
    }
}