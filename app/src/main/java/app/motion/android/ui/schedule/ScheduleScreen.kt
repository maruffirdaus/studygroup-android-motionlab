package app.motion.android.ui.schedule

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import app.motion.android.ui.theme.MotionAppTheme
import kotlinx.coroutines.launch
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun ScheduleScreen(
    username: String,
    innerPadding: PaddingValues = PaddingValues()
) {
    val viewModel: ScheduleViewModel = koinViewModel()
    val uiState by viewModel.uiState.collectAsState()

    val scope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        viewModel.refreshSchedules()
    }

    if (uiState.isDialogOpen) {
        AddEditScheduleDialog(
            onAdd = { newSchedule ->
                viewModel.addSchedule(newSchedule)
                viewModel.closeDialog()
            },
            onEdit = { newSchedule ->
                viewModel.closeDialog()
                viewModel.editSchedule(newSchedule)
            },
            onDismissRequest = {
                viewModel.closeDialog()
            },
            schedule = uiState.scheduleToEdit
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(innerPadding)
    ) {
        val lazyListState = rememberLazyListState()

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "$username's Schedule",
                fontSize = 32.sp,
                fontWeight = FontWeight.ExtraBold
            )
            Button(
                onClick = {
                    viewModel.openDialog()
                }
            ) {
                Text("Add")
            }
        }
        HorizontalDivider()
        LazyColumn(
            state = lazyListState,
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            items(uiState.schedules) { schedule ->
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(24.dp))
                        .background(Color.LightGray)
                        .clickable {
                            viewModel.openDialog(schedule)
                        }
                        .padding(24.dp)
                ) {
                    Text("Lesson name")
                    Text(
                        text = schedule.lesson.name,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(Modifier.height(8.dp))
                    Text("Lesson mentor")
                    Text(
                        text = schedule.lesson.mentor,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(Modifier.height(8.dp))
                    Text("Date")
                    Text(
                        text = schedule.date,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(Modifier.height(8.dp))
                    Text("Time")
                    Text(
                        text = schedule.time,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(Modifier.height(24.dp))
                    Button(
                        onClick = {
                            viewModel.deleteSchedule(schedule.id)
                        },
                        modifier = Modifier.align(Alignment.End)
                    ) {
                        Text("Delete")
                    }
                }
            }
            item {
                TextButton(
                    onClick = {
                        scope.launch {
                            lazyListState.animateScrollToItem(0)
                        }
                    }
                ) {
                    Text("Back to top")
                }
            }
        }
    }
}

@Composable
@Preview
private fun ScheduleScreenPreview() {
    MotionAppTheme {
        ScheduleScreen(
            username = "Motion"
        )
    }
}