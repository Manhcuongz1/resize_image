package freelance.demoapp.domain.model

data class Category(
    val id: Long = -1,

    val name: String,

    val type: Transaction.Type,

    val createdAt: Long = System.currentTimeMillis()
)

