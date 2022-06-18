package userInteraction

import java.lang.IllegalArgumentException
import java.math.RoundingMode
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.*

class Logger(private var loggerMod: LoggerMod = LoggerMod.CONSOLE) {
    enum class LoggerMod {
        CONSOLE,
        DEBUG
    }

    private val logInfo: MutableList<String> = mutableListOf()
    private val outFormatDouble : DecimalFormat

    init {
        val outFormatSymbols = DecimalFormatSymbols(Locale.getDefault())
        outFormatSymbols.decimalSeparator = '.'
        outFormatDouble = DecimalFormat("#.##", outFormatSymbols)
        outFormatDouble.roundingMode = RoundingMode.DOWN
    }

    fun <T> log(vararg messages: T?) {
        var printlnFlag = false
        val fullLog = StringBuilder()

        for (message in messages) {
            if (message == null) break

            val stringMessage = when (message) {
                is String -> message
                is Double -> outFormatDouble.format(message)
                else -> throw IllegalArgumentException("Undefined logging message format")
            }

            when (loggerMod) {
                LoggerMod.CONSOLE -> { print(stringMessage)
                    printlnFlag = true }
                else -> fullLog.append(stringMessage)
            }
        }

        if (printlnFlag) println()
        if (fullLog.isNotEmpty()) logInfo.add(fullLog.toString())
    }

    fun changeMod(newMod: LoggerMod) {
        loggerMod = newMod
    }

    fun getMod() = loggerMod

    fun getLogInfo() = logInfo.toList()
}