package operationsLogic

import java.lang.IllegalArgumentException
import kotlin.collections.HashMap

/**
 * Implementation of currency converter.
 *
 * A class that allows you to set the ratio of currencies or convert one currency into another.
 *
 * @property currenciesRate - contains all currency ratios.
 * @constructor initializes empty converter.
 */
class CurrencyConverter {
    private val currenciesRate : HashMap<Pair<String, String>, Double> = hashMapOf()

    /**
     * Sets the ratio between [currency1] and [currency2].
     * @see [Account.setRate].
     */
    fun setRate(currency1: String, currency2: String, ratioNumerator: Double, ratioDenominator: Double) {
        currenciesRate[currency1 to currency2] = ratioNumerator / ratioDenominator
    }

    /**
     * Converts [amount] of [currency1] to [currency2].
     *
     * If ratio [currency1] to [currency2] doesn't exist, uses the [currency2] to [currency1] ratio.
     *
     * @see [Account.convert]
     * @return converted amount.
     */
    fun convert(amount: Double, currency1: String, currency2: String): Double {
        if (!currenciesRate.contains(currency1 to currency2) && !currenciesRate.contains(currency2 to currency1)) {
            throw IllegalArgumentException("The ratio of currencies \"$currency1\" and \"$currency2\" is not set")
        }
        if (currenciesRate.contains(currency1 to currency2)) return amount / currenciesRate[currency1 to currency2]!!
        return amount * currenciesRate[currency2 to currency1]!! // !! because we already checked for containing
    }
}