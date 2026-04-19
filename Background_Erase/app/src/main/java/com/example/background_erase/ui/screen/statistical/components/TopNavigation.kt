package com.example.background_erase.ui.screen.statistical.components


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.background_erase.ui.screen.statistical.RowDefaults

@Composable
fun TopNavigation(
    modifier: Modifier,
    onLeftBtnClick: () -> Unit
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = onLeftBtnClick, modifier = Modifier
            .background(Color.White, CircleShape)
            .size(40.dp)) {
            Icon(Icons.Default.Close, contentDescription = null)
        }
        Text("Thống kê", fontSize = 18.sp, fontWeight = FontWeight.Bold)
        Surface(shape = RoundedCornerShape(16.dp), color = Color.White, border = RowDefaults.border) {
            Text("VN VND", modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp), fontSize = 12.sp)
        }
    }
}