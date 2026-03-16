package freelance.demoapp.data.llm

import com.google.firebase.ai.FirebaseAI
import com.google.firebase.ai.GenerativeModel
import com.google.firebase.ai.type.Schema
import com.google.firebase.ai.type.generationConfig

object LLM {
    object Gemini {
        const val GEMINI_2_5_FLASH_LITE = "gemini-2.5-flash-lite"
    }

}

fun FirebaseAI.generativeModel(schema: Schema): GenerativeModel {
    return this.generativeModel(
        modelName = LLM.Gemini.GEMINI_2_5_FLASH_LITE,
        generationConfig = generationConfig {
            responseMimeType = "application/json"
            responseSchema = schema
        },
       // systemInstruction =
    )
}