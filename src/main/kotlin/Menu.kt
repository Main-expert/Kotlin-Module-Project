import java.util.Scanner

class Menu<T>(private val items: List<T>, private val createItem: () -> T, private val onSelect: (T) -> Unit, private val createItemName: String) {
    private val scanner = Scanner(System.`in`)

    // Показ меню
    fun showMenu(title: String): Boolean {
        while (true) {
            println("\n$title")
            println("0. Создать $createItemName")
            items.forEachIndexed { index, item -> println("${index + 1}. $item") }
            println("${items.size + 1}. Выход")

            print("Введите номер: ")
            val input = scanner.nextLine()

            when {
                input == (items.size + 1).toString() -> return false // Выход из меню
                input == "0" -> {
                    val newItem = createItem()
                    if (newItem != null) println("Создано: $newItem")
                }
                input.toIntOrNull() in 1..items.size -> onSelect(items[input.toInt() - 1])
                else -> println("Ошибка ввода. Введите число от 0 до ${items.size + 1}.")
            }
        }
    }
}