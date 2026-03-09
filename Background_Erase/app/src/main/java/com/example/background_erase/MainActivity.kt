package com.example.background_erase

import ChatHomeScreen
import android.app.Activity
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.background_erase.nav.Routers
import com.example.background_erase.ui.screen.OnboardingScreen
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { MainContent() }
    }
}
@Composable
fun SetSystemBar() {
    val view = LocalView.current
    SideEffect {
        val window = (view.context as Activity).window
        window.statusBarColor = Color.White.toArgb()

        WindowCompat.getInsetsController(window, view)
            .isAppearanceLightStatusBars = true // icon tối
    }
}
@Composable
fun MainContent() {
    SetSystemBar()
    Surface(color = MaterialTheme.colorScheme.background) {
        val nav = rememberNavController()
        NavHost(
            navController = nav,
            startDestination = Routers.ChatHomeScreen
        ) {
            composable<Routers.Onboarding>(
            ) {
                OnboardingScreen(
                    onStartClick = {
                        Log.d("LOG_TAG", "MainContent: Click")
                        nav.navigate(Routers.ChatHomeScreen)
                    },
                    onPrivacyPolicyClick = { }
                )
            }
            composable<Routers.ChatHomeScreen> { navBackStackEntry ->
                val route = navBackStackEntry.toRoute<Routers.ChatHomeScreen>()
                ChatHomeScreen()
            }
        }
    }
}
