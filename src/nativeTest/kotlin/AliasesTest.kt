import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

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
}