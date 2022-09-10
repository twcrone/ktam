fun main() {
    println("Please enter your name...")
    val name = readln()
    name.replace(" ", "").let {
        println("Your name contains ${it.length} characters")
    }
}