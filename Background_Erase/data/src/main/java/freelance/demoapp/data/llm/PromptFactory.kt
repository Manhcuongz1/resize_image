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
    
          Today: ${dataPrompt.date}.
     
          Existing categories:
          ${dataPrompt.categories.joinToString(", ")}
    
          Rules:
          1. Prioritize using an existing Category if the meaning is similar. (Example: "Grab", "Gas" -> use "Transportation").
          2. Only create a new category (1–2 words) if it does not fit any of the categories above
          3. Separate each income/expense into its own transaction.
          4. User: Vietnam. Currency unit: VND
          5. Specific details ("Highland Coffee", "Refuel") -> "description"
          6. If there are spelling mistakes, infer from the context
        """.trimIndent()
    }
}