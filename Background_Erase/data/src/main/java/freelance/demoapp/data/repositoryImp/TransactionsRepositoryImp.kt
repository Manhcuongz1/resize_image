package freelance.demoapp.data.repositoryImp

import com.google.firebase.Firebase
import com.google.firebase.ai.ai
import com.google.firebase.ai.type.GenerativeBackend
import freelance.demoapp.data.llm.PromptFactory
import freelance.demoapp.data.llm.generativeModel
import freelance.demoapp.data.llm.toSchema
import freelance.demoapp.data.model.TransactionResponseLLM
import freelance.demoapp.data.model.toTransactionResponse
import freelance.demoapp.domain.model.DataPrompt
import freelance.demoapp.domain.model.Transaction
import freelance.demoapp.domain.model.TransactionResponse
import freelance.demoapp.domain.repository.TransactionsRepository
import kotlinx.serialization.json.Json
import javax.inject.Inject

class TransactionsRepositoryImp @Inject constructor() : TransactionsRepository {
    private val ai = Firebase.ai(backend = GenerativeBackend.googleAI())

    override suspend fun extractTransactions(dataPrompt: DataPrompt): List<TransactionResponse> {
        val schema = Transaction.toSchema()
        val data = ai.generativeModel(schema)
            .generateContent(
                PromptFactory.getPrompt(dataPrompt)
            )
        val json = Json {
            ignoreUnknownKeys = true
            isLenient = true
        }
        val transaction =
            json.decodeFromString<List<TransactionResponseLLM>>(data.text ?: "").map { it.toTransactionResponse() }

        return transaction
    }
}