package freelance.demoapp.data.repositoryImp

import com.google.firebase.Firebase
import com.google.firebase.ai.ai
import com.google.firebase.ai.type.GenerativeBackend
import freelance.demoapp.data.llm.PromptFactory
import freelance.demoapp.data.llm.generativeModel
import freelance.demoapp.data.llm.toSchema
import freelance.demoapp.data.model.TransactionLLM
import freelance.demoapp.data.model.toTransaction
import freelance.demoapp.domain.model.DataPrompt
import freelance.demoapp.domain.model.Transaction
import freelance.demoapp.domain.repository.TransactionsRepository
import kotlinx.serialization.json.Json
import javax.inject.Inject

class TransactionsRepositoryImp @Inject constructor() : TransactionsRepository {
    private val ai = Firebase.ai(backend = GenerativeBackend.googleAI())

    override suspend fun extractTransactions(dataPrompt: DataPrompt): List<Transaction> {
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
            json.decodeFromString<List<TransactionLLM>>(data.text ?: "").map { it.toTransaction() }

        return transaction
    }
}