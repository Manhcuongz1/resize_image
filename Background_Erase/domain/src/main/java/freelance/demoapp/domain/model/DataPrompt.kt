package freelance.demoapp.domain.model

data class DataPrompt(
    val text: String,
    val countryName: String,
    val language: String,
    val currencyUnit: String,
    val patternDate: String,
    val date: String,
    val categories: List<String>,
)