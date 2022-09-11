import kotlinx.cinterop.toKString
import platform.posix.getenv

fun main(args: Array<String>) {
    val homeDir = getenv("HOME")?.toKString() ?: "~"
    val cli = AliasCli()
    val aliases = Aliases.from("$homeDir/.alias")
    cli.printAliases(homeDir, aliases)
}