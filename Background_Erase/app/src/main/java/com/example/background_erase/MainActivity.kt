package com.example.background_erase

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.background_erase.nav.Routers
import com.example.background_erase.ui.screen.home.HomeScreen
import com.example.background_erase.ui.screen.OnboardingScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.light(
                android.graphics.Color.TRANSPARENT,   // màu nền status bar
                android.graphics.Color.TRANSPARENT    // scrim cho chế độ light
            ),
            navigationBarStyle = SystemBarStyle.light(
                android.graphics.Color.TRANSPARENT,
                android.graphics.Color.TRANSPARENT
            )
        )
        setContent { MainContent() }
    }
}

@Composable
fun MainContent() {
    SetSystemBar()
    Surface(color = MaterialTheme.colorScheme.background) {
        val nav = rememberNavController()
        NavHost(
            navController = nav,
            startDestination = Routers.Onboarding
        ) {
            composable<Routers.Onboarding>(
            ) {
                OnboardingScreen(
                    onStartClick = {
                        Log.d("LOG_TAG", "MainContent: Click")
                        nav.navigate(Routers.Home)
                    },
                    onPrivacyPolicyClick = { }
                )
            }
            composable<Routers.Home> { navBackStackEntry ->
                val route = navBackStackEntry.toRoute<Routers.Home>()
                HomeScreen()
            }
        }
    }
}

@Composable
fun SetSystemBar() {

}
