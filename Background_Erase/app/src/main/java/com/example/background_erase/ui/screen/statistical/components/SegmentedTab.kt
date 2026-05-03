package com.example.background_erase.ui.screen.statistical.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.background_erase.R
import com.example.background_erase.model.TransactionUI
import com.example.background_erase.ui.screen.statistical.TextSecondary

@Preview
@Composable
fun PreviewSegmentTab() {
    SegmentedTab(
        modifier = Modifier.fillMaxWidth().height(48.dp),
        tab = TransactionUI.Type.Expense,
        onTabSelected = {}
    )
}

@Composable
fun SegmentedTab(
    modifier: Modifier,
    tab: TransactionUI.Type,
    onTabSelected: (TransactionUI.Type) -> Unit
) {
    val expenseColor by animateColorAsState(
        targetValue = if (tab == TransactionUI.Type.Expense) Color(0xFFFF9800) else TextSecondary,
        animationSpec = tween(300),
        label = "ExpenseColor"
    )
    val incomeColor by animateColorAsState(
        targetValue = if (tab == TransactionUI.Type.Income) Color(0xFF009688) else TextSecondary,
        animationSpec = tween(300),
        label = "IncomeColor"
    )

    BoxWithConstraints(
        modifier = modifier
    ) {
        val tabWidth = maxWidth / 2

        val indicatorOffset by animateDpAsState(
            targetValue = if (tab == TransactionUI.Type.Expense) 0.dp else tabWidth,
            animationSpec = tween(300),
            label = "IndicatorOffset"
        )

        // SLIDING INDICATOR
        Box(
            modifier = Modifier
                .offset(x = indicatorOffset)
                .width(tabWidth)
                .fillMaxHeight()
                .clip(RoundedCornerShape(12.dp))
                .background(Color.White)
        )

        //  INTERACTIVE LAYER
        Row(modifier = Modifier.matchParentSize()) {
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
                    .clip(RoundedCornerShape(12.dp))
                    .clickable { onTabSelected(TransactionUI.Type.Expense) },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = stringResource(R.string.expense_label),
                    color = expenseColor,
                    fontWeight = FontWeight.Bold
                )
            }

            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
                    .clip(RoundedCornerShape(12.dp))
                    .clickable { onTabSelected(TransactionUI.Type.Income) },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = stringResource(R.string.income_label),
                    color = incomeColor,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}