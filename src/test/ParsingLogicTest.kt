package test

import operationsLogic.Account
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import parsingLogic.InputParser
import userInteraction.Logger
import kotlin.test.assertEquals

/**
 * Tests the logic of input parser.
 */
open class ParsingLogicTest {

    @Nested
    inner class `Incorrect input tests` {
        private var expected = listOf("This operation doesn't exist. Please, check the correctness of the input.")

        @Nested
        inner class `Add currency tests` {
            @Test
            fun `add currency test 1`() {
                val logger = Logger(Logger.LoggerMod.DEBUG)
                val account = Account(logger)
                val inputParser = InputParser(logger, account)

                inputParser.parse("add currency")

                val actual = logger.getLogInfo()

                assertEquals(expected, actual)
            }

            @Test
            fun `add currency test 2`() {
                val logger = Logger(Logger.LoggerMod.DEBUG)
                val account = Account(logger)
                val inputParser = InputParser(logger, account)

                inputParser.parse("add currency strong ruble")

                val actual = logger.getLogInfo()

                assertEquals(expected, actual)
            }

            @Test
            fun `add currency test 3`() {
                val logger = Logger(Logger.LoggerMod.DEBUG)
                val account = Account(logger)
                val inputParser = InputParser(logger, account)

                inputParser.parse(" add currency")

                val actual = logger.getLogInfo()

                assertEquals(expected, actual)
            }

            @Test
            fun `add currency test 4`() {
                val logger = Logger(Logger.LoggerMod.DEBUG)
                val account = Account(logger)
                val inputParser = InputParser(logger, account)

                inputParser.parse("add crrency dollar")

                val actual = logger.getLogInfo()

                assertEquals(expected, actual)
            }
        }

        @Nested
        inner class `Deposit tests` {
            @Test
            fun `deposit test 1`() {
                val logger = Logger(Logger.LoggerMod.DEBUG)
                val account = Account(logger)
                val inputParser = InputParser(logger, account)

                inputParser.parse("deposit  ")

                val actual = logger.getLogInfo()

                assertEquals(expected, actual)
            }

            @Test
            fun `deposit test 2`() {
                val logger = Logger(Logger.LoggerMod.DEBUG)
                val account = Account(logger)
                val inputParser = InputParser(logger, account)

                inputParser.parse("deposit 14a")

                expected = listOf("Input is incorrect: amount must be a real number.")
                val actual = logger.getLogInfo()

                assertEquals(expected, actual)
            }

            @Test
            fun `deposit test 3`() {
                val logger = Logger(Logger.LoggerMod.DEBUG)
                val account = Account(logger)
                val inputParser = InputParser(logger, account)

                inputParser.parse("deposit 14 strong ruble")

                val actual = logger.getLogInfo()

                assertEquals(expected, actual)
            }

            @Test
            fun `deposit test 4`() {
                val logger = Logger(Logger.LoggerMod.DEBUG)
                val account = Account(logger)
                val inputParser = InputParser(logger, account)

                inputParser.parse(" deposit 14 ruble")

                val actual = logger.getLogInfo()

                assertEquals(expected, actual)
            }
        }

        @Nested
        inner class `Withdraw tests` {
            @Test
            fun `withdraw test 1`() {
                val logger = Logger(Logger.LoggerMod.DEBUG)
                val account = Account(logger)
                val inputParser = InputParser(logger, account)

                inputParser.parse("withdraw")

                val actual = logger.getLogInfo()

                assertEquals(expected, actual)
            }

            @Test
            fun `withdraw test 2`() {
                val logger = Logger(Logger.LoggerMod.DEBUG)
                val account = Account(logger)
                val inputParser = InputParser(logger, account)

                inputParser.parse(" withdraw 15")

                val actual = logger.getLogInfo()

                assertEquals(expected, actual)
            }

            @Test
            fun `withdraw test 3`() {
                val logger = Logger(Logger.LoggerMod.DEBUG)
                val account = Account(logger)
                val inputParser = InputParser(logger, account)

                inputParser.parse("withdraw 15 ")

                val actual = logger.getLogInfo()

                assertEquals(expected, actual)
            }

            @Test
            fun `withdraw test 4`() {
                val logger = Logger(Logger.LoggerMod.DEBUG)
                val account = Account(logger)
                val inputParser = InputParser(logger, account)

                inputParser.parse("withdraw 15.0.12")

                expected = listOf("Input is incorrect: amount must be a real number.")
                val actual = logger.getLogInfo()

                assertEquals(expected, actual)
            }

            @Test
            fun `withdraw test 5`() {
                val logger = Logger(Logger.LoggerMod.DEBUG)
                val account = Account(logger)
                val inputParser = InputParser(logger, account)

                inputParser.parse("withdraw 38 red parrots")

                val actual = logger.getLogInfo()

                assertEquals(expected, actual)
            }
        }

        @Nested
        inner class `Set rate tests` {
            @Test
            fun `set rate test 1`() {
                val logger = Logger(Logger.LoggerMod.DEBUG)
                val account = Account(logger)
                val inputParser = InputParser(logger, account)

                inputParser.parse("set rate")

                val actual = logger.getLogInfo()

                assertEquals(expected, actual)
            }

            @Test
            fun `set rate test 2`() {
                val logger = Logger(Logger.LoggerMod.DEBUG)
                val account = Account(logger)
                val inputParser = InputParser(logger, account)

                inputParser.parse("set rate dollar USA dollar 3:2")

                val actual = logger.getLogInfo()

                assertEquals(expected, actual)
            }

            @Test
            fun `set rate test 3`() {
                val logger = Logger(Logger.LoggerMod.DEBUG)
                val account = Account(logger)
                val inputParser = InputParser(logger, account)

                inputParser.parse("set rate dollar ruble 1 2")

                val actual = logger.getLogInfo()

                assertEquals(expected, actual)
            }

            @Test
            fun `set rate test 4`() {
                val logger = Logger(Logger.LoggerMod.DEBUG)
                val account = Account(logger)
                val inputParser = InputParser(logger, account)

                inputParser.parse("set rate  dollar ruble 1:60.6")

                val actual = logger.getLogInfo()

                assertEquals(expected, actual)
            }
        }

        @Nested
        inner class `Convert tests` {
            @Test
            fun `convert test 1`() {
                val logger = Logger(Logger.LoggerMod.DEBUG)
                val account = Account(logger)
                val inputParser = InputParser(logger, account)

                inputParser.parse("convert dollar ruble")

                val actual = logger.getLogInfo()

                assertEquals(expected, actual)
            }

            @Test
            fun `convert test 2`() {
                val logger = Logger(Logger.LoggerMod.DEBUG)
                val account = Account(logger)
                val inputParser = InputParser(logger, account)

                inputParser.parse("convert dollar ruble 3.2")

                val actual = logger.getLogInfo()

                assertEquals(expected, actual)
            }

            @Test
            fun `convert test 3`() {
                val logger = Logger(Logger.LoggerMod.DEBUG)
                val account = Account(logger)
                val inputParser = InputParser(logger, account)

                inputParser.parse("convert 1k dollar ruble")

                val actual = logger.getLogInfo()

                assertEquals(expected, actual)
            }

            @Test
            fun `convert test 4`() {
                val logger = Logger(Logger.LoggerMod.DEBUG)
                val account = Account(logger)
                val inputParser = InputParser(logger, account)

                inputParser.parse("convert -20.5 dollar")

                val actual = logger.getLogInfo()

                assertEquals(expected, actual)
            }
        }

        @Nested
        inner class `Show balance tests` {
            @Test
            fun `show balance test 1`() {
                val logger = Logger(Logger.LoggerMod.DEBUG)
                val account = Account(logger)
                val inputParser = InputParser(logger, account)

                inputParser.parse("show balance ")

                val actual = logger.getLogInfo()

                assertEquals(expected, actual)
            }

            @Test
            fun `show balance test 2`() {
                val logger = Logger(Logger.LoggerMod.DEBUG)
                val account = Account(logger)
                val inputParser = InputParser(logger, account)

                inputParser.parse("show balance dollar")

                val actual = logger.getLogInfo()

                assertEquals(expected, actual)
            }

            @Test
            fun `show balance test 3`() {
                val logger = Logger(Logger.LoggerMod.DEBUG)
                val account = Account(logger)
                val inputParser = InputParser(logger, account)

                inputParser.parse("snow balance")

                val actual = logger.getLogInfo()

                assertEquals(expected, actual)
            }
        }

        @Nested
        inner class `Show total tests` {
            @Test
            fun `show total test 1`() {
                val logger = Logger(Logger.LoggerMod.DEBUG)
                val account = Account(logger)
                val inputParser = InputParser(logger, account)

                inputParser.parse("show total game score")

                val actual = logger.getLogInfo()

                assertEquals(expected, actual)
            }

            @Test
            fun `show total test 2`() {
                val logger = Logger(Logger.LoggerMod.DEBUG)
                val account = Account(logger)
                val inputParser = InputParser(logger, account)

                inputParser.parse("show total  dollar")

                val actual = logger.getLogInfo()

                assertEquals(expected, actual)
            }

            @Test
            fun `show total test 3`() {
                val logger = Logger(Logger.LoggerMod.DEBUG)
                val account = Account(logger)
                val inputParser = InputParser(logger, account)

                inputParser.parse(" show total")

                val actual = logger.getLogInfo()

                assertEquals(expected, actual)
            }

            @Test
            fun `show total test 4`() {
                val logger = Logger(Logger.LoggerMod.DEBUG)
                val account = Account(logger)
                val inputParser = InputParser(logger, account)

                inputParser.parse(" show totalitarianism")

                val actual = logger.getLogInfo()

                assertEquals(expected, actual)
            }
        }
    }

    @Nested
    inner class `Correct input tests` {

        @Nested
        inner class `Add currency tests` {
            @Test
            fun `add currency test 1`() {
                val logger = Logger(Logger.LoggerMod.DEBUG)
                val account = Account(logger)
                val inputParser = InputParser(logger, account)

                inputParser.parse("add currency dollar")
                account.showBalance()

                val expected = listOf("0 dollar")
                val actual = logger.getLogInfo()

                assertEquals(expected, actual)
            }

            @Test
            fun `add currency test 2`() {
                val logger = Logger(Logger.LoggerMod.DEBUG)
                val account = Account(logger)
                val inputParser = InputParser(logger, account)

                inputParser.parse("add currency three_hundred_bucks")
                account.showBalance()

                val expected = listOf("0 three_hundred_bucks")
                val actual = logger.getLogInfo()

                assertEquals(expected, actual)
            }

            @Test
            fun `add currency test 3`() {
                val logger = Logger(Logger.LoggerMod.DEBUG)
                val account = Account(logger)
                val inputParser = InputParser(logger, account)

                inputParser.parse("add currency 1lf4\"0!df*g)")
                account.showBalance()

                val expected = listOf("0 1lf4\"0!df*g)")
                val actual = logger.getLogInfo()

                assertEquals(expected, actual)
            }
        }

        @Nested
        inner class `Deposit tests` {
            @Test
            fun `deposit test 1`() {
                val logger = Logger(Logger.LoggerMod.DEBUG)
                val account = Account(logger)
                val inputParser = InputParser(logger, account)

                account.addCurrency("dollar")
                inputParser.parse("deposit 12")
                account.showBalance()

                val expected = listOf("12 dollar")
                val actual = logger.getLogInfo()

                assertEquals(expected, actual)
            }

            @Test
            fun `deposit test 2`() {
                val logger = Logger(Logger.LoggerMod.DEBUG)
                val account = Account(logger)
                val inputParser = InputParser(logger, account)

                account.addCurrency("dollar")
                inputParser.parse("deposit 12.")
                account.showBalance()

                val expected = listOf("12 dollar")
                val actual = logger.getLogInfo()

                assertEquals(expected, actual)
            }

            @Test
            fun `deposit test 3`() {
                val logger = Logger(Logger.LoggerMod.DEBUG)
                val account = Account(logger)
                val inputParser = InputParser(logger, account)

                account.addCurrency("dollar")
                inputParser.parse("deposit 12.00234")
                account.showBalance()

                val expected = listOf("12 dollar")
                val actual = logger.getLogInfo()

                assertEquals(expected, actual)
            }

            @Test
            fun `deposit test 4`() {
                val logger = Logger(Logger.LoggerMod.DEBUG)
                val account = Account(logger)
                val inputParser = InputParser(logger, account)

                account.addCurrency("dollar")
                inputParser.parse("deposit 0.481")
                account.showBalance()

                val expected = listOf("0.48 dollar")
                val actual = logger.getLogInfo()

                assertEquals(expected, actual)
            }

            @Test
            fun `deposit test 5`() {
                val logger = Logger(Logger.LoggerMod.DEBUG)
                val account = Account(logger)
                val inputParser = InputParser(logger, account)

                account.addCurrency("dollar")
                inputParser.parse("deposit -2.2")

                val expected = listOf("Amount must be > 0.")
                val actual = logger.getLogInfo()

                assertEquals(expected, actual)
            }

            @Test
            fun `deposit test 6`() {
                val logger = Logger(Logger.LoggerMod.DEBUG)
                val account = Account(logger)
                val inputParser = InputParser(logger, account)

                account.addCurrency("dollar")
                inputParser.parse("deposit 0")

                val expected = listOf("Amount must be > 0.")
                val actual = logger.getLogInfo()

                assertEquals(expected, actual)
            }

            @Test
            fun `deposit test 7`() {
                val logger = Logger(Logger.LoggerMod.DEBUG)
                val account = Account(logger)
                val inputParser = InputParser(logger, account)

                account.addCurrency("dollar")
                inputParser.parse("deposit 2.4 dollar")
                account.showBalance()

                val expected = listOf("2.4 dollar")
                val actual = logger.getLogInfo()

                assertEquals(expected, actual)
            }
        }

        @Nested
        inner class `Withdraw tests` {
            @Test
            fun `withdraw test 1`() {
                val logger = Logger(Logger.LoggerMod.DEBUG)
                val account = Account(logger)
                val inputParser = InputParser(logger, account)

                account.addCurrency("dollar")
                account.deposit(20.0)
                inputParser.parse("withdraw 12")
                account.showBalance()

                val expected = listOf("8 dollar")
                val actual = logger.getLogInfo()

                assertEquals(expected, actual)
            }

            @Test
            fun `withdraw test 2`() {
                val logger = Logger(Logger.LoggerMod.DEBUG)
                val account = Account(logger)
                val inputParser = InputParser(logger, account)

                account.addCurrency("dollar")
                account.deposit(20.0)
                inputParser.parse("withdraw 12.")
                account.showBalance()

                val expected = listOf("8 dollar")
                val actual = logger.getLogInfo()

                assertEquals(expected, actual)
            }

            @Test
            fun `withdraw test 3`() {
                val logger = Logger(Logger.LoggerMod.DEBUG)
                val account = Account(logger)
                val inputParser = InputParser(logger, account)

                account.addCurrency("dollar")
                account.deposit(20.0)
                inputParser.parse("withdraw 12.00234")
                account.showBalance()

                val expected = listOf("7.99 dollar")
                val actual = logger.getLogInfo()

                assertEquals(expected, actual)
            }

            @Test
            fun `withdraw test 4`() {
                val logger = Logger(Logger.LoggerMod.DEBUG)
                val account = Account(logger)
                val inputParser = InputParser(logger, account)

                account.addCurrency("dollar")
                account.deposit(20.0)
                inputParser.parse("withdraw 0.481")
                account.showBalance()

                val expected = listOf("19.51 dollar")
                val actual = logger.getLogInfo()

                assertEquals(expected, actual)
            }

            @Test
            fun `withdraw test 5`() {
                val logger = Logger(Logger.LoggerMod.DEBUG)
                val account = Account(logger)
                val inputParser = InputParser(logger, account)

                account.addCurrency("dollar")
                account.deposit(20.0)
                inputParser.parse("withdraw -2.2")

                val expected = listOf("Amount must be > 0.")
                val actual = logger.getLogInfo()

                assertEquals(expected, actual)
            }

            @Test
            fun `withdraw test 6`() {
                val logger = Logger(Logger.LoggerMod.DEBUG)
                val account = Account(logger)
                val inputParser = InputParser(logger, account)

                account.addCurrency("dollar")
                account.deposit(20.0)
                inputParser.parse("withdraw 0")

                val expected = listOf("Amount must be > 0.")
                val actual = logger.getLogInfo()

                assertEquals(expected, actual)
            }

            @Test
            fun `withdraw test 7`() {
                val logger = Logger(Logger.LoggerMod.DEBUG)
                val account = Account(logger)
                val inputParser = InputParser(logger, account)

                account.addCurrency("dollar")
                account.deposit(20.0)
                inputParser.parse("withdraw 2.4 dollar")
                account.showBalance()

                val expected = listOf("17.6 dollar")
                val actual = logger.getLogInfo()

                assertEquals(expected, actual)
            }
        }

        @Nested
        inner class `Set rate tests` {
            @Test
            fun `set rate test 1`() {
                val logger = Logger(Logger.LoggerMod.DEBUG)
                val account = Account(logger)
                val inputParser = InputParser(logger, account)

                account.addCurrency("dollar")
                account.addCurrency("ruble")
                inputParser.parse("set rate dollar ruble 1:60")
                account.deposit(1.0, "dollar")
                account.showTotal("ruble")

                val expected = listOf("60 ruble")
                val actual = logger.getLogInfo()

                assertEquals(expected, actual)
            }

            @Test
            fun `set rate test 2`() {
                val logger = Logger(Logger.LoggerMod.DEBUG)
                val account = Account(logger)
                val inputParser = InputParser(logger, account)

                account.addCurrency("dollar")
                account.addCurrency("ruble")
                inputParser.parse("set rate dollar ruble 1:60.5")
                account.deposit(1.0, "dollar")
                account.showTotal("ruble")

                val expected = listOf("60.5 ruble")
                val actual = logger.getLogInfo()

                assertEquals(expected, actual)
            }

            @Test
            fun `set rate test 3`() {
                val logger = Logger(Logger.LoggerMod.DEBUG)
                val account = Account(logger)
                val inputParser = InputParser(logger, account)

                account.addCurrency("dollar")
                account.addCurrency("ruble")
                inputParser.parse("set rate dollar ruble 1.5:60")
                account.deposit(1.5, "dollar")
                account.showTotal("ruble")

                val expected = listOf("60 ruble")
                val actual = logger.getLogInfo()

                assertEquals(expected, actual)
            }

            @Test
            fun `set rate test 4`() {
                val logger = Logger(Logger.LoggerMod.DEBUG)
                val account = Account(logger)
                val inputParser = InputParser(logger, account)

                account.addCurrency("dollar")
                account.addCurrency("ruble")
                inputParser.parse("set rate dollar ruble 1.5:60.92")
                account.deposit(60.92, "ruble")
                account.showTotal("dollar")

                val expected = listOf("1.5 dollar")
                val actual = logger.getLogInfo()

                assertEquals(expected, actual)
            }
        }

        @Nested
        inner class `Convert tests` {
            @Test
            fun `convert test 1`() {
                val logger = Logger(Logger.LoggerMod.DEBUG)
                val account = Account(logger)
                val inputParser = InputParser(logger, account)

                account.addCurrency("dollar")
                account.addCurrency("ruble")
                account.setRate("dollar", "ruble", 1.0, 60.0)
                account.deposit(10.0, "dollar")
                account.deposit(120.0, "ruble")
                inputParser.parse("convert 10 dollar to ruble")
                account.showBalance()

                val expected = listOf("720 ruble", "0 dollar")
                val actual = logger.getLogInfo()

                assertEquals(expected, actual)
            }

            @Test
            fun `convert test 2`() {
                val logger = Logger(Logger.LoggerMod.DEBUG)
                val account = Account(logger)
                val inputParser = InputParser(logger, account)

                account.addCurrency("dollar")
                account.addCurrency("ruble")
                account.setRate("dollar", "ruble", 1.0, 60.0)
                account.deposit(10.0, "dollar")
                account.deposit(120.0, "ruble")
                inputParser.parse("convert 5.5 dollar to ruble")
                account.showBalance()

                val expected = listOf("450 ruble", "4.5 dollar")
                val actual = logger.getLogInfo()

                assertEquals(expected, actual)
            }

            @Test
            fun `convert test 3`() {
                val logger = Logger(Logger.LoggerMod.DEBUG)
                val account = Account(logger)
                val inputParser = InputParser(logger, account)

                account.addCurrency("dollar")
                account.addCurrency("ruble")
                account.setRate("dollar", "ruble", 1.0, 60.0)
                account.deposit(10.0, "dollar")
                account.deposit(120.0, "ruble")
                inputParser.parse("convert 120 ruble to dollar")
                account.showBalance()

                val expected = listOf("0 ruble", "12 dollar")
                val actual = logger.getLogInfo()

                assertEquals(expected, actual)
            }

            @Test
            fun `convert test 4`() {
                val logger = Logger(Logger.LoggerMod.DEBUG)
                val account = Account(logger)
                val inputParser = InputParser(logger, account)

                account.addCurrency("dollar")
                account.addCurrency("ruble")
                account.setRate("dollar", "ruble", 1.0, 60.0)
                account.deposit(10.0, "dollar")
                account.deposit(120.0, "ruble")
                inputParser.parse("convert 0.0001 ruble to dollar")
                account.showBalance()

                val expected = listOf("119.99 ruble", "10 dollar")
                val actual = logger.getLogInfo()

                assertEquals(expected, actual)
            }
        }

        @Nested
        inner class `Show balance tests` {
            @Test
            fun `show balance test 1`() {
                val logger = Logger(Logger.LoggerMod.DEBUG)
                val account = Account(logger)
                val inputParser = InputParser(logger, account)

                account.addCurrency("dollar")
                inputParser.parse("show balance")

                val expected = listOf("0 dollar")
                val actual = logger.getLogInfo()

                assertEquals(expected, actual)
            }
        }

        @Nested
        inner class `Show total tests` {
            @Test
            fun `show total test 1`() {
                val logger = Logger(Logger.LoggerMod.DEBUG)
                val account = Account(logger)
                val inputParser = InputParser(logger, account)

                account.addCurrency("dollar")
                inputParser.parse("show total")

                val expected = listOf("0 dollar")
                val actual = logger.getLogInfo()

                assertEquals(expected, actual)
            }

            @Test
            fun `show total test 2`() {
                val logger = Logger(Logger.LoggerMod.DEBUG)
                val account = Account(logger)
                val inputParser = InputParser(logger, account)

                account.addCurrency("dollar")
                inputParser.parse("show total in dollar")

                val expected = listOf("0 dollar")
                val actual = logger.getLogInfo()

                assertEquals(expected, actual)
            }
        }
    }
}