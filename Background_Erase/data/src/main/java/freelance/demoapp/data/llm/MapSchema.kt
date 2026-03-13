package freelance.demoapp.data.llm

import com.google.firebase.ai.type.Schema
import freelance.demoapp.data.model.TransactionResponseLLM
import freelance.demoapp.domain.model.Transaction

fun Transaction.Companion.toSchema(): Schema {
    return Schema.array(
        Schema.obj(
            properties = mapOf(
                TransactionResponseLLM::type.name to Schema.enumeration(
                    listOf(
                        TransactionResponseLLM.INCOME, TransactionResponseLLM.EXPENSE
                    )
                ),
                TransactionResponseLLM::dateLabel.name to Schema.integer(),
                TransactionResponseLLM::amount.name to Schema.string(),
                TransactionResponseLLM::category.name to Schema.string()
            ),
            optionalProperties = listOf(
                TransactionResponseLLM::type.name,
                TransactionResponseLLM::dateLabel.name,
                TransactionResponseLLM::amount.name,
                TransactionResponseLLM::category.name
            )
        )
    )
}
