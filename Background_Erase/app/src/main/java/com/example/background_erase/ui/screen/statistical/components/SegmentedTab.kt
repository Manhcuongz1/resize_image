package com.example.background_erase.ui.screen.statistical.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.background_erase.model.TransactionUI
import com.example.background_erase.ui.screen.statistical.TextSecondary

@Composable
fun SegmentedTab(modifier: Modifier, tab: TransactionUI.Type, onTabSelected: (TransactionUI.Type) -> Unit) {
    Row(
        modifier = modifier
    ) {
        val modifier = Modifier
            .weight(1f)
            .fillMaxHeight()
            .clip(RoundedCornerShape(12.dp))

        Box(
            modifier
                .background(if (tab == TransactionUI.Type.Expense) Color.White else Color.Transparent)
                .clickable {
                    onTabSelected(TransactionUI.Type.Expense)
                },
            contentAlignment = Alignment.Center
        ) {
            Text(
                "Chi tiêu",
                color = if (tab == TransactionUI.Type.Expense) Color(0xFF009688) else TextSecondary
            )
        }

        Box(
            modifier
                .background(if (tab == TransactionUI.Type.Income) Color.White else Color.Transparent)
                .clickable {
                    onTabSelected(TransactionUI.Type.Income)
                },
            contentAlignment = Alignment.Center
        ) {
            Text(
                "Thu nhập",
                color = if (tab == TransactionUI.Type.Income) Color(0xFF009688) else TextSecondary
            )
        }
    }
}