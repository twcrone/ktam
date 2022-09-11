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
        aliases.writeTo("$workingDir/aliases.txt")
    }

    cli.printAliases(homeDir, aliases)
}

/*
COLUMN defines the width of the terminal screen.
HOME defines the pathname of the user’s home directory.
LOGNAME defines the user’s login name.
LINES defines the user’s preferred lines on the terminal screen.
PATH defines binary colon-separated paths for executables.
PWD defines the current working directory.
SHELL defines the current shell in use.
TERM defines the terminal type.
 */