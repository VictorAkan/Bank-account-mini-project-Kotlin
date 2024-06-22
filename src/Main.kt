
fun main() {
    println("Welcome to your banking system")
    println("What type of account would you like to create?")
    println("1. Debit account\n2. Credit account\n3. Checking account\n")
    println("Choose an option (1, 2 or 3)")

    var accountType = ""
    var userChoice = readln().toInt()

    while(accountType == "") {
        println("The selected option is $userChoice")
         when (userChoice) {
             1 -> accountType = "debit"
             2 -> accountType = "credit"
             3 -> accountType = "checking"
             else -> {
                 println("Invalid option. Please enter a valid option (1,2 or 3)")
                 userChoice = readln().toInt()
             }
         }
    }
    println("You have created a $accountType account")

    var accountBalance = (0..1000).random()
    println("The $accountType balance is $accountBalance dollars")
    val money = (0..1000).random()
    println("The money is $money")
//    output
    var output = 0
    fun withdraw(amount: Int): Int{
        accountBalance -= amount
        println("You successfully withdrew $amount")
        println("The current balance is $accountBalance")
        return amount
    }
    output = withdraw(money)
    println("Your current withdrawal is: $output")

    fun debitWithdraw(amount: Int): Int{
        if (accountBalance == 0) {
            println("Can't withdraw, no money on this account!")
            return amount
        }
        if (amount > accountBalance) {
            println("Not enough money on this account! The checking balance is $accountBalance dollars.")
        }
        return withdraw(amount)
    }
    output = debitWithdraw(money)
    println("You've made a debit of $output")

    fun deposit(amount: Int): Int{
        accountBalance += amount
        println("You successfully deposited $amount and your updated account balance is $accountBalance")
        return amount
    }
    output = deposit(money)
    println("You've successfully deposited $output")

    fun creditDeposit(amount: Int): Int{
        if (accountBalance == 0) {
            println("You don’t need to deposit anything in order to pay off the account since it has already been paid off.")
            return accountBalance
        } else if (accountBalance + amount > 0) {
            println("Deposit failed, you tried to pay off an amount greater than the credit balance. The checking balance is $accountBalance dollars.")
            return 0
        } else if (amount == -accountBalance) {
            accountBalance = 0
            println("You have paid off this account!")
            return amount
        } else {
            return deposit(amount)
        }
    }
    output = creditDeposit(money)
    println("You've successfully credited $output")

    fun transfer(mode: String) {
        val transferAmount: Int?
        when (mode) {
            "withdraw" -> {
                if (accountType == "debit") {
                    transferAmount = debitWithdraw(money)
                    println("The amount you withdrew is $transferAmount dollars.")
                } else {
                    transferAmount = withdraw(money)
                    println("The amount you withdrew is $transferAmount dollars.")
                }
            }
            "deposit" -> {
                if (accountType == "credit") {
                    transferAmount = creditDeposit(money)
                    println("The amount you deposited is $transferAmount dollars.")
                } else {
                    transferAmount = deposit(money)
                    println("The amount you deposited is $transferAmount dollars.")
                }
            }
            else -> return
        }
    }

    var isSystemOpen = true
    var option = 0

    while (isSystemOpen) {
        println("What would you like to do?")
        println("1. Check bank account balance\n2. Withdraw money\n3. Deposit money\n4. Close the app")
        println("Which option do you choose? (1, 2, 3 or 4)")
        option = readln().toInt()
        println("The selected option is $option")

        when (option) {
            1 -> println("The current balance is $accountBalance dollars")
            2 -> transfer("withdraw")
            3 -> transfer("deposit")
            4 -> {
                isSystemOpen = false
                println("The system is closed")
            }
            else -> continue
        }
    }
}