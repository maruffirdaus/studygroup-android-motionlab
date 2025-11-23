package app.motion.android.ui.main

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import app.motion.android.ui.about.AboutScreen
import app.motion.android.ui.schedule.ScheduleScreen
import app.motion.android.ui.theme.MotionAppTheme
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun MainScreen(
    username: String,
    navController: NavHostController
) {
    val viewModel: MainViewModel = koinViewModel()
    val uiState by viewModel.uiState.collectAsState()

    MainScreenContent(
        username = username,
        uiState = uiState,
        onSelectedNavItemChange = viewModel::changeSelectedNavItem,
        scheduleScreen = { username, innerPadding ->
            ScheduleScreen(
                username = username,
                innerPadding = innerPadding
            )
        },
        aboutScreen = { navController, innerPadding ->
            AboutScreen(
                navController = navController,
                innerPadding = innerPadding
            )
        },
        navController = navController
    )
}

@Composable
fun MainScreenContent(
    username: String,
    uiState: MainUiState,
    onSelectedNavItemChange: (NavItem) -> Unit,
    scheduleScreen: @Composable (String, PaddingValues) -> Unit,
    aboutScreen: @Composable (NavHostController, PaddingValues) -> Unit,
    navController: NavHostController
) {
    // Scaffold digunakan untuk menyusun layout berdasarkan Material Design
    // Scaffold tidak wajib digunakan, terlebih ketika mengimplementasikan custom design system
    // Untuk menyusun NavigationBar tanpa Scaffold, dapat menggunakan Column
    Scaffold(
        bottomBar = {
            // NavigationBar bawaan Material Design, untuk membuat versi custom dapat menggunakan Row
            NavigationBar {
                NavItem.entries.forEach { item ->
                    val isSelected = uiState.selectedNavItem == item

                    NavigationBarItem(
                        selected = isSelected,
                        onClick = {
                            onSelectedNavItemChange(item)
                        },
                        icon = {
                            Icon(
                                painter = if (isSelected) {
                                    painterResource(item.selectedIcon)
                                } else {
                                    painterResource(item.unselectedIcon)
                                },
                                contentDescription = item.label
                            )
                        },
                        label = {
                            Text(item.label)
                        }
                    )
                }
            }
        }
    ) { innerPadding ->
        when (uiState.selectedNavItem) {
            NavItem.SCHEDULE -> scheduleScreen(username, innerPadding)
            NavItem.ABOUT -> aboutScreen(navController, innerPadding)
        }
    }
}

@Composable
@Preview
private fun MainScreenPreview() {
    MotionAppTheme {
        MainScreenContent(
            username = "Motion",
            uiState = MainUiState(
                selectedNavItem = NavItem.ABOUT
            ),
            onSelectedNavItemChange = {},
            scheduleScreen = { _, _ -> },
            aboutScreen = { navController, innerPadding ->
                AboutScreen(
                    navController = navController,
                    innerPadding = innerPadding
                )
            },
            navController = rememberNavController()
        )
    }
}