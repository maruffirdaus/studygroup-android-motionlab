package app.motion.android.ui

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import app.motion.android.ui.login.LoginScreen
import app.motion.android.ui.main.MainScreen

@Composable
fun AppNavHost() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Login
    ) {
        composable<Login> {
            LoginScreen(
                navController = navController
            )
        }

        composable<Main> { backStackEntry ->
            val args: Main = backStackEntry.toRoute()

            MainScreen(
                username = args.username,
                navController = navController
            )
        }
    }
}