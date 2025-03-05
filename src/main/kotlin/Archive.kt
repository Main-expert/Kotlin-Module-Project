class Archive(val name: String) {
    val notes = mutableListOf<Note>() // Список заметок

    // Возвращает корректное название архива
    override fun toString(): String {
        return name
    }
}