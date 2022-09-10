import kotlin.test.Test
import kotlin.test.assertEquals

class AliasesTest {

    @Test
    fun addAlias() {
        val aliases = Aliases()
        aliases.add("alias name=/path/to/file.txt")
        assertEquals(1, aliases.size())

        val alias = aliases.list().first()
        assertEquals("name", alias.name)
        assertEquals("/path/to/file.txt", alias.filepath)
    }

    @Test
    fun removeAliasByName() {
        val aliases = Aliases()
        aliases.add("alias name=/path/to/file.txt")
        aliases.add("alias key=something/else")
        assertEquals(2, aliases.size())
        aliases.remove("name")
        assertEquals(1, aliases.size())
    }
}