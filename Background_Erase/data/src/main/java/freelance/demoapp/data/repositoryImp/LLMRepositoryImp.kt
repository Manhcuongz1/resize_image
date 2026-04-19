package freelance.demoapp.data.repositoryImp

import android.util.Log
import com.google.firebase.Firebase
import com.google.firebase.ai.ai
import com.google.firebase.ai.type.GenerativeBackend
import com.google.firebase.ai.type.Schema
import com.google.firebase.ai.type.generationConfig
import freelance.demoapp.data.llm.PromptFactory
import freelance.demoapp.data.llm.toSchema
import freelance.demoapp.data.model.TransactionResponseLLM
import freelance.demoapp.data.model.toTransactionResponse
import freelance.demoapp.domain.model.DataPrompt
import freelance.demoapp.domain.model.TransactionResponseFromLLM
import freelance.demoapp.domain.repository.LLMRepository
import kotlinx.serialization.json.Json
import javax.inject.Inject

class LLMRepositoryImp @Inject constructor() : LLMRepository {

    val GEMINI_2_5_FLASH_LITE = "gemini-3-flash-preview"
    private fun generativeModel(schema: Schema) =
        Firebase.ai(backend = GenerativeBackend.vertexAI("global"))
            .generativeModel(
                modelName = GEMINI_2_5_FLASH_LITE,
                generationConfig = generationConfig {
                    responseMimeType = "application/json"
                    responseSchema = schema
                })

    override suspend fun extractTransactions(dataPrompt: DataPrompt): List<TransactionResponseFromLLM> {
        val schema = TransactionResponseLLM.toSchema()
        val data = generativeModel(schema)
            .generateContent(PromptFactory.getPrompt(dataPrompt))

        val json = Json {
            ignoreUnknownKeys = true
            isLenient = true
        }
        Log.d("Gemini call","request: ${PromptFactory.getPrompt(dataPrompt)}")
        Log.d("Gemini call","response: ${data.text}")
        Log.d("Gemini call", """
                Prompt tokens: ${data.usageMetadata?.promptTokenCount}
                Candidate tokens: ${data.usageMetadata?.candidatesTokenCount}
                Total tokens: ${data.usageMetadata?.totalTokenCount}
            """.trimIndent()
        )
        val transaction =
            json.decodeFromString<List<TransactionResponseLLM>>(data.text ?: "").map { it.toTransactionResponse() }

        return transaction
    }
}