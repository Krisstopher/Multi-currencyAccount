# Multi-currency Account

The program implements a console application for a multi-currency account.

## Launch Instructions

Run src/Main.kt via the development environment or the console of your OS.

## Commands
**add currency <currency>**: adds a new currency to the account. Name must not contain spaces

**deposit <amount> <currency>**: deposits <amount> in the specified currency to the account. Amount must be a real number > 0

**deposit <amount>**: deposits <amount> in the first added currency to the account. Amount must be a real number > 0

**withdraw <amount> <currency>**: withdraws <amount> in the specified currency from the account. Amount must be a real number > 0

**withdraw <amount>**: withdraws <amount> in the first added currency from the account. Amount must be a real number > 0

**set rate <currency 1> <currency 2>**: sets the ratio between <currency 1> and <currency 2>

**convert <amount> <currency 1> to <currency 2>**: converts <amount> on the account from <currency 1> to <currency 2>, if there are enough funds

**show balance**: shows amount of every currency is in the account

**show total in <currency>**: shows total amount on the account in <currency>

**show total**: shows total amount on the account in first added currency

**help**: shows available commands

**stop**: deletes the account and stops the application
## Example
```
add currency ruble
deposit 100
add currency dollar
set rate dollar ruble 1:60
deposit 1 dollar
convert 80 ruble to dollar
withdraw 2.1 dollar
show balance
20 ruble
0.23 dollar
show total
34 ruble
show total in dollar
0.56 dollar
```
## Tests

You can also run tests in src/test.