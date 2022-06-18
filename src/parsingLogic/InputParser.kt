package parsingLogic

import userInteraction.Logger
import operationsLogic.Account
import java.lang.IllegalArgumentException

/**
 * Parses input, passing further commands to the internal implementation handler.
 *
 * @param logger - [userInteraction.Logger].
 * @param account - [operationsLogic.Account], internal implementation handler.
 * @property regexToHandler - maps regular expressions with their parsers.
 * @constructor - initializes [regexToHandler].
 * @throws IllegalArgumentException, when some operation doesn't have a handler.
 */
class InputParser(private val logger: Logger, private val account: Account) {
    private val regexToHandler : HashMap<Regex, OperationHandler> = hashMapOf()

    init {
        for (operation in AvailableOperations.values()) {
            regexToHandler[operation.regex] = when (operation) {
                AvailableOperations.ADD_CURRENCY -> AddCurrencyHandler()
                AvailableOperations.DEPOSIT -> DepositHandler()
                AvailableOperations.DEPOSIT_FIRST_CURRENCY -> DepositFirstCurrencyHandler()
                AvailableOperations.WITHDRAW -> WithdrawHandler()
                AvailableOperations.WITHDRAW_FIRST_CURRENCY -> WithdrawFirstCurrencyHandler()
                AvailableOperations.SET_RATE -> SetRateHandler()
                AvailableOperations.CONVERT -> ConvertHandler()
                AvailableOperations.SHOW_BALANCE -> ShowBalanceHandler()
                AvailableOperations.SHOW_TOTAL -> ShowTotalHandler()
                AvailableOperations.SHOW_TOTAL_FIRST_CURRENCY -> ShowTotalFirstCurrencyHandler()
                AvailableOperations.HELP -> HelpHandler()
                else -> throw IllegalArgumentException("${operation.name} operation has no handler")
            }
        }
    }

    /**
     * Parses the string received as input and passes it to its handler.
     */
    fun parse(string: String) {
        regexToHandler.forEach { (regex, handler) ->
            val foundString = regex.find(string)
            if (foundString != null) {
                handler.handle(foundString.groupValues)
                return
            }
        }

        logger.log("This operation doesn't exist. Please, check the correctness of the input.")
    }

    private interface OperationHandler {
        fun handle(foundGroups : List<String>)
    }

    private inner class AddCurrencyHandler : OperationHandler {
        override fun handle(foundGroups: List<String>) {
            val currency = foundGroups[1]
            account.addCurrency(currency)
        }
    }

    private inner class DepositHandler : OperationHandler {
        override fun handle(foundGroups: List<String>) {
            val amount = foundGroups[1].toDoubleOrNull()
            val currency = foundGroups[2]

            if (amount != null) {
                account.deposit(amount, currency)
            } else {
                logger.log("Input is incorrect: amount must be a real number.")
            }
        }
    }

    private inner class DepositFirstCurrencyHandler : OperationHandler {
        override fun handle(foundGroups: List<String>) {
            val amount = foundGroups[1].toDoubleOrNull()

            if (amount != null) {
                account.deposit(amount)
            } else {
                logger.log("Input is incorrect: amount must be a real number.")
            }
        }
    }

    private inner class WithdrawHandler : OperationHandler {
        override fun handle(foundGroups: List<String>) {
            val amount = foundGroups[1].toDoubleOrNull()
            val currency = foundGroups[2]

            if (amount != null) {
                account.withdraw(amount, currency)
            } else {
                logger.log("Input is incorrect: amount must be a real number.")
            }
        }
    }

    private inner class WithdrawFirstCurrencyHandler : OperationHandler {
        override fun handle(foundGroups: List<String>) {
            val amount = foundGroups[1].toDoubleOrNull()

            if (amount != null) {
                account.withdraw(amount)
            } else {
                logger.log("Input is incorrect: amount must be a real number.")
            }
        }
    }

    private inner class SetRateHandler : OperationHandler {
        override fun handle(foundGroups: List<String>) {
            val currency1 = foundGroups[1]
            val currency2 = foundGroups[2]
            val ratioNumerator = foundGroups[3].toDoubleOrNull()
            val ratioDenominator = foundGroups[4].toDoubleOrNull()

            if (ratioNumerator != null && ratioDenominator != null) {
                account.setRate(currency1, currency2, ratioNumerator, ratioDenominator)
            } else {
                logger.log("Input is incorrect: ratio parts must be real numbers.")
            }
        }
    }

    private inner class ConvertHandler : OperationHandler {
        override fun handle(foundGroups: List<String>) {
            val amount = foundGroups[1].toDoubleOrNull()
            val currency1 = foundGroups[2]
            val currency2 = foundGroups[3]

            if (amount != null) {
                account.convert(amount, currency1, currency2)
            } else {
                logger.log("Input is incorrect: amount must be a real number.")
            }
        }
    }

    private inner class ShowBalanceHandler : OperationHandler {
        override fun handle(foundGroups: List<String>) = account.showBalance()
    }

    private inner class ShowTotalHandler : OperationHandler {
        override fun handle(foundGroups: List<String>) {
            val currency = foundGroups[1]
            account.showTotal(currency)
        }
    }

    private inner class ShowTotalFirstCurrencyHandler : OperationHandler {
        override fun handle(foundGroups: List<String>) = account.showTotal()
    }

    /**
     * @throws IllegalArgumentException, when some operation doesn't have a description in "help" command.
     */
    private inner class HelpHandler : OperationHandler {
        override fun handle(foundGroups: List<String>) {
            println("=================")
            println("Available commands:")
            for (operation in AvailableOperations.values()) {
                when (operation) {
                    AvailableOperations.ADD_CURRENCY -> println("\"add currency <currency>\": " +
                            "adds a new currency to the account. Name must not contain spaces")
                    AvailableOperations.DEPOSIT -> println("\"deposit <amount> <currency>\": " +
                            "deposits <amount> in the specified currency to the account. Amount must be a real number > 0")
                    AvailableOperations.DEPOSIT_FIRST_CURRENCY -> println("\"deposit <amount>\": " +
                            "deposits <amount> in the first added currency to the account. " +
                            "Amount must be a real number > 0")
                    AvailableOperations.WITHDRAW -> println("\"withdraw <amount> <currency>\": " +
                            "withdraws <amount> in the specified currency from the account. " +
                            "Amount must be a real number > 0")
                    AvailableOperations.WITHDRAW_FIRST_CURRENCY -> println("\"withdraw <amount>\": " +
                            "withdraws <amount> in the first added currency from the account. " +
                            "Amount must be a real number > 0")
                    AvailableOperations.SET_RATE -> println("\"set rate <currency 1> <currency 2>\": " +
                            "sets the ratio between <currency 1> and <currency 2>")
                    AvailableOperations.CONVERT -> println("\"convert <amount> <currency 1> to <currency 2>\": " +
                            "converts <amount> on the account from <currency 1> to <currency 2>, " +
                            "if there are enough funds")
                    AvailableOperations.SHOW_BALANCE -> println("\"show balance\": " +
                            "shows amount of every currency is in the account")
                    AvailableOperations.SHOW_TOTAL -> println("\"show total in <currency>\": " +
                            "shows total amount on the account in <currency>")
                    AvailableOperations.SHOW_TOTAL_FIRST_CURRENCY -> println("\"show total\": " +
                            "shows total amount on the account in first added currency")
                    AvailableOperations.HELP -> println("\"help\": shows available commands")
                    else -> throw IllegalArgumentException("${operation.name} operation has no handler")
                }
            }
            println("\"stop\": deletes the account and stops the application")
            println("=================")
        }
    }
}