package sh.vcm.sensiblelogging.filter

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import sh.vcm.sensiblelogging.Category
import sh.vcm.sensiblelogging.Level
import sh.vcm.sensiblelogging.Line

class SimpleCategoryFilterTest {
    private val firstCategory = Category("First")
    private val secondCategory = Category("Second")

    @Test
    internal fun `does not pass non-included category`() {
        // GIVEN
        val filter = Filter.categories(listOf(firstCategory))

        // WHEN
        val line = Line(
            System.currentTimeMillis(),
            secondCategory,
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
        Assertions.assertFalse(result)
    }

    @Test
    internal fun `pass included category`() {
        // GIVEN
        val filter = Filter.categories(listOf(firstCategory, secondCategory))

        // WHEN
        val line = Line(
            System.currentTimeMillis(),
            secondCategory,
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
        Assertions.assertTrue(result)
    }
}
