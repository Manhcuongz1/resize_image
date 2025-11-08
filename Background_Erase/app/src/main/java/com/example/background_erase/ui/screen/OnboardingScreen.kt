package com.example.background_erase.ui.screen

import android.text.method.LinkMovementMethod
import android.widget.TextView
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.core.text.HtmlCompat
import com.example.background_erase.R
import com.example.background_erase.ui.WrapDefault
import com.example.background_erase.ui.resource.Accent_0xFF4E74FF
import com.example.background_erase.ui.resource.Black_0xFF0F0F10
import com.example.background_erase.ui.resource.White_0xFFEAE5DE

@Preview
@Composable
fun Preview() {
    OnboardingScreen(onStartClick = {}, onPrivacyPolicyClick = {})
}

@Composable
fun OnboardingScreen(
    onStartClick: () -> Unit,
    onPrivacyPolicyClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val bg = Black_0xFF0F0F10
    val titleColor = White_0xFFEAE5DE
    val accent = Accent_0xFF4E74FF

    ConstraintLayout(
       modifier=  modifier.WrapDefault()
    ) {
        val (t1, t2, sub, btnStart, privacy) = createRefs()
        Text(
            text = stringResource(R.string.background_eraser_offline),
            color = titleColor,
            textAlign = TextAlign.Center,
            fontSize = 32.sp,
            modifier = Modifier
                .constrainAs(t1) {
                    top.linkTo(parent.top, margin = 30.dp)
                    end.linkTo(parent.end)
                    start.linkTo(parent.start)
                    width = Dimension.fillToConstraints
                }
                .padding(top = 16.dp)
        )
        AndroidView(
            factory = { context ->
                TextView(context).apply {
                    text = HtmlCompat.fromHtml(
                        """
<text>オンラインで完結、月額もシンプル。LINEMOで軽やかに乗り換え。</text>,  \n<a href=\"https://www.linemo.jp/\" target=\"_blank\" rel=\"noopener noreferrer\">詳しく見る</a>
                """.trimIndent(),
                        HtmlCompat.FROM_HTML_MODE_COMPACT
                    )
                    movementMethod = LinkMovementMethod.getInstance()
                    textAlignment = TextView.TEXT_ALIGNMENT_CENTER
                    textSize = 20f
                }
            },
            modifier = Modifier.constrainAs(t2) {
                top.linkTo(t1.bottom, margin = 20.dp)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                width = Dimension.fillToConstraints
            }
        )


        Button(
            onClick = onStartClick,
            colors = ButtonDefaults.buttonColors(
                containerColor = accent,
                contentColor = Color.White
            ),
            modifier = Modifier.constrainAs(btnStart) {
                bottom.linkTo(privacy.top, margin = 15.dp)
                end.linkTo(parent.end, margin = 40.dp)
                start.linkTo(parent.start, margin = 40.dp)
                width = Dimension.fillToConstraints
            }
        ) {
            Text(
                text = stringResource(R.string.start),
                textAlign = TextAlign.Center,
                fontSize = 30.sp
            )
        }
        Text(
            text = stringResource(R.string.privacy_policy),
            color = titleColor,
            textAlign = TextAlign.Center,
            fontSize = 16.sp,
            modifier = modifier.constrainAs(privacy) {
                bottom.linkTo(parent.bottom, margin = 40.dp)
                start.linkTo(btnStart.start)
                end.linkTo(btnStart.end)
            }
        )
    }
}