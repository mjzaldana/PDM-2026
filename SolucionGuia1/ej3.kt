data class Score(
    val playerName: String,
    val score: Int
)

fun main() {

    val scores = listOf(
        Score("Ana", 120),
        Score("Luis", 200),
        Score("Maria", 180),
        Score("Carlos", 90)
    )

    val topPlayer = scores.maxByOrNull { it.score }

    val average = scores.map { it.score }.average()

    val ranking = scores.sortedByDescending { it.score }

    println("Mejor jugador: ${topPlayer?.playerName}")

    println("Promedio: $average")

    println("Ranking:")

    ranking.forEach {
        println("${it.playerName} - ${it.score}")
    }
}