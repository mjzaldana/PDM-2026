data class Transaction(
    val id: Int,
    val type: String,
    val amount: Double
)

fun main() {

    val transactions = listOf(
        Transaction(1, "income", 1000.0),
        Transaction(2, "expense", 200.0),
        Transaction(3, "expense", 100.0),
        Transaction(4, "income", 500.0)
    )

    val income = transactions
        .filter { it.type == "income" }
        .sumOf { it.amount }

    val expenses = transactions
        .filter { it.type == "expense" }
        .sumOf { it.amount }

    val balance = income - expenses

    println("Ingresos: $income")
    println("Gastos: $expenses")
    println("Balance: $balance")
}