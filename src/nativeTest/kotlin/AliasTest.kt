import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

class AliasTest {

    @Test
    fun parseAlias() {
        val line = "alias name=/path/to/file.txt"
        val alias = aliasFrom(line)

        assertNotNull(alias)
        assertEquals("name", alias.name)
        assertEquals("/path/to/file.txt", alias.filepath)
    }
}