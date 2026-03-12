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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.background_erase.ui.resource.BotBubbleColor
import com.example.background_erase.ui.resource.UserBubbleColor
import com.example.background_erase.ui.screen.home.model.Message


@Composable
fun MessageBubble(text : String, sender: Message.Sender) {
    val (alignment, backgroundColor, textColor) = when (sender) {
        is Message.Sender.User -> {
            Triple(Alignment.CenterEnd, UserBubbleColor, Color.White)
        }
        is Message.Sender.LLM -> {
            Triple(Alignment.CenterStart, BotBubbleColor, Color.Black)
        }

    }

    // Bo góc tùy biến: bubble của bot và user thường có 1 góc nhọn để tạo đuôi (tail)
    val bubbleShape = if (sender is Message.Sender.User) {
        RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp, bottomStart = 16.dp, bottomEnd = 4.dp)
    } else {
        RoundedCornerShape(topStart = 4.dp, topEnd = 16.dp, bottomStart = 16.dp, bottomEnd = 16.dp)
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 0.dp, vertical = 8.dp),
        contentAlignment = alignment
    ) {
        Text(
            text = text,
            color = textColor,
            fontSize = 16.sp,
            modifier = Modifier
                .background(color = backgroundColor, shape = bubbleShape)
                .padding(horizontal = 16.dp, vertical = 12.dp)
                .widthIn(max = 280.dp)
        )
    }
}