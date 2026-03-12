package freelance.demoapp.domain.model

data class DataPrompt(
    val text: String,
    val location: String,
    val currencyUnit: String,
    val date: String,
    val categories: List<String>,
)