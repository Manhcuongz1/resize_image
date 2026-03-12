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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.background_erase.ui.resource.BorderLightColor
import com.example.background_erase.ui.resource.ExpenseBgColor
import com.example.background_erase.ui.resource.ExpenseColor
import com.example.background_erase.ui.resource.IncomeBgColor
import com.example.background_erase.ui.resource.IncomeColor
import com.example.background_erase.ui.resource.ItemBgColor
import com.example.background_erase.ui.screen.home.model.TransactionUI
import com.example.background_erase.ui.screen.home.model.TransactionListUI
import com.example.background_erase.ui.screen.home.model.TransactionTypeUI
import java.text.DecimalFormat

@Preview()
@Composable
fun AiTransactionBubblePreview() {
    val
            payLoad = TransactionListUI(
        listOf(
            TransactionUI(
                type = TransactionTypeUI.EXPENSE,
                dateLabel = "Hôm qua",
                amount = 100000,
                category = "Ăn uống",
                note = "Bún bò"
            ),
            TransactionUI(
                type = TransactionTypeUI.INCOME,
                dateLabel = "Hôm qua",
                amount = 200000,
                category = "Tiền lương",
                note = "Lương tháng 1"
            ),
            TransactionUI(
                type = TransactionTypeUI.INCOME,
                dateLabel = "Hôm nay",
                amount = 200000,
                category = "Tiền lương",
                note = "Thưởng tháng 1"
            ),
        )

    )
    MessageOfLLM(title = "Transactions", payLoad)
}


@Composable
fun MessageOfLLM(title: String, messageTransaction: TransactionListUI) {
    // Khung bong bóng bên ngoài
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 48.dp, top = 8.dp, bottom = 8.dp) // end=48dp để tạo khoảng trống bên phải vì là tin nhắn của bot
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
            fontSize = 18.sp,
            fontWeight = FontWeight.Medium,
            color = Color(0xFF2C3E50)
        )

        Spacer(modifier = Modifier.height(16.dp))

        val groupedTransactions = messageTransaction.transactions.groupBy { it.dateLabel }

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
fun DateSeparator(label: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        HorizontalDivider(modifier = Modifier.weight(1f), color = BorderLightColor)
        Text(
            text = label.uppercase(),
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Gray,
            modifier = Modifier.padding(horizontal = 12.dp)
        )
        HorizontalDivider(modifier = Modifier.weight(1f), color = BorderLightColor)
    }
}

@Composable
fun TransactionItem(info: TransactionUI) {
    val isExpense = info.type == TransactionTypeUI.EXPENSE
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
                text = info.category,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
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
