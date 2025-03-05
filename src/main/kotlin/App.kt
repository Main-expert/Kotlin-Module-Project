class App {
    private val archives = mutableListOf<Archive>() // Список архивов

    //Запускает главное меню
    fun run() {
        var running = true
        while (running) {
            val menu = Menu(
                items = archives,
                createItem = { createArchive() },
                onSelect = { archive -> openArchive(archive) },
                createItemName = "новый архив"
            )
            running = menu.showMenu("Список архивов")
        }
    }

    // Создаёт новый архив
    private fun createArchive(): Archive {
        print("Введите название архива: ")
        val name = readLine()?.trim()
        return if (!name.isNullOrEmpty()) {
            val archive = Archive(name)
            archives.add(archive)
            println("Архив \"$name\" создан.")
            archive
        } else {
            println("Ошибка: название архива не может быть пустым.")
            createArchive()
        }
    }

    // Открывает архив
    private fun openArchive(archive: Archive) {
        while (true) {
            val menu = Menu(
                items = archive.notes,
                createItem = { createNote(archive) },
                onSelect = { note -> viewNote(note) },
                createItemName = "новую заметку"
            )
            menu.showMenu("Архив: ${archive.name}")
            return
        }
    }

    // Создаёт заметку
    private fun createNote(archive: Archive): Note {
        print("Введите название заметки: ")
        val title = readLine()?.trim()
        if (title.isNullOrEmpty()) {
            println("Ошибка: название заметки не может быть пустым.")
            return createNote(archive)
        }

        print("Введите текст заметки: ")
        val content = readLine()?.trim()
        if (content.isNullOrEmpty()) {
            println("Ошибка: текст заметки не может быть пустым.")
            return createNote(archive)
        }

        val note = Note(title, content)
        archive.notes.add(note)
        println("Заметка \"$title\" создана.")
        return note
    }

    // Показывает содержимое заметки
    private fun viewNote(note: Note) {
        println("\nЗаметка: ${note.title}")
        println(note.content)
        println("\nНажмите Enter, чтобы вернуться.")
        readLine()
    }

}