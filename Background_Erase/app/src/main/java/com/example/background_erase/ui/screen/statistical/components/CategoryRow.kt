package com.example.background_erase.ui.screen.statistical.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
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
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.background_erase.R
import com.example.background_erase.base.DateFormatter
import com.example.background_erase.base.Space
import com.example.background_erase.base.isPreview
import com.example.background_erase.base.toDefaultLocaleFormat
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
        typeCurrency = "đ"
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

    val categoryColor = Color(item.iconBg)
    val timelineColor = categoryColor.copy(alpha = 0.2f) // Đường kẻ mờ

    Column(modifier) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { if (item.transactions.isNotEmpty()) isExpanded = !isExpanded }
                .padding(vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .background(Color(item.iconBg), CircleShape),
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
                text = buildAnnotatedString {
                    append(item.totalAmount.toDefaultLocaleFormat())
                    withStyle(style = SpanStyle(textDecoration = TextDecoration.Underline)) {
                        append(String.Space)
                        append(item.typeCurrency)
                    }
                },
                fontSize = 16.sp, fontWeight = FontWeight.Bold, color = TextPrimary
            )
            Icon(
                imageVector = Icons.Default.KeyboardArrowDown,
                contentDescription = null,
                modifier = Modifier
                    .padding(start = 8.dp)
                    .rotate(rotation),
                tint = TextSecondary
            )
        }

        AnimatedVisibility(
            visible = if(isPreview()) true else isExpanded,
            modifier = Modifier.padding(0.dp,0.dp,0.dp,8.dp),
            enter = expandVertically() + fadeIn(),
            exit = shrinkVertically() + fadeOut()
        ) {
            Column(
                modifier = Modifier
                    .padding(start = 24.dp)
                    .drawBehind {
                        // Vẽ đường kẻ dọc từ trên xuống dưới
                        drawLine(
                            color = timelineColor,
                            start = Offset(0f, 0f),
                            end = Offset(0f, size.height), // Dừng trước item cuối một chút
                            strokeWidth = 2.dp.toPx()
                        )
                    }
            ) {
                item.transactions.forEach { tx ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 6.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        // Chấm tròn nhỏ trên đường kẻ
                        Box(
                            modifier = Modifier
                                .offset(x = (-4).dp) // Đè lên đường kẻ dọc
                                .size(8.dp)
                                .background(Color.White, CircleShape)
                                .border(2.dp, categoryColor.copy(alpha = 0.5f), CircleShape)
                        )

                        Spacer(modifier = Modifier.width(24.dp))

                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = tx.note.ifEmpty { stringResource(R.string.do_not_note) },
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Medium,
                                color = Color(0xFF44474E)
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = DateFormatter.transactionTime(tx.dateLabel, LocalContext.current), // Bạn có thể map field date thực tế vào đây
                                fontSize = 11.sp,
                                color = Color.LightGray
                            )
                        }

                        Text(
                            text = buildAnnotatedString {
                                append(tx.amount.toDefaultLocaleFormat())
                                withStyle(
                                    style = SpanStyle(textDecoration = TextDecoration.Underline)) {
                                    append(String.Space)
                                    append(item.typeCurrency)
                                }
                            },
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF1A1C1E)
                        )
                    }
                }
            }
        }
    }
}