package com.example.background_erase.ui.screen.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDownward
import androidx.compose.material.icons.filled.ArrowUpward
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.background_erase.base.DateFormatter
import com.example.background_erase.model.CategoryUI
import com.example.background_erase.model.MessageUI
import com.example.background_erase.model.TransactionUI
import com.example.background_erase.ui.resource.BorderLightColor
import com.example.background_erase.ui.resource.BotBubbleColor
import com.example.background_erase.ui.resource.ExpenseBgColor
import com.example.background_erase.ui.resource.ExpenseColor
import com.example.background_erase.ui.resource.IncomeBgColor
import com.example.background_erase.ui.resource.IncomeColor
import com.example.background_erase.ui.resource.ItemBgColor
import java.math.BigDecimal
import java.text.DecimalFormat

@Preview()
@Composable
fun AiTransactionBubblePreview() {
    val payLoad = MessageUI(
        sender = MessageUI.Sender.LLM,
        content = "Đã nhập các thu chi sau:",
        conversationId = 1,
        transaction = listOf(
            TransactionUI(
                type = TransactionUI.Type.EXPENSE,
                dateLabel = System.currentTimeMillis(),
                amount = BigDecimal("10000"),
                category = CategoryUI(
                    id = 1,
                    name = "Ăn uống",
                    type = TransactionUI.Type.EXPENSE
                ),
                note = "Bún bò"
            ),
            TransactionUI(
                type = TransactionUI.Type.EXPENSE,
                dateLabel = System.currentTimeMillis(),
                amount = BigDecimal("200000"),
                category = CategoryUI(
                    id = 1,
                    name = "Tiền lương",
                    type = TransactionUI.Type.EXPENSE
                ),
                note = "Lương tháng 1"
            ),
            TransactionUI(
                type = TransactionUI.Type.INCOME,
                dateLabel = System.currentTimeMillis(),
                amount = BigDecimal(2000000) + BigDecimal(100000),
                category = CategoryUI(
                    id = 1,
                    name = "Tiền thưởng",
                    type = TransactionUI.Type.EXPENSE
                ),
                note = "Thưởng tháng 1"
            ),
        )
    )

    MessageLLM(payLoad)
}


@Composable
fun MessageLLM(messageUI: MessageUI) {
    if (messageUI.transaction.isNotEmpty())
        MessageTransaction(messageUI.content, messageUI.transaction)
    else
        MessageNormal(messageUI.content)
}

@Composable
private fun MessageTransaction(
    title: String,
    transactions: List<TransactionUI>
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                start = 16.dp,
                end = 48.dp,
                top = 8.dp,
                bottom = 8.dp
            ) // end=48dp để tạo khoảng trống bên phải vì là tin nhắn của bot
            .border(
                width = 1.dp,
                color = BorderLightColor,
                shape = RoundedCornerShape(
                    topStart = 4.dp,
                    topEnd = 24.dp,
                    bottomStart = 24.dp,
                    bottomEnd = 24.dp
                )
            )
            .background(
                Color.White,
                RoundedCornerShape(
                    topStart = 4.dp,
                    topEnd = 24.dp,
                    bottomStart = 24.dp,
                    bottomEnd = 24.dp
                )
            )
            .padding(16.dp)
    ) {
        Text(
            text = title,
            fontSize = 16.sp,
            color = Color.Black
        )

        Spacer(modifier = Modifier.height(16.dp))

        val groupedTransactions = transactions.groupBy { it.dateLabel }

        groupedTransactions.forEach { (dateLabel, items) ->
            DateSeparator(dateLabel)
            Spacer(modifier = Modifier.height(12.dp))

            items.forEach { item ->
                TransactionItem(item)
                Spacer(modifier = Modifier.height(12.dp))
            }
        }
    }
}

@Composable
fun DateSeparator(label: Long) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        HorizontalDivider(modifier = Modifier.weight(1f), color = BorderLightColor)
        Text(
            text = DateFormatter.transactionTime(label,context = LocalContext.current),
            fontSize = 12.sp,
            fontWeight = FontWeight.Medium,
            color = Color.Gray,
            modifier = Modifier.padding(horizontal = 12.dp)
        )
        HorizontalDivider(modifier = Modifier.weight(1f), color = BorderLightColor)
    }
}

@Composable
fun TransactionItem(info: TransactionUI) {
    val isExpense = info.type == TransactionUI.Type.EXPENSE
    val iconColor = if (isExpense) ExpenseColor else IncomeColor
    val iconBgColor = if (isExpense) ExpenseBgColor else IncomeBgColor
    val iconVector = if (isExpense) Icons.Default.ArrowUpward else Icons.Default.ArrowDownward // Tiền ra (Up), Tiền vào (Down)
    val amountPrefix = if (isExpense) "-" else "+"

    // Format tiền tệ chuẩn Việt Nam
    val formatter = DecimalFormat("#,###")
    val formattedAmount = "${amountPrefix}${formatter.format(info.amount)} đ"

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .border(1.dp, BorderLightColor, RoundedCornerShape(16.dp))
            .background(ItemBgColor, RoundedCornerShape(16.dp))
            .padding(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Icon
        Box(
            modifier = Modifier
                .size(48.dp)
                .clip(CircleShape)
                .background(iconBgColor),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = iconVector,
                contentDescription = null,
                tint = iconColor,
                modifier = Modifier.size(24.dp)
            )
        }

        Spacer(modifier = Modifier.width(12.dp))

        // Nội dung (Category + Note)
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = info.category.name,
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                color = Color.Black
            )
            Text(
                text = info.note,
                fontSize = 14.sp,
                color = Color.Gray
            )
        }

        // Số tiền
        Text(
            text = formattedAmount,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = iconColor,
            style = TextStyle(
                fontFeatureSettings = "tnum"
            )
        )
    }
}

@Composable
fun MessageNormal(text : String,) {
    val (alignment, backgroundColor, textColor) =
            Triple(Alignment.CenterStart, BotBubbleColor, Color.Black)
    val bubbleShape =
        RoundedCornerShape(topStart = 4.dp, topEnd = 16.dp, bottomStart = 16.dp, bottomEnd = 16.dp)

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 0.dp, vertical = 8.dp),
        contentAlignment = alignment,
    ) {
        Text(
            text = text,
            color = textColor,
            fontSize = 14.sp,
            modifier = Modifier
                .background(color = backgroundColor, shape = bubbleShape)
                .padding(horizontal = 16.dp, vertical = 12.dp)
                .widthIn(max = 280.dp),
            style = TextStyle(
                platformStyle = PlatformTextStyle(
                    includeFontPadding = false
                )
            ),
        )
    }
}
