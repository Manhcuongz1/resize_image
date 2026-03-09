
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material.icons.filled.GridView
import androidx.compose.material.icons.filled.ViewQuilt
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.example.background_erase.ui.screen.home.model.Message

// Định nghĩa màu sắc từ UI
val BotBubbleColor = Color(0xFFF4F5F7)
val UserBubbleColor = Color(0xFF000000)
val IconBackgroundColor = Color(0xFFF4F5F7)
val SendButtonColor = Color(0xFFAFAFAF)
val BorderColor = Color(0xFFE5E7EB)

@Preview
@Composable
fun ChatHomeScreen(

) {
    ConstraintLayout(
        Modifier.fillMaxHeight()
    ) {
        val (header, bodyChat, footer) = createRefs()
        TopActionButtons(
            modifier = Modifier.constrainAs(header) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
        )

        LazyColumn(
            modifier = Modifier
                .constrainAs(bodyChat) {
                    top.linkTo(header.bottom)
                    bottom.linkTo(footer.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    height = Dimension.fillToConstraints
                }
                .fillMaxWidth(),
            contentPadding = PaddingValues(vertical = 16.dp),
            reverseLayout = true
        ) {
            item {
                MessageBubble(
                    Message(
                        text = "Xin chào. Hôm nay bạn đã chi tiêu gì chưa? Hãy nhắn cho tôi nhé.",
                        sender = Message.Sender.Gemini
                    )
                )
            }
            item {
                MessageBubble(
                    Message(
                        text = "Xin chào. Hôm nay bạn đã chi tiêu gì chưa? Hãy nhắn cho tôi nhé."
                    )
                )
            }
            item {
                TypingIndicator()
            }
        }

        ChatInputBar(
            modifier = Modifier
                .constrainAs(footer) {
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
                .fillMaxWidth()

        )
    }
}

@Composable
fun TopActionButtons(modifier: Modifier) {
    Column(modifier = modifier) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 6.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            IconButton(
                onClick = { /* TODO */ },
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape)
                    .background(IconBackgroundColor)
            ) {
                Icon(
                    imageVector = Icons.Default.GridView,
                    contentDescription = "Menu",
                    tint = Color.DarkGray
                )
            }

            IconButton(
                onClick = { /* TODO */ },
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape)
                    .background(IconBackgroundColor)
            ) {
                Icon(
                    imageVector = Icons.Default.ViewQuilt, // Icon gần giống trên ảnh
                    contentDescription = "Layout",
                    tint = Color.DarkGray
                )
            }
        }
        HorizontalDivider(color = BorderColor, thickness = 1.dp)
    }
}

@Composable
fun MessageBubble(message: Message) {
    val alignment =
        if (message.isUser()) Alignment.CenterEnd else Alignment.CenterStart
    val backgroundColor =
        if (message.isUser()) UserBubbleColor else BotBubbleColor
    val textColor = if (message.isUser()) Color.White else Color.Black

    // Bo góc tùy biến: bubble của bot và user thường có 1 góc nhọn để tạo đuôi (tail)
    val bubbleShape = if (message.isUser()) {
        RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp, bottomStart = 16.dp, bottomEnd = 4.dp)
    } else {
        RoundedCornerShape(topStart = 4.dp, topEnd = 16.dp, bottomStart = 16.dp, bottomEnd = 16.dp)
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        contentAlignment = alignment
    ) {
        Text(
            text = message.text,
            color = textColor,
            fontSize = 16.sp,
            modifier = Modifier
                .background(color = backgroundColor, shape = bubbleShape)
                .padding(horizontal = 16.dp, vertical = 12.dp)
                .widthIn(max = 280.dp)
        )
    }
}

@Composable
fun TypingIndicator() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        contentAlignment = Alignment.CenterStart
    ) {
        Row(
            modifier = Modifier
                .background(color = BotBubbleColor, shape = RoundedCornerShape(16.dp))
                .padding(horizontal = 16.dp, vertical = 14.dp),
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            repeat(3) {
                Box(
                    modifier = Modifier
                        .size(6.dp)
                        .clip(CircleShape)
                        .background(Color.Gray.copy(alpha = 0.6f))
                )
            }
        }
    }
}

@Composable
fun ChatInputBar(modifier: Modifier) {
    var text by remember { mutableStateOf("") }

    Column(modifier) {
        HorizontalDivider(color = BorderColor, thickness = 1.dp)
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp, end = 6.dp, top = 6.dp, bottom = 6.dp)
                .border(width = 1.dp, color = BorderColor, shape = CircleShape)
                .padding(
                    paddingValues = PaddingValues(
                        start = 20.dp,
                        end = 6.dp,
                        top = 6.dp,
                        bottom = 6.dp
                    )
                ),
            verticalAlignment = Alignment.CenterVertically
        ) {
            BasicTextField(
                value = text,
                onValueChange = { text = it },
                modifier = Modifier.weight(1f),
                textStyle = TextStyle(fontSize = 16.sp, color = Color.Black),
                decorationBox = { innerTextField ->
                    if (text.isEmpty()) {
                        Text(
                            text = "Ví dụ: Ăn trưa 50k, cafe 30k...",
                            color = Color.Gray,
                            fontSize = 16.sp
                        )
                    }
                    innerTextField()
                }
            )

            Spacer(modifier = Modifier.width(8.dp))

            IconButton(
                onClick = {
                    if (text.isNotBlank()) {
                        text = ""
                    }
                },
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(SendButtonColor)
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.Send,
                    contentDescription = "Send",
                    tint = Color.White,
                    modifier = Modifier.size(20.dp)
                )
            }
        }
    }
}