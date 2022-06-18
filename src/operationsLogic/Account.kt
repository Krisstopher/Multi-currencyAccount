package operationsLogic

import userInteraction.Logger
import java.util.*

/**
 * Implementation of application operations.
 *
 * A class that implements the logic of application operations.
 * The functions take correctly parsed values as input and pass the output to the [userInteraction.Logger].
 *
 * @property logger - output handler.
 * @property currencyConverter - [CurrencyConverter].
 * @property currencies - [Currencies].
 * @property currencyAmount - stores the current amount for each currency.
 * @constructor initializes empty account.
 */
class Account(private val logger: Logger) {
    private val currencyConverter = CurrencyConverter()
    private val currencies = Currencies()
    private val currencyAmount: HashMap<String, Double> = hashMapOf()

    /**
     * Checks if the [currency] exists in the [currencies].
     * @return true if it exists, and false otherwise.
     */
    private fun checkCurrency(currency: String?): Boolean {
        try {
            if (!currencies.contains(currency)) {
                logger.log("Type of currency \"$currency\" doesn't exist.")
                return false
            } else {
                return true
            }
        } catch (e: IllegalArgumentException) {
            logger.log(e.message)
            return false
        }
    }

    /**
     * Checks if the [amount] is positive.
     * @return true if positive, and false otherwise.
     */
    private fun checkAmount(amount: Double): Boolean {
        if (amount <= 0) logger.log("Amount must be > 0.")
        return amount > 0
    }

    /**
     * Adds [currency] to the [currencies].
     */
    fun addCurrency(currency: String) {
        if (!currencies.add(currency)) {
            logger.log("Type of currency \"$currency\" already exists in this account.")
        }
        currencyAmount[currency] = 0.0
    }

    /**
     * Adds [amount] to specified currency.
     *
     * Adds [amount] to the specified currency, if it exists in [currencies],
     * or to the first currency added to the [currencies], if [currencies] not empty.
     */
    fun deposit(amount: Double, currency: String? = null) {
        if (!checkAmount(amount)) return
        if (checkCurrency(currency)) {
            val thisCurrency = currencies.get(currency)
            currencyAmount[thisCurrency] = currencyAmount[thisCurrency]!!.plus(amount)
            // !! because currency exists in currencyAmount
        }
    }

    /**
     * Subtracts [amount] from the specified currency.
     *
     * Subtracts [amount] from the specified currency, if it exists in [currencies] and old amount > [amount],
     * or to the first currency added to the [currencies], if [currencies] not empty.
     */
    fun withdraw(amount: Double, currency: String? = null) {
        if (!checkAmount(amount)) return
        if (checkCurrency(currency)) {
            val thisCurrency = currencies.get(currency)
            val currentAmount = currencyAmount[thisCurrency]!! // !! because currency exists in currencyAmount

            if (currentAmount < amount) {
                logger.log("There are not enough money in \"$thisCurrency\" type of currency for withdrawal.")
            } else {
                currencyAmount[thisCurrency] = currencyAmount[thisCurrency]!!.minus(amount)
            }
        }
    }

    /**
     * Sets the ratio between [currency1] and [currency2].
     *
     * @param currency1 - first currency.
     * @param currency2 - second currency.
     * @param ratioNumerator - numerator of [currency1] to [currency2] ratio.
     * @param ratioDenominator - denominator of [currency1] to [currency2] ratio.
     */
    fun setRate(currency1: String, currency2: String, ratioNumerator: Double, ratioDenominator: Double) {
        if (!checkAmount(ratioNumerator) || !checkAmount(ratioDenominator)) return
        if (currency1 == currency2) {
            logger.log("The specified currencies must be different")
            return
        }
        if (checkCurrency(currency1) && checkCurrency(currency2)) {
            currencyConverter.setRate(currency1, currency2, ratioNumerator, ratioDenominator)
        }
    }

    /**
     * Converts [amount] value from [currency1] to [currency2].
     *
     * Converts [amount] value from [currency1] to [currency2], if [currency1] and [currency2] exists in [currencies]
     * and value in [currencyAmount] for [currency1] >= [amount].
     */
    fun convert(amount: Double, currency1: String, currency2: String) {
        if (!checkAmount(amount)) return
        if (checkCurrency(currency1) && checkCurrency(currency2)) {
            val oldAmount = currencyAmount[currency1]!! // !! because currency exists in currencyAmount
            if (oldAmount < amount) {
                logger.log("Not enough money: you have only ", oldAmount, " $currency1.")
                return
            }
            try {
                val newAmount = currencyConverter.convert(amount, currency1, currency2)
                withdraw(amount, currency1)
                deposit(newAmount, currency2)
            } catch (e: IllegalArgumentException) {
                logger.log(e.message)
            }
        }
    }

    /**
     * Logging amount of each existing in [currencies] currency.
     */
    fun showBalance() {
        currencyAmount.forEach { (currency, amount) -> logger.log(amount, " $currency") }
    }

    /**
     * Logging summary amount in specified currency.
     *
     * Logging summary amount of all currencies in the account in specified currency, if it exists,
     * or in the first currency added to the [currencies], if [currencies] not empty.
     */
    fun showTotal(currency: String? = null) {
        if (checkCurrency(currency)) {
            val currencyTo = currencies.get(currency)
            var totalAmount = 0.0
            var errorFlag = false

            currencyAmount.forEach { (currencyFrom, amount) ->
                try {
                    if (currencyFrom == currencyTo) {
                        totalAmount += amount
                    } else {
                        totalAmount += currencyConverter.convert(amount, currencyFrom, currencyTo)
                    }
                } catch (e: IllegalArgumentException) {
                    errorFlag = true
                    logger.log(e.message)
                }
            }

            if (!errorFlag) logger.log(totalAmount, " $currencyTo")
        }
    }
}