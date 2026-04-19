package com.example.background_erase.ui.screen.statistical.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.background_erase.model.FinanceStatisticUiState
import kotlin.math.cos
import kotlin.math.sin

fun FinanceStatisticUiState.exportChartData() : List<ChartItem> {
    return displayCategories.map {
        ChartItem(
            title = it.category.name,
            value = it.totalAmount.toFloat(),
            color = Color(it.iconBg)
        )
    }
}
data class ChartItem(
    val title: String,
    val value: Float,
    val color: Color
)

@Preview
@Composable
fun A() {
    val mockData = listOf(
        ChartItem("Tiền nhà", 2560000f, Color(0xFFFF85BC)),
        ChartItem("Tiền ăn", 1370000f, Color(0xFFFB923C)),
        ChartItem("Y tế", 4510000f, Color(0xFF6EE7B7)),
        ChartItem("Khác", 1060000f, Color(0xFF90A4AE)),
        ChartItem("Khác", 1560000f, Color(0xFF2A98CE)),
        ChartItem("Khác", 1260000f, Color(0xFF90A4AE)),
        ChartItem("Khác", 2560000f, Color(0xFF2197D2)),
        ChartItem("Khác", 1060000f, Color(0xFF2DAA89)),
    )

    PureComposeDonutChart(data = mockData)
}

// 2. Composable vẽ biểu đồ
@Composable
fun PureComposeDonutChart(
    modifier: Modifier = Modifier,
    data: List<ChartItem>
) {
    DonutChart(modifier, data)
}

@Composable
private fun DonutChart(
    modifier: Modifier,
    data: List<ChartItem>
) {
    // Trình đo đạc text thuần Compose
    val textMeasurer = rememberTextMeasurer()

    Box(
        modifier = modifier
            .fillMaxWidth()
            .aspectRatio(1.2f)
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            val totalValue = data.sumOf { it.value.toDouble() }.toFloat()
            if (totalValue == 0f) return@Canvas

            // Thông số kích thước biểu đồ
            val donutRadius = size.minDimension / 3.5f
            val strokeWidth = 36.dp.toPx()
            val center = Offset(size.width / 2, size.height / 2)
            val gapDegree = 2f // Khe hở giữa các mảng màu

            // Thông số đường nối (Callout line)
            val lineLength1 = 15.dp.toPx() // Đoạn kéo thẳng ra từ vòng tròn
            val lineLength2 = 25.dp.toPx() // Đoạn gập ngang

            var currentStartAngle = -90f // Bắt đầu vẽ từ góc 12h

            data.forEach { item ->
                // Tính phần trăm và góc vẽ
                val percentage = item.value / totalValue
                val sweepAngle = percentage * 360f
                val percentText = String.format("%.1f%%", percentage * 100)

                // 1. VẼ MẢNG MÀU (DONUT SLICE)
                // Trừ đi gapDegree để tạo khe hở trắng giữa các mảng
                val actualSweepAngle = sweepAngle - gapDegree

                drawArc(
                    color = item.color,
                    startAngle = currentStartAngle + (gapDegree / 2F),
                    sweepAngle = actualSweepAngle,
                    useCenter = false,
                    style = Stroke(width = strokeWidth, cap = StrokeCap.Butt),
                    topLeft = Offset(center.x - donutRadius, center.y - donutRadius),
                    size = Size(donutRadius * 2, donutRadius * 2)
                )

                // 2. TÍNH TỌA ĐỘ ĐƯỜNG KẺ (Toán lượng giác)
                // Lấy góc ở giữa mảng màu để chĩa đường kẻ ra
                val midAngle = currentStartAngle + (sweepAngle / 2)
                val midAngleRad = Math.toRadians(midAngle.toDouble())

                // Điểm 1: Nằm trên mép ngoài của vòng tròn
                val startX = center.x + (donutRadius + strokeWidth / 2) * cos(midAngleRad).toFloat()
                val startY = center.y + (donutRadius + strokeWidth / 2) * sin(midAngleRad).toFloat()

                // Điểm 2: Gập khúc (kéo xiên ra ngoài một chút)
                val elbowX =
                    center.x + (donutRadius + strokeWidth / 2 + lineLength1) * cos(midAngleRad).toFloat()
                val elbowY =
                    center.y + (donutRadius + strokeWidth / 2 + lineLength1) * sin(midAngleRad).toFloat()

                // Điểm 3: Kéo ngang sang trái hoặc phải
                val isRightSide = cos(midAngleRad) >= 0 // Cung nằm nửa bên phải hay trái
                val endX = if (isRightSide) elbowX + lineLength2 else elbowX - lineLength2
                val endY = elbowY

                // Vẽ đường kẻ khúc khuỷu
                val path = Path().apply {
                    moveTo(startX, startY)
                    lineTo(elbowX, elbowY)
                    lineTo(endX, endY)
                }
                drawPath(
                    path = path,
                    color = Color.LightGray,
                    style = Stroke(width = 1.5.dp.toPx())
                )

                // 3. VẼ TEXT LÊN ĐẦU ĐƯỜNG KẺ
                // Đo kích thước text trước khi vẽ để căn lề cho chuẩn
                val percentLayoutResult = textMeasurer.measure(
                    text = percentText,
                    style = TextStyle(
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF1F2937)
                    )
                )
                val titleLayoutResult = textMeasurer.measure(
                    text = item.title,
                    style = TextStyle(fontSize = 12.sp, color = Color(0xFF6B7280))
                )

                // Tính toán tọa độ X để chữ không bị đè lên đường kẻ
                val paddingText = 6.dp.toPx()
                val textXPercent =
                    if (isRightSide) endX + paddingText else endX - paddingText - percentLayoutResult.size.width
                val textXTitle =
                    if (isRightSide) endX + paddingText else endX - paddingText - titleLayoutResult.size.width

                // Vẽ phần trăm (Nằm trên đường kẻ)
                drawText(
                    textLayoutResult = percentLayoutResult,
                    topLeft = Offset(textXPercent, endY - percentLayoutResult.size.height)
                )

                // Vẽ tên danh mục (Nằm dưới đường kẻ)
                drawText(
                    textLayoutResult = titleLayoutResult,
                    topLeft = Offset(textXTitle, endY + 2.dp.toPx())
                )

                // Cộng dồn góc cho mảng tiếp theo
                currentStartAngle += sweepAngle
            }
        }
    }
}