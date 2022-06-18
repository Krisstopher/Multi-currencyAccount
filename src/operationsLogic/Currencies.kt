package operationsLogic

import java.lang.IllegalArgumentException
import kotlin.collections.HashSet

/**
 * Implementation of currency storage.
 *
 * A class that allows you to add a currency to the storage or find the currency in the storage.
 *
 * @property availableCurrencies - storage of currencies.
 * @property firstAddedCurrency - variable that contains the first added currency.
 * @constructor initializes empty storage.
 */

class Currencies {
    private val availableCurrencies: HashSet<String> = hashSetOf()
    private var firstAddedCurrency : String? = null

    /**
     * Adds [currency] to the [availableCurrencies].
     *
     * @return false, if currency already exists in [availableCurrencies], or false otherwise.
     */
    fun add(currency: String): Boolean {
        firstAddedCurrency = firstAddedCurrency ?: currency

        if (availableCurrencies.contains(currency)) return false
        availableCurrencies.add(currency)

        return true
    }

    /**
     * Checks if there is a currency in the [availableCurrencies].
     *
     * @throws IllegalArgumentException - if [availableCurrencies] is empty.
     * @return true, if param [currency] is specified and exists in [availableCurrencies];
     * false, if specified and doesn't exists;
     * true, if not specified and [availableCurrencies] not empty; false otherwise.
     */
    fun contains(currency: String? = null): Boolean {
        if (currency == null) {
            if (firstAddedCurrency != null) {
                return true
            }
            throw IllegalArgumentException("No one currency enabled.")
        }

        if (availableCurrencies.contains(currency))
            return true
        return false
    }

    /**
     * Gets the currency from storage.
     *
     * ATTENTION: can only be used after [contains]!
     *
     * @return [currency], if it exists in [availableCurrencies] and specified, or return [firstAddedCurrency].
     */
    fun get(currency: String? = null): String {
        return currency ?: firstAddedCurrency!!
    }
}