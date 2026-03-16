package freelance.demoapp.data.llm

import freelance.demoapp.domain.model.DataPrompt

object PromptFactory {

    fun getPrompt(dataPrompt: DataPrompt) : String {
        return getPromptGemini(dataPrompt)
    }

    private fun getPromptGemini(dataPrompt: DataPrompt) : String{
        return """
          Extract financial transactions from the text.
          Text: ${dataPrompt.text}
    
          Today: ${dataPrompt.date} ( ${dataPrompt.patternDate} )
     
          Existing categories:
          ${dataPrompt.categories.joinToString(", ")}
    
          Rules:
          1. Country: ${dataPrompt.countryName}, Language: ${dataPrompt.language}, currency unit: ${dataPrompt.currencyUnit}
          2. Prioritize using an existing Category if the meaning is similar. (Example: "Grab", "Gas" -> use "Transportation").
          3. Only create a new category (1–2 words) if it does not fit any of the categories above
          4. Any NEW category MUST be written in ${dataPrompt.language}.
          5. Separate each income/expense into its own transaction.
          6. Specific details ("Highland Coffee", "Refuel") -> "description"
          7. If there are spelling mistakes, infer from the context
        """.trimIndent()
    }
}