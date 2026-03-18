fun processNumbers(numbers: List<Int>, operation: (Int) -> Int): List<Int> {
    return numbers.map { operation(it) }
}

fun main() {

    val numbers = listOf(1,2,3,4)

    val doubled = processNumbers(numbers) {
        it * 2
    }

    println(doubled)
}