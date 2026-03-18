fun main() {

    val emails = listOf(
        "a@mail.com",
        "b@mail.com",
        "a@mail.com",
        "c@mail.com"
    )

    val duplicates = emails
        .groupBy { it }
        .filter { it.value.size > 1 }
        .keys

    duplicates.forEach {
        println(it)
    }

}