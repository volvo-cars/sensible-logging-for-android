package sh.vcm.sensiblelogging.processor

import io.mockk.*
import io.mockk.impl.annotations.MockK
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import sh.vcm.sensiblelogging.Category
import sh.vcm.sensiblelogging.Level
import sh.vcm.sensiblelogging.Line
import sh.vcm.sensiblelogging.Meta
import sh.vcm.sensiblelogging.channel.DebugChannel
import sh.vcm.sensiblelogging.channel.ReleaseChannel
import sh.vcm.sensiblelogging.filter.Filter
import sh.vcm.sensiblelogging.util.Constants
import sh.vcm.sensiblelogging.util.MetaDataFactory

internal class LogProcessorTest {
    @MockK
    lateinit var debugChannel: DebugChannel
    @MockK
    lateinit var releaseChannel: ReleaseChannel
    @MockK
    lateinit var permissiveFilter: Filter
    @MockK
    lateinit var strictFilter: Filter

    private val category = Category("UnitTest")

    private lateinit var underTest: LogProcessor

    @BeforeEach
    internal fun setUp() {
        MockKAnnotations.init(this)
        underTest = LogProcessor()
        every { releaseChannel.id } returns 1
        every { debugChannel.id } returns 2

        every { releaseChannel.default } returns false
        every { debugChannel.default } returns true

        every { releaseChannel.printFiltered(any()) } just runs
        every { debugChannel.printFiltered(any(), any()) } just runs

        every { permissiveFilter.matches(any()) } returns true
        every { strictFilter.matches(any()) } returns false

        mockkObject(MetaDataFactory)
    }

    @Test
    internal fun `should not create debug metadata when theres no DebugChannel`() {
        // GIVEN
        every { releaseChannel.default } returns true
        underTest.addChannels(listOf(releaseChannel))

        // WHEN
        underTest.log(
            Level.DEBUG,
            "Something happened",
            false,
            category,
            Constants.DEFAULT_CHANNEL_LIST,
            null,
            emptyMap(),
            Constants.DEFAULT_STACK_DEPTH
        )

        // THEN
        verify(exactly = 0) { MetaDataFactory.create(any()) }
        verify { releaseChannel.printFiltered(any()) }
    }

    @Test
    internal fun `should not create debug metadata when it is not in any filter`() {
        // GIVEN
        underTest.addChannels(listOf(releaseChannel, debugChannel))
        every { releaseChannel.filter } returns permissiveFilter
        every { debugChannel.filter } returns strictFilter

        // WHEN
        underTest.log(
            Level.DEBUG,
            "Something happened",
            false,
            category,
            Constants.DEFAULT_CHANNEL_LIST,
            null,
            emptyMap(),
            Constants.DEFAULT_STACK_DEPTH
        )

        // THEN
        verify(exactly = 0) { MetaDataFactory.create(any()) }
    }

    @Test
    internal fun `should create debug metadata exactly once per line`() {
        // GIVEN
        underTest.addChannels(listOf(debugChannel, debugChannel))
        every { releaseChannel.filter } returns permissiveFilter
        every { debugChannel.filter } returns permissiveFilter

        // WHEN
        underTest.log(
            Level.DEBUG,
            "Something happened",
            false,
            category,
            Constants.DEFAULT_CHANNEL_LIST,
            null,
            emptyMap(),
            Constants.DEFAULT_STACK_DEPTH
        )

        // THEN
        verify(exactly = 1) { MetaDataFactory.create(any()) }
        verify(exactly = 2) { debugChannel.printFiltered(any(), any()) }
    }


    @Test
    internal fun `debug happy path`() {
        // GIVEN
        underTest.addChannels(listOf(debugChannel))
        every { debugChannel.filter } returns permissiveFilter
        val lineSlot = slot<Line>()
        val metaSlot = slot<Meta>()
        every { MetaDataFactory.create(any()) } returns Meta(
            "com.package.SomeClass",
            "SomeClass",
            "function",
            25,
            "Main",
            "File.kt"
        )
        justRun { debugChannel.printFiltered(capture(lineSlot), capture(metaSlot)) }

        // WHEN
        underTest.log(
            Level.DEBUG,
            "Something happened",
            false,
            category,
            Constants.DEFAULT_CHANNEL_LIST,
            null,
            emptyMap(),
            Constants.DEFAULT_STACK_DEPTH
        )

        // THEN
        val line = lineSlot.captured
        val meta = metaSlot.captured
        assertEquals(line.level, Level.DEBUG)
        assertEquals(line.message, "Something happened")
        assertEquals(meta.simpleClassName, "SomeClass")
    }

    @Test
    internal fun `should pass to a specific channel when its id is used`() {
        // GIVEN
        underTest.addChannels(listOf(releaseChannel))
        every { releaseChannel.filter } returns permissiveFilter

        // WHEN
        underTest.log(
            Level.DEBUG,
            "Something happened",
            false,
            category,
            listOf(1),
            null,
            emptyMap(),
            Constants.DEFAULT_STACK_DEPTH
        )

        // THEN
        verify(exactly = 1) { releaseChannel.printFiltered(any()) }
    }
}
