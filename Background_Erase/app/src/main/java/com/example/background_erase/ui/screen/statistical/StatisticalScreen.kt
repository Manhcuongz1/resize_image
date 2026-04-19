package com.example.background_erase.ui.screen.statistical


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.example.background_erase.model.CategoryGroupUI
import com.example.background_erase.model.FinanceStatisticUiState
import com.example.background_erase.model.TransactionUI
import com.example.background_erase.ui.screen.statistical.components.CategoryRow
import com.example.background_erase.ui.screen.statistical.components.PureComposeDonutChart
import com.example.background_erase.ui.screen.statistical.components.SegmentedTab
import com.example.background_erase.ui.screen.statistical.components.TopNavigation
import com.example.background_erase.ui.screen.statistical.components.exportChartData
import org.instancio.Instancio
import java.math.BigDecimal

// --- THEME COLORS ---
val BgGray = Color(0xFFF7F8FA)
val TextPrimary = Color(0xFF1D1F24)
val TextSecondary = Color(0xFF8B92A0)
val TabBgGray = Color(0xFFE8EAF1)

@Preview
@Composable
fun Pre() {
    val red = 0xFFFF0000
    val white = 0xFFFFFFFF
    val blue = 0xFF0000FF
    val darkGray = 0xFF444444
    val displayCategories = listOf(
        Instancio.create(CategoryGroupUI::class.java).copy(
            iconChar = "A",
            iconBg = red,
            iconColor = white,
            totalAmount = BigDecimal(45000)
        ),
        Instancio.create(CategoryGroupUI::class.java).copy(
            iconChar = "A",
            iconBg = blue,
            iconColor = darkGray,
            totalAmount = BigDecimal(60000)
        )
    )
    val mock = Instancio.create(FinanceStatisticUiState::class.java).copy(
        displayCategories = displayCategories,
        typeCurrency = "đ"
    )
    ScreenContent(mock,{},{})
}

@Composable
fun FinanceStatisticScreen(
    viewModel: StatisticalViewModel = hiltViewModel(),
    onBackScreen : () -> Unit,
) {
    val analytic by viewModel.analytic.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.fetchStatisticData()
    }

    ScreenContent(
        analytic = analytic,
        onTabSegmentSelected = viewModel::onTabSelected,
        onBackScreen = onBackScreen
    )
}

@Composable
private fun ScreenContent(
    analytic: FinanceStatisticUiState,
    onTabSegmentSelected: (TransactionUI.Type) -> Unit,
    onBackScreen: () -> Unit
) {

    val listState = rememberLazyListState()
    // Tính toán thu nhỏ font chữ dựa trên scroll
    // Khi cuộn được 100px thì font chữ sẽ đạt mức nhỏ nhất (16sp)
    val scrollOffset = remember { derivedStateOf { listState.firstVisibleItemScrollOffset } }
    val firstIndex = remember { derivedStateOf { listState.firstVisibleItemIndex } }
    val fontSize = if (firstIndex.value > 0) 16.sp else {
        val coercedOffset = scrollOffset.value.coerceIn(0, 200)
        (32 - (coercedOffset / 200f) * 16).sp
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BgGray)
    ) {
        TopNavigation(
            Modifier
                .fillMaxWidth()
                .padding(top = 8.dp, bottom = 16.dp, start = 24.dp, end = 24.dp),
            onBackScreen
        )
        SegmentedTab(
            Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp)
                .height(48.dp)
                .background(TabBgGray, RoundedCornerShape(16.dp))
                .padding(4.dp),
            tab = analytic.selectedTab
        ) { onTabSegmentSelected.invoke(it) }
        Spacer(modifier = Modifier.height(16.dp))
        ContentStatistical(
            modifier = Modifier.fillMaxSize(),
            listState,
            fontSize,
            analytic,
        )
    }
}


@Composable
private fun ContentStatistical(
    modifier: Modifier,
    listState: LazyListState,
    fontSize: TextUnit,
    analytic : FinanceStatisticUiState,
) {
    LazyColumn(
        modifier = modifier,
        state = listState,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // 1. Cụm Tổng tiền (Sẽ thu nhỏ khi cuộn)
        item {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(top = 16.dp)
            ) {
                Text(
                    text = stringResource(analytic.getTitleRes()),
                    color = TextSecondary,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Medium
                )
                Text(
                    text = buildAnnotatedString {
                        append(analytic.getTotalBalanceFormat())
                        withStyle(style = SpanStyle(textDecoration = TextDecoration.Underline)) {
                            append(
                                analytic.typeCurrency
                            )
                        }
                    },
                    color = TextPrimary,
                    fontSize = fontSize, // Biến font size động
                    fontWeight = FontWeight.Bold
                )
            }
        }

        // 2. Cụm Chart (Sẽ bị kéo lên cùng list)
        item {
            PureComposeDonutChart(
                Modifier.padding(horizontal = 36.dp),
                analytic.exportChartData()
            )
        }

        // 3. Header của Danh mục (Bắt đầu nền trắng bo góc)
        item {
            Surface(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp),
                color = Color.White
            ) {
                Column(
                    modifier = Modifier.padding(
                        start = 24.dp,
                        end = 24.dp,
                        top = 24.dp,
                        bottom = 8.dp
                    )
                ) {
                    // Thanh gạch ngang trang trí
                    Box(
                        modifier = Modifier
                            .width(40.dp)
                            .height(4.dp)
                            .background(Color(0xFFE0E0E0), CircleShape)
                            .align(Alignment.CenterHorizontally)
                    )
                    Spacer(modifier = Modifier.height(24.dp))
                    Text(
                        text = "Danh mục",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = TextPrimary
                    )
                }
            }
        }

        // 4. Danh sách các Category (Nằm trên nền trắng)
        items(analytic.displayCategories) { item ->
            Surface(
                color = Color.White,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)
                    .padding(horizontal = 24.dp)
            ) {
                CategoryRow(modifier = Modifier.fillMaxWidth(), item)
            }
        }

        // 5. Padding cuối list
        item {
            Spacer(modifier = Modifier
                .height(100.dp)
                .fillMaxWidth()
                .background(Color.White))
        }
    }
}
object RowDefaults { val border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFFEBEBEB)) }