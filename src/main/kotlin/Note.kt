class Note(val title: String, val content: String) {
    // Возвращает корректное название заметки
    override fun toString(): String {
        return title
    }
}