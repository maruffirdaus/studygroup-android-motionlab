package app.motion.android.ui.schedule

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import app.motion.android.common.model.Schedule
import app.motion.android.common.model.Subject

@Composable
@Preview
fun ScheduleScreen() {
    val schedules: MutableList<Schedule> = remember {
        mutableStateListOf(
            Schedule(
                subject = Subject(
                    name = "Jetpack Compose Part I",
                    teacher = "Ma\'ruf"
                ),
                day = "Monday",
                time = "18.30 - 20.30"
            ),
            Schedule(
                subject = Subject(
                    name = "Jetpack Compose Part II",
                    teacher = "Fatih"
                ),
                day = "Tuesday",
                time = "18.30 - 20.30"
            ),
            Schedule(
                subject = Subject(
                    name = "Firebase Part I",
                    teacher = "Genta"
                ),
                day = "Wednesday",
                time = "18.30 - 20.30"
            )
        )
    }
    var isDialogOpen by remember { mutableStateOf(false) }

    if (isDialogOpen) {
        AddScheduleDialog(
            onSave = {
                schedules.add(0, it)
                isDialogOpen = false
            },
            onDismissRequest = {
                isDialogOpen = false
            }
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .statusBarsPadding()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Schedule",
                fontSize = 32.sp,
                fontWeight = FontWeight.ExtraBold
            )
            Button(
                onClick = {
                    isDialogOpen = true
                }
            ) {
                Text("Add")
            }
        }
        HorizontalDivider()
        LazyColumn(
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(schedules) { schedule ->
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(24.dp))
                        .background(Color.LightGray)
                        .padding(24.dp)
                ) {
                    Text("Subject name")
                    Text(
                        text = schedule.subject.name,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(Modifier.height(8.dp))
                    Text("Subject teacher")
                    Text(
                        text = schedule.subject.teacher,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(Modifier.height(8.dp))
                    Text("Day")
                    Text(
                        text = schedule.day,
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
                            schedules.remove(schedule)
                        },
                        modifier = Modifier.align(Alignment.End)
                    ) {
                        Text("Delete")
                    }
                }
            }
        }
    }
}