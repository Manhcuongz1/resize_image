package freelance.demoapp.domain.model

data class Category(
    val id: Long = -1,

    val name: String,

    val colorBackground: Long,

    val type: Transaction.Type,

    val createdAt: Long = System.currentTimeMillis()
) {
    companion object {
        const val COLOR_ICON_DEFAULT : Long = 0xFFFFFFFF
    }
}

