package com.example.background_erase.ui.screen.statistical.components

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.ContentTransform
import androidx.compose.animation.SizeTransform
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.KeyboardArrowLeft
import androidx.compose.material.icons.automirrored.rounded.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.background_erase.R
import com.example.background_erase.base.Space
import java.time.YearMonth

@Composable
@Preview
fun C() {
    MonthPicker(
        modifier = Modifier.background(Color.White),
        stateTime = YearMonth.now(),
        onPreviousMonth = {},
        onNextMonth = {}
    )
}

enum class Direction {
    LEFT, RIGHT
}


@Composable
fun MonthPicker(
    modifier: Modifier = Modifier,
    stateTime: YearMonth,
    onPreviousMonth: () -> Unit,
    onNextMonth: () -> Unit
) {
    var direction by remember { mutableStateOf(Direction.LEFT) }

    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        IconButton(
            onClick = {
                direction = Direction.LEFT
                onPreviousMonth()
            },
            modifier = Modifier.size(48.dp),
            colors = IconButtonDefaults.iconButtonColors(
                contentColor = Color(0xFF64748B)
            )
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Rounded.KeyboardArrowLeft,
                contentDescription = "Tháng trước",
                modifier = Modifier.size(28.dp)
            )
        }

        Column(
            modifier = Modifier.weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            AnimatedContent(
                targetState = stateTime,
                transitionSpec = {
                    slideAnimation(this, direction)
                },
                label = "MonthTextAnimation"
            ) { stateTime: YearMonth ->
                Text(
                    text = stringResource(R.string.month) + String.Space + stateTime.monthValue,
                    fontSize = 18.sp, // Chữ tháng to hẳn lên
                    fontWeight = FontWeight.Bold, // Phông siêu đậm tạo sức nặng
                    color = Color(0xFF0F172A), // Màu đen hơi ám xanh nhẹ cực sang
                    letterSpacing = (-0.5).sp
                )
            }
            AnimatedContent(
                targetState = stateTime,
                transitionSpec = {
                    slideAnimation(this, direction)
                },
                label = "YearTextAnimation"
            ) { stateTime: YearMonth ->
                Text(
                    text = "${stateTime.year}",
                    fontSize = 14.sp, // Chữ năm nhỏ lại
                    fontWeight = FontWeight.Medium,
                    color = Color(0xFF64748B), // Màu xám nhạt để lùi về sau
                    letterSpacing = 1.5.sp // Tracking rộng ra tạo sự sang trọng
                )
            }
        }

        IconButton(
            onClick = {
                direction = Direction.RIGHT
                onNextMonth()
            },
            modifier = Modifier.size(48.dp),
            colors = IconButtonDefaults.iconButtonColors(
                contentColor = Color(0xFF64748B)
            )
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Rounded.KeyboardArrowRight,
                contentDescription = "Tháng sau",
                modifier = Modifier.size(28.dp)
            )
        }
    }
}

private fun slideAnimation(
    owner : AnimatedContentTransitionScope<*>,
    direction: Direction
): ContentTransform  {
    return with(owner){
        if (direction == Direction.LEFT) {
            (slideInHorizontally(tween(300)) { width -> width } + fadeIn(tween(300))) togetherWith
                    (slideOutHorizontally(tween(300)) { width -> -width } + fadeOut(
                        tween(300)
                    ))
        } else {
            (slideInHorizontally(tween(300)) { width -> -width } + fadeIn(tween(300))) togetherWith
                    (slideOutHorizontally(tween(300)) { width -> width } + fadeOut(
                        tween(
                            300
                        )
                    ))
        }.using(SizeTransform(clip = false))
    }
}