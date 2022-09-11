import kotlinx.cinterop.toKString
import platform.posix.getenv

fun main(args: Array<String>) {
    val homeDir = getenv("HOME")?.toKString() ?: "~"
    val workingDir = getenv("PWD")?.toKString() ?: "."
    val cli = AliasCli()
    val aliases = Aliases.from("$workingDir/.alias")
    if(args.isNotEmpty()) {
        aliases.writeTo("$workingDir/.alias.bak")
        val name = args.first()
        aliases.add(name, "'$workingDir'")
        aliases.writeTo("$workingDir/.alias")
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