package userInteraction

import parsingLogic.InputParser
import operationsLogic.Account
import java.lang.RuntimeException

/**
 * Implementation of a multi-currency account console application.
 *
 * @property logger - [Logger].
 * @property account - [operationsLogic.Account]
 * @property inputParser - [parsingLogic.InputParser]
 * @constructor - initializes the application.
 */
class ConsoleApplication : Runnable {
    private var logger = Logger(Logger.LoggerMod.CONSOLE)
    private val account = Account(logger)
    private val inputParser = InputParser(logger, account)

    override fun run() {
        println("Hello!")
        println("Your account has been created.")
        println("Available operations can be found with the command \"help\"")

        while (true) {
            var command : String
            try {
                command = readln()
            } catch (e: RuntimeException) {
                break
            }

            if (command == "stop") break
            inputParser.parse(command)
        }

        println("Your account has been deleted, the application is turned off.")
        println("Goodbye!")
    }
}