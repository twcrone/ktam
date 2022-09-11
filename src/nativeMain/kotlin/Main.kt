import kotlinx.cinterop.toKString
import platform.posix.getenv

fun main(args: Array<String>) {
    val homeDir = getenv("HOME")?.toKString() ?: "~"
    val cli = AliasCli()
    val aliases = Aliases.from("$homeDir/.alias")

    if(args.isNotEmpty()) {
        val name = args.first()
        val workingDir = getenv("PWD")?.toKString() ?: "."
        aliases.add(name, "'$workingDir'")
    }

    cli.printAliases(homeDir, aliases)
}