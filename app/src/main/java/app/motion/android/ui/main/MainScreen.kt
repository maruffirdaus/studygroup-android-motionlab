package app.motion.android.ui.main

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import app.motion.android.R
import app.motion.android.ui.about.AboutScreen
import app.motion.android.ui.schedule.ScheduleScreen
import app.motion.android.ui.theme.MotionAppTheme

@Composable
fun MainScreen(
    username: String,
    navController: NavHostController
) {
    val items = listOf("Schedule", "About")
    val selectedIcons = listOf(R.drawable.ic_calendar_month_filled, R.drawable.ic_info_filled)
    val unselectedIcons = listOf(R.drawable.ic_calendar_month, R.drawable.ic_info)
    var selectedItem by remember { mutableIntStateOf(0) }

    // Scaffold digunakan untuk menyusun layout berdasarkan Material Design
    // Scaffold tidak wajib digunakan, terlebih ketika mengimplementasikan custom design system
    // Untuk menyusun NavigationBar tanpa Scaffold, dapat menggunakan Column
    Scaffold(
        bottomBar = {
            // NavigationBar bawaan Material Design, untuk membuat versi custom dapat menggunakan Row
            NavigationBar {
                items.forEachIndexed { index, item ->
                    NavigationBarItem(
                        selected = selectedItem == index,
                        onClick = {
                            selectedItem = index
                        },
                        icon = {
                            Icon(
                                painter = if (selectedItem == index) {
                                    painterResource(selectedIcons[index])
                                } else {
                                    painterResource(unselectedIcons[index])
                                },
                                contentDescription = item
                            )
                        },
                        label = {
                            Text(item)
                        }
                    )
                }
            }
        }
    ) { innerPadding ->
        when (selectedItem) {
            0 -> ScheduleScreen(
                username = username,
                innerPadding = innerPadding
            )

            1 -> AboutScreen(
                navController = navController,
                innerPadding = innerPadding
            )
        }
    }
}

@Composable
@Preview
private fun MainScreenPreview() {
    MotionAppTheme {
        MainScreen(
            username = "Motion",
            navController = rememberNavController()
        )
    }
}