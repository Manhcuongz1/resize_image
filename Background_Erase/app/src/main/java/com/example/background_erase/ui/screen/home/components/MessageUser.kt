package com.example.background_erase.ui.screen.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.background_erase.ui.resource.UserBubbleColor


@Composable
fun MessageUser(text : String) {
    val (alignment, backgroundColor, textColor) = Triple(Alignment.CenterEnd, UserBubbleColor, Color.White)


    // Bo góc tùy biến: bubble của bot và user thường có 1 góc nhọn để tạo đuôi (tail)
    val bubbleShape =
        RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp, bottomStart = 16.dp, bottomEnd = 2.dp)

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 0.dp, vertical = 8.dp),
        contentAlignment = alignment
    ) {
        Text(
            text = text,
            color = textColor,
            fontSize = 14.sp,
            style = TextStyle(
                platformStyle = PlatformTextStyle(
                    includeFontPadding = false
                )
            ),
            modifier = Modifier
                .background(color = backgroundColor, shape = bubbleShape)
                .padding(horizontal = 16.dp, vertical = 12.dp)
                .widthIn(max = 280.dp)
        )
    }
}