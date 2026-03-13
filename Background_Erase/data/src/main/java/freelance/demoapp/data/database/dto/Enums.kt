package freelance.demoapp.data.database.dto

class Enums {
    enum class MessageSender(val value : String) {
        User("User"), LLM("LLM")
    }

    enum class TypeTransaction(val value : String) {
        Income("Income"), Expense("Expense")
    }
}