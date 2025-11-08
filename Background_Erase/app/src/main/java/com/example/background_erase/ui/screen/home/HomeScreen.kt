package com.example.background_erase.ui.screen.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.background_erase.R
import com.example.background_erase.ui.WrapDefault



@Preview()
@Composable
fun Show() {
    HomeScreen()
}
@Composable
fun HomeScreen() {
    ConstraintLayout(modifier = Modifier.WrapDefault()) {
        val (iconApp, title) = createRefs()
        Image(
            painter = painterResource(id = R.drawable.icon_app),
            contentDescription = "",
            modifier = Modifier.constrainAs(iconApp) {
                top.linkTo(parent.top)
            }.size(48.dp)
        )
        Text(
            text = "Background Eraser",
            fontSize = 16.sp,
            color = Color.White,
            modifier = Modifier.constrainAs(title) {
                top.linkTo(iconApp.top)
                bottom.linkTo(iconApp.bottom)
                start.linkTo(iconApp.end, margin = 20.dp)
            }
        )
    }
}