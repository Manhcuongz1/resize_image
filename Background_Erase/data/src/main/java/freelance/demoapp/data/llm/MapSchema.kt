package freelance.demoapp.data.llm

import com.google.firebase.ai.type.Schema
import freelance.demoapp.data.model.TransactionLLM
import freelance.demoapp.domain.model.Transaction

fun Transaction.Companion.toSchema(): Schema {
    return Schema.array(
        Schema.obj(
            properties = mapOf(
                TransactionLLM::type.name to Schema.enumeration(
                    listOf(
                        TransactionLLM.INCOME, TransactionLLM.EXPENSE
                    )
                ),
                TransactionLLM::dateLabel.name to Schema.integer(),
                TransactionLLM::amount.name to Schema.string(),
                TransactionLLM::category.name to Schema.string()
            ),
            optionalProperties = listOf(
                TransactionLLM::type.name,
                TransactionLLM::dateLabel.name,
                TransactionLLM::amount.name,
                TransactionLLM::category.name
            )
        )
    )
}
