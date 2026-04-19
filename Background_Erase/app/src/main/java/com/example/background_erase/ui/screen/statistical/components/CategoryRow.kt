package com.example.background_erase.ui.screen.statistical.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.background_erase.model.CategoryGroupUI
import com.example.background_erase.ui.screen.statistical.TextPrimary
import com.example.background_erase.ui.screen.statistical.TextSecondary
import org.instancio.Instancio

@Preview
@Composable
private fun Pre() {
    val red = 0xFFFF0000
    val white = 0xFFFFFFFF
    val mock = Instancio.create(CategoryGroupUI::class.java).copy(
        iconChar = "A",
        iconBg = red,
        iconColor = white,
    )
    Surface(
        color = Color.White,
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(horizontal = 24.dp)
    ) {
        CategoryRow(modifier = Modifier.fillMaxWidth(), mock)
    }
}

@Composable
fun CategoryRow(modifier: Modifier,item: CategoryGroupUI) {
    var isExpanded by remember { mutableStateOf(false) }
    val rotation by animateFloatAsState(if (isExpanded) 180f else 0f, label = "")

    Column(modifier) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { if (item.transactions.isNotEmpty()) isExpanded = !isExpanded }
                .padding(vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier.size(48.dp).background(Color(item.iconBg), CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Text(item.iconChar, color = Color(item.iconColor), fontWeight = FontWeight.Bold, fontSize = 18.sp)
            }
            Spacer(modifier = Modifier.width(16.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(item.category.name, fontSize = 16.sp, fontWeight = FontWeight.Medium, color = TextPrimary)
                Text(item.getSubTitle(), fontSize = 13.sp, color = TextSecondary)
            }
            Text(
                text = item.getTotalAmountFormat(),
                fontSize = 16.sp, fontWeight = FontWeight.Bold, color = TextPrimary
            )
            Icon(
                imageVector = Icons.Default.KeyboardArrowDown,
                contentDescription = null,
                modifier = Modifier.rotate(rotation).padding(start = 8.dp),
                tint = TextSecondary
            )
        }

        AnimatedVisibility(visible = isExpanded) {
            Column(modifier = Modifier.padding(start = 64.dp, bottom = 12.dp)) {
                item.transactions.forEach { tx ->
                    Row(modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)) {
                        Text(tx.note, modifier = Modifier.weight(1f), fontSize = 14.sp)
                        Text(tx.amount.toString(), fontSize = 14.sp, fontWeight = FontWeight.Medium)
                    }
                }
            }
        }
    }
}