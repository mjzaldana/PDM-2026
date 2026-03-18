data class User(
    val id: Int,
    val name: String,
    val email: String,
    val isActive: Boolean
)

fun main() {

    val users = listOf(
        User(1, "Ana", "ana@mail.com", true),
        User(2, "Luis", "luis@mail.com", false),
        User(3, "Maria", "maria@mail.com", true),
        User(4, "Carlos", "carlos@mail.com", false)
    )

    val activeUsers = users.filter { it.isActive }

    activeUsers.forEach {
        println(it.name)
    }

    val names = users.map { it.name }

    println(names)
}