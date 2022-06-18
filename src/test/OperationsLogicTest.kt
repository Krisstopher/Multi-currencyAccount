package test

import operationsLogic.Account
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import userInteraction.Logger
import kotlin.test.assertEquals

/**
 * Tests the inner logic of operations.
 */
open class OperationsLogicTest {

    @Nested
    inner class `addCurrency tests` {
        @Test
        fun `addCurrency test 1`() {
            val logger = Logger(Logger.LoggerMod.DEBUG)
            val account = Account(logger)

            account.addCurrency("dollar")
            account.showBalance()

            val expected = listOf("0 dollar")
            val actual = logger.getLogInfo()

            assertEquals(expected, actual)
        }

        @Test
        fun `addCurrency test 2`() {
            val logger = Logger(Logger.LoggerMod.DEBUG)
            val account = Account(logger)

            account.addCurrency("dollar")
            account.addCurrency("dollar")
            account.showBalance()

            val expected = listOf("Type of currency \"dollar\" already exists in this account.", "0 dollar")
            val actual = logger.getLogInfo()

            assertEquals(expected, actual)
        }

        @Test
        fun `addCurrency test 3`() {
            val logger = Logger(Logger.LoggerMod.DEBUG)
            val account = Account(logger)

            account.addCurrency("dollar")
            account.addCurrency("ruble")
            account.showBalance()

            val expected = listOf("0 ruble", "0 dollar")
            val actual = logger.getLogInfo()

            assertEquals(expected, actual)
        }
    }

    @Nested
    inner class `deposit tests` {
        @Test
        fun `deposit test 1`() {
            val logger = Logger(Logger.LoggerMod.DEBUG)
            val account = Account(logger)

            account.deposit(-3.0)

            val expected = listOf("Amount must be > 0.")
            val actual = logger.getLogInfo()

            assertEquals(expected, actual)
        }

        @Test
        fun `deposit test 2`() {
            val logger = Logger(Logger.LoggerMod.DEBUG)
            val account = Account(logger)

            account.deposit(3.0)

            val expected = listOf("No one currency enabled.")
            val actual = logger.getLogInfo()

            assertEquals(expected, actual)
        }

        @Test
        fun `deposit test 3`() {
            val logger = Logger(Logger.LoggerMod.DEBUG)
            val account = Account(logger)

            account.addCurrency("dollar")
            account.deposit(3.0, "leaf")

            val expected = listOf("Type of currency \"leaf\" doesn't exist.")
            val actual = logger.getLogInfo()

            assertEquals(expected, actual)
        }

        @Test
        fun `deposit test 4`() {
            val logger = Logger(Logger.LoggerMod.DEBUG)
            val account = Account(logger)

            account.addCurrency("dollar")
            account.deposit(-3.0, "leaf")

            val expected = listOf("Amount must be > 0.")
            val actual = logger.getLogInfo()

            assertEquals(expected, actual)
        }

        @Test
        fun `deposit test 5`() {
            val logger = Logger(Logger.LoggerMod.DEBUG)
            val account = Account(logger)

            account.addCurrency("dollar")
            account.deposit(-3.0, "dollar")

            val expected = listOf("Amount must be > 0.")
            val actual = logger.getLogInfo()

            assertEquals(expected, actual)
        }

        @Test
        fun `deposit test 6`() {
            val logger = Logger(Logger.LoggerMod.DEBUG)
            val account = Account(logger)

            account.addCurrency("dollar")
            account.deposit(2.0)
            account.showBalance()

            val expected = listOf("2 dollar")
            val actual = logger.getLogInfo()

            assertEquals(expected, actual)
        }

        @Test
        fun `deposit test 7`() {
            val logger = Logger(Logger.LoggerMod.DEBUG)
            val account = Account(logger)

            account.addCurrency("dollar")
            account.deposit(2.0, "dollar")
            account.showBalance()

            val expected = listOf("2 dollar")
            val actual = logger.getLogInfo()

            assertEquals(expected, actual)
        }
    }

    @Nested
    inner class `withdraw tests` {
        @Test
        fun `withdraw test 1`() {
            val logger = Logger(Logger.LoggerMod.DEBUG)
            val account = Account(logger)

            account.withdraw(-3.0)

            val expected = listOf("Amount must be > 0.")
            val actual = logger.getLogInfo()

            assertEquals(expected, actual)
        }

        @Test
        fun `withdraw test 2`() {
            val logger = Logger(Logger.LoggerMod.DEBUG)
            val account = Account(logger)

            account.withdraw(3.0)

            val expected = listOf("No one currency enabled.")
            val actual = logger.getLogInfo()

            assertEquals(expected, actual)
        }

        @Test
        fun `withdraw test 3`() {
            val logger = Logger(Logger.LoggerMod.DEBUG)
            val account = Account(logger)

            account.addCurrency("dollar")
            account.withdraw(3.0, "leaf")

            val expected = listOf("Type of currency \"leaf\" doesn't exist.")
            val actual = logger.getLogInfo()

            assertEquals(expected, actual)
        }

        @Test
        fun `withdraw test 4`() {
            val logger = Logger(Logger.LoggerMod.DEBUG)
            val account = Account(logger)

            account.addCurrency("dollar")
            account.withdraw(-3.0, "leaf")

            val expected = listOf("Amount must be > 0.")
            val actual = logger.getLogInfo()

            assertEquals(expected, actual)
        }

        @Test
        fun `withdraw test 5`() {
            val logger = Logger(Logger.LoggerMod.DEBUG)
            val account = Account(logger)

            account.addCurrency("dollar")
            account.withdraw(-3.0, "dollar")

            val expected = listOf("Amount must be > 0.")
            val actual = logger.getLogInfo()

            assertEquals(expected, actual)
        }

        @Test
        fun `withdraw test 6`() {
            val logger = Logger(Logger.LoggerMod.DEBUG)
            val account = Account(logger)

            account.addCurrency("dollar")
            account.withdraw(2.0)

            val expected = listOf("There are not enough money in \"dollar\" type of currency for withdrawal.")
            val actual = logger.getLogInfo()

            assertEquals(expected, actual)
        }

        @Test
        fun `withdraw test 7`() {
            val logger = Logger(Logger.LoggerMod.DEBUG)
            val account = Account(logger)

            account.addCurrency("dollar")
            account.deposit(2.0, "dollar")
            account.withdraw(2.000001, "dollar")

            val expected = listOf("There are not enough money in \"dollar\" type of currency for withdrawal.")
            val actual = logger.getLogInfo()

            assertEquals(expected, actual)
        }

        @Test
        fun `withdraw test 8`() {
            val logger = Logger(Logger.LoggerMod.DEBUG)
            val account = Account(logger)

            account.addCurrency("dollar")
            account.deposit(2.0, "dollar")
            account.withdraw(1.5, "dollar")
            account.showBalance()

            val expected = listOf("0.5 dollar")
            val actual = logger.getLogInfo()

            assertEquals(expected, actual)
        }
    }

    @Nested
    inner class `setRate tests` {
        @Test
        fun `setRate test 1`() {
            val logger = Logger(Logger.LoggerMod.DEBUG)
            val account = Account(logger)

            account.setRate("dollar", "leaf", 1.0, 4.0)

            val expected = listOf("Type of currency \"dollar\" doesn't exist.")
            val actual = logger.getLogInfo()

            assertEquals(expected, actual)
        }

        @Test
        fun `setRate test 2`() {
            val logger = Logger(Logger.LoggerMod.DEBUG)
            val account = Account(logger)

            account.addCurrency("dollar")
            account.setRate("dollar", "leaf", 1.0, 4.0)

            val expected = listOf("Type of currency \"leaf\" doesn't exist.")
            val actual = logger.getLogInfo()

            assertEquals(expected, actual)
        }

        @Test
        fun `setRate test 3`() {
            val logger = Logger(Logger.LoggerMod.DEBUG)
            val account = Account(logger)

            account.addCurrency("dollar")
            account.setRate("dollar", "dollar", 1.0, 4.0)

            val expected = listOf("The specified currencies must be different")
            val actual = logger.getLogInfo()

            assertEquals(expected, actual)
        }

        @Test
        fun `setRate test 4`() {
            val logger = Logger(Logger.LoggerMod.DEBUG)
            val account = Account(logger)

            account.addCurrency("dollar")
            account.addCurrency("ruble")
            account.setRate("dollar", "ruble", -1.0, 4.0)

            val expected = listOf("Amount must be > 0.")
            val actual = logger.getLogInfo()

            assertEquals(expected, actual)
        }

        @Test
        fun `setRate test 5`() {
            val logger = Logger(Logger.LoggerMod.DEBUG)
            val account = Account(logger)

            account.addCurrency("dollar")
            account.addCurrency("ruble")
            account.setRate("dollar", "ruble", 1.0, -4.0)

            val expected = listOf("Amount must be > 0.")
            val actual = logger.getLogInfo()

            assertEquals(expected, actual)
        }

        @Test
        fun `setRate test 6`() {
            val logger = Logger(Logger.LoggerMod.DEBUG)
            val account = Account(logger)

            account.addCurrency("dollar")
            account.addCurrency("ruble")
            account.setRate("dollar", "ruble", 1.0, 4.0)
            account.deposit(5.0, "dollar")
            account.convert(5.0, "dollar", "ruble")
            account.showBalance()

            val expected = listOf("20 ruble", "0 dollar")
            val actual = logger.getLogInfo()

            assertEquals(expected, actual)
        }

        @Test
        fun `setRate test 7`() {
            val logger = Logger(Logger.LoggerMod.DEBUG)
            val account = Account(logger)

            account.addCurrency("dollar")
            account.addCurrency("ruble")
            account.setRate("dollar", "ruble", 1.0, 4.0)
            account.deposit(8.0, "ruble")
            account.convert(8.0, "ruble", "dollar")
            account.showBalance()

            val expected = listOf("0 ruble", "2 dollar")
            val actual = logger.getLogInfo()

            assertEquals(expected, actual)
        }
    }

    @Nested
    inner class `convert tests` {
        @Test
        fun `convert test 1`() {
            val logger = Logger(Logger.LoggerMod.DEBUG)
            val account = Account(logger)

            account.convert(20.0, "dollar", "leaf")

            val expected = listOf("Type of currency \"dollar\" doesn't exist.")
            val actual = logger.getLogInfo()

            assertEquals(expected, actual)
        }

        @Test
        fun `convert test 2`() {
            val logger = Logger(Logger.LoggerMod.DEBUG)
            val account = Account(logger)

            account.convert(-20.0, "dollar", "leaf")

            val expected = listOf("Amount must be > 0.")
            val actual = logger.getLogInfo()

            assertEquals(expected, actual)
        }

        @Test
        fun `convert test 3`() {
            val logger = Logger(Logger.LoggerMod.DEBUG)
            val account = Account(logger)

            account.addCurrency("dollar")
            account.convert(20.0, "dollar", "leaf")

            val expected = listOf("Type of currency \"leaf\" doesn't exist.")
            val actual = logger.getLogInfo()

            assertEquals(expected, actual)
        }

        @Test
        fun `convert test 4`() {
            val logger = Logger(Logger.LoggerMod.DEBUG)
            val account = Account(logger)

            account.addCurrency("dollar")
            account.addCurrency("leaf")
            account.convert(20.0, "dollar", "leaf")

            val expected = listOf("Not enough money: you have only 0 dollar.")
            val actual = logger.getLogInfo()

            assertEquals(expected, actual)
        }

        @Test
        fun `convert test 5`() {
            val logger = Logger(Logger.LoggerMod.DEBUG)
            val account = Account(logger)

            account.addCurrency("dollar")
            account.addCurrency("leaf")
            account.deposit(30.0)
            account.convert(20.0, "dollar", "leaf")

            val expected = listOf("The ratio of currencies \"dollar\" and \"leaf\" is not set")
            val actual = logger.getLogInfo()

            assertEquals(expected, actual)
        }

        @Test
        fun `convert test 6`() {
            val logger = Logger(Logger.LoggerMod.DEBUG)
            val account = Account(logger)

            account.addCurrency("opportunities")
            account.addCurrency("time")
            account.setRate("time", "opportunities", 20.0, 1.0)
            account.convert(20.0, "time", "opportunities")

            val expected = listOf("Not enough money: you have only 0 time.")
            val actual = logger.getLogInfo()

            assertEquals(expected, actual)
        }

        @Test
        fun `convert test 7`() {
            val logger = Logger(Logger.LoggerMod.DEBUG)
            val account = Account(logger)

            account.addCurrency("dollar")
            account.addCurrency("ruble")
            account.setRate("dollar", "ruble", 20.0, 1.0)
            account.convert(-20.0, "dollar", "ruble")

            val expected = listOf("Amount must be > 0.")
            val actual = logger.getLogInfo()

            assertEquals(expected, actual)
        }

        @Test
        fun `convert test 8`() {
            val logger = Logger(Logger.LoggerMod.DEBUG)
            val account = Account(logger)

            account.addCurrency("dollar")
            account.addCurrency("ruble")
            account.setRate("dollar", "ruble", 20.0, 1.0)
            account.deposit(30.0)
            account.convert(20.0, "dollar", "ruble")
            account.showBalance()

            val expected = listOf("1 ruble", "10 dollar")
            val actual = logger.getLogInfo()

            assertEquals(expected, actual)
        }

        @Test
        fun `convert test 9`() {
            val logger = Logger(Logger.LoggerMod.DEBUG)
            val account = Account(logger)

            account.addCurrency("dollar")
            account.addCurrency("ruble")
            account.setRate("dollar", "ruble", 20.0, 1.0)
            account.deposit(30.0, "ruble")
            account.convert(20.0, "ruble", "dollar")
            account.showBalance()

            val expected = listOf("10 ruble", "400 dollar")
            val actual = logger.getLogInfo()

            assertEquals(expected, actual)
        }

        @Test
        fun `convert test 10`() {
            val logger = Logger(Logger.LoggerMod.DEBUG)
            val account = Account(logger)

            account.addCurrency("dollar")
            account.addCurrency("ruble")
            account.setRate("dollar", "ruble", 20.0, 1.0)
            account.deposit(30.0, "ruble")
            account.deposit(10.0)
            account.convert(20.0, "ruble", "dollar")
            account.showBalance()

            val expected = listOf("10 ruble", "410 dollar")
            val actual = logger.getLogInfo()

            assertEquals(expected, actual)
        }
    }

    @Nested
    inner class `showBalance tests` {
        @Test
        fun `showBalance test 1`() {
            val logger = Logger(Logger.LoggerMod.DEBUG)
            val account = Account(logger)

            account.showBalance()

            val expected = listOf<String>()
            val actual = logger.getLogInfo()

            assertEquals(expected, actual)
        }

        @Test
        fun `showBalance test 2`() {
            val logger = Logger(Logger.LoggerMod.DEBUG)
            val account = Account(logger)

            account.addCurrency("dollar")
            account.showBalance()

            val expected = listOf("0 dollar")
            val actual = logger.getLogInfo()

            assertEquals(expected, actual)
        }

        @Test
        fun `showBalance test 3`() {
            val logger = Logger(Logger.LoggerMod.DEBUG)
            val account = Account(logger)

            account.addCurrency("dollar")
            account.deposit(20.1555)
            account.showBalance()

            val expected = listOf("20.15 dollar")
            val actual = logger.getLogInfo()

            assertEquals(expected, actual)
        }

        @Test
        fun `showBalance test 4`() {
            val logger = Logger(Logger.LoggerMod.DEBUG)
            val account = Account(logger)

            account.addCurrency("dollar")
            account.addCurrency("ruble")
            account.addCurrency("freedom")
            account.showBalance()

            val expected = listOf("0 freedom", "0 ruble", "0 dollar")
            val actual = logger.getLogInfo()

            assertEquals(expected, actual)
        }

        @Test
        fun `showBalance test 5`() {
            val logger = Logger(Logger.LoggerMod.DEBUG)
            val account = Account(logger)

            account.addCurrency("dollar")
            account.addCurrency("ruble")
            account.addCurrency("freedom")
            account.deposit(13571.0, "ruble")
            account.showBalance()

            val expected = listOf("0 freedom", "13571 ruble", "0 dollar")
            val actual = logger.getLogInfo()

            assertEquals(expected, actual)
        }
    }

    @Nested
    inner class `showTotal tests` {
        @Test
        fun `showTotal test 1`() {
            val logger = Logger(Logger.LoggerMod.DEBUG)
            val account = Account(logger)

            account.showTotal()

            val expected = listOf("No one currency enabled.")
            val actual = logger.getLogInfo()

            assertEquals(expected, actual)
        }

        @Test
        fun `showTotal test 2`() {
            val logger = Logger(Logger.LoggerMod.DEBUG)
            val account = Account(logger)

            account.addCurrency("dollar")
            account.showTotal()

            val expected = listOf("0 dollar")
            val actual = logger.getLogInfo()

            assertEquals(expected, actual)
        }


        @Test
        fun `showTotal test 3`() {
            val logger = Logger(Logger.LoggerMod.DEBUG)
            val account = Account(logger)

            account.addCurrency("dollar")
            account.deposit(300.0)
            account.showTotal()

            val expected = listOf("300 dollar")
            val actual = logger.getLogInfo()

            assertEquals(expected, actual)
        }

        @Test
        fun `showTotal test 4`() {
            val logger = Logger(Logger.LoggerMod.DEBUG)
            val account = Account(logger)

            account.addCurrency("dollar")
            account.addCurrency("ruble")
            account.showTotal()

            val expected = listOf("The ratio of currencies \"ruble\" and \"dollar\" is not set")
            val actual = logger.getLogInfo()

            assertEquals(expected, actual)
        }

        @Test
        fun `showTotal test 5`() {
            val logger = Logger(Logger.LoggerMod.DEBUG)
            val account = Account(logger)

            account.addCurrency("dollar")
            account.addCurrency("ruble")
            account.setRate("dollar", "ruble", 1.0, 60.0)
            account.showTotal()

            val expected = listOf("0 dollar")
            val actual = logger.getLogInfo()

            assertEquals(expected, actual)
        }

        @Test
        fun `showTotal test 6`() {
            val logger = Logger(Logger.LoggerMod.DEBUG)
            val account = Account(logger)

            account.addCurrency("dollar")
            account.addCurrency("ruble")
            account.setRate("dollar", "ruble", 1.0, 60.0)
            account.deposit(20.0)
            account.showTotal()

            val expected = listOf("20 dollar")
            val actual = logger.getLogInfo()

            assertEquals(expected, actual)
        }

        @Test
        fun `showTotal test 7`() {
            val logger = Logger(Logger.LoggerMod.DEBUG)
            val account = Account(logger)

            account.addCurrency("dollar")
            account.addCurrency("ruble")
            account.setRate("dollar", "ruble", 1.0, 60.0)
            account.deposit(600.0, "ruble")
            account.showTotal()

            val expected = listOf("10 dollar")
            val actual = logger.getLogInfo()

            assertEquals(expected, actual)
        }

        @Test
        fun `showTotal test 8`() {
            val logger = Logger(Logger.LoggerMod.DEBUG)
            val account = Account(logger)

            account.addCurrency("dollar")
            account.addCurrency("ruble")
            account.setRate("dollar", "ruble", 1.0, 60.0)
            account.deposit(600.0, "ruble")
            account.showTotal("leaf")

            val expected = listOf("Type of currency \"leaf\" doesn't exist.")
            val actual = logger.getLogInfo()

            assertEquals(expected, actual)
        }

        @Test
        fun `showTotal test 9`() {
            val logger = Logger(Logger.LoggerMod.DEBUG)
            val account = Account(logger)

            account.addCurrency("dollar")
            account.addCurrency("ruble")
            account.setRate("dollar", "ruble", 1.0, 60.0)
            account.showTotal("ruble")

            val expected = listOf("0 ruble")
            val actual = logger.getLogInfo()

            assertEquals(expected, actual)
        }

        @Test
        fun `showTotal test 10`() {
            val logger = Logger(Logger.LoggerMod.DEBUG)
            val account = Account(logger)

            account.addCurrency("dollar")
            account.addCurrency("ruble")
            account.setRate("dollar", "ruble", 1.0, 60.0)
            account.deposit(20.0)
            account.showTotal("ruble")

            val expected = listOf("1200 ruble")
            val actual = logger.getLogInfo()

            assertEquals(expected, actual)
        }
    }
}