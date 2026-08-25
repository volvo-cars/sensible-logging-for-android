package sh.vcm.sensiblelogging.filter

import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Test
import sh.vcm.sensiblelogging.Category
import sh.vcm.sensiblelogging.Level
import sh.vcm.sensiblelogging.Line

internal class SimpleLogLevelFilterTest {

    private val category = Category("UnitTest")

    @Test
    internal fun `error filter does not pass debug`() {
        // GIVEN
        val filter = Filter.level(Level.ERROR)

        // WHEN
        val line = Line(
            System.currentTimeMillis(),
            category,
            Level.DEBUG,
            "Something happened",
            false,
            null,
            emptyMap()
        )

        val result = filter.matches(
            line
        )
        // THEN
        assertFalse(result)
    }
}
