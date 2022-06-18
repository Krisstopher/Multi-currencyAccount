package test

import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import userInteraction.Logger
import kotlin.test.assertEquals

/**
 * Tests [Logger].
 */
open class UserInteractionTest {

    @Nested
    inner class `Logger tests` {
        @Test
        fun `logger log test 1`() {
            val logger = Logger(Logger.LoggerMod.DEBUG)

            logger.log("message")

            val expected = listOf("message")
            val actual = logger.getLogInfo()

            assertEquals(expected, actual)
        }

        @Test
        fun `logger log test 2`() {
            val logger = Logger(Logger.LoggerMod.DEBUG)

            logger.log(15.0)

            val expected = listOf("15")
            val actual = logger.getLogInfo()

            assertEquals(expected, actual)
        }

        @Test
        fun `logger log test 3`() {
            val logger = Logger(Logger.LoggerMod.DEBUG)

            logger.log(14.999999)

            val expected = listOf("14.99")
            val actual = logger.getLogInfo()

            assertEquals(expected, actual)
        }

        @Test
        fun `logger getMod test 1`() {
            val logger = Logger(Logger.LoggerMod.CONSOLE)

            val expected = Logger.LoggerMod.CONSOLE
            val actual = logger.getMod()

            assertEquals(expected, actual)
        }

        @Test
        fun `logger getMod test 2`() {
            val logger = Logger(Logger.LoggerMod.DEBUG)

            val expected = Logger.LoggerMod.DEBUG
            val actual = logger.getMod()

            assertEquals(expected, actual)
        }

        @Test
        fun `logger changeMod test`() {
            val logger = Logger(Logger.LoggerMod.CONSOLE)

            logger.changeMod(Logger.LoggerMod.DEBUG)

            val expected = Logger.LoggerMod.DEBUG
            val actual = logger.getMod()

            assertEquals(expected, actual)
        }
    }

}