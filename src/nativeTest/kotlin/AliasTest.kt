import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

class AliasTest {

    @Test
    fun parseAlias() {
        val line = "alias name=/path/to/file.txt\n"
        val alias = aliasFrom(line)

        assertNotNull(alias)
        assertEquals("name", alias.name)
        assertEquals("/path/to/file.txt", alias.filepath)
    }

    @Test
    fun parseAlias_emptyLine() {
        val alias = aliasFrom("")
        assertEquals(NoAlias, alias)
    }

    @Test
    fun toStringTest() {
        val s = Alias("name", "filepath")
        assertEquals("alias name=filepath", s.toString())
    }
}