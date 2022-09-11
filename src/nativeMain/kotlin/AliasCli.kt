class AliasCli {

    fun printAddHeading(name: String) {
        println("${brightBlue}Adding ${green}[$name]$reset")
    }

    fun printDeleteHeading(name: String) {
        println("${red}Deleting ${green}[$name]$reset")
    }

    fun printAliases(homeDir: String, aliases: Aliases) {
        // https://en.wikipedia.org/wiki/ANSI_escape_code#Colors
        val maxNameLength = aliases.list().maxBy { it.name.length }.name.length

        aliases.list().forEach {
            val dots = maxNameLength - it.name.length + 3
            print(brightRed + it.name + gray)
            (1..dots).forEach { _ -> print(".") }
            println(cyan + it.filepath.replace(homeDir, "~") + reset)
        }
    }

    companion object {
        const val brightBlue = "\u001b[94m"
        const val blue = "\u001b[34m"
        const val cyan = "\u001b[36m"
        const val green = "\u001b[32m"
        const val brightRed = "\u001b[91m"
        const val red = "\u001b[31m"
        const val gray = "\u001b[90m"
        const val reset = "\u001b[0m"
    }
}