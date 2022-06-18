package parsingLogic

/**
 * Stores existing operations and their regular expressions.
 */
enum class AvailableOperations(val regex: Regex) {
    ADD_CURRENCY(Regex("^add currency ([\\S]+)$")),
    DEPOSIT(Regex("^deposit ([\\S]+) ([\\S]+)$")),
    DEPOSIT_FIRST_CURRENCY(Regex("^deposit ([\\S]+)$")),
    WITHDRAW(Regex("^withdraw ([\\S]+) ([\\S]+)$")),
    WITHDRAW_FIRST_CURRENCY(Regex("^withdraw ([\\S]+)$")),
    SET_RATE(Regex("^set rate ([\\S]+) ([\\S]+) ([\\S]+):([\\S]+)$")),
    CONVERT(Regex("^convert ([\\S]+) ([\\S]+) to ([\\S]+)$")),
    SHOW_BALANCE(Regex("^show balance$")),
    SHOW_TOTAL(Regex("^show total in ([\\S]+)$")),
    SHOW_TOTAL_FIRST_CURRENCY(Regex("^show total$")),
    HELP(Regex("^help$"))
}