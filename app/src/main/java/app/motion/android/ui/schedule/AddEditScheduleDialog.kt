package app.motion.android.ui.schedule

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import app.motion.android.common.model.Lesson
import app.motion.android.common.model.Schedule
import app.motion.android.ui.theme.MotionAppTheme

@Composable
fun AddEditScheduleDialog(
    onAdd: (Schedule) -> Unit,
    onEdit: (Schedule) -> Unit,
    onDismissRequest: () -> Unit,
    schedule: Schedule? = null
) {
    val isEdit = schedule != null

    Dialog(
        onDismissRequest = onDismissRequest
    ) {
        Column(
            modifier = Modifier
                .clip(RoundedCornerShape(24.dp))
                .background(Color.White)
                .padding(24.dp)
        ) {
            var lessonName by remember { mutableStateOf(schedule?.lesson?.name ?: "") }
            var lessonMentor by remember { mutableStateOf(schedule?.lesson?.mentor ?: "") }
            var date by remember { mutableStateOf(schedule?.date ?: "") }
            var time by remember { mutableStateOf(schedule?.time ?: "") }

            Text(
                text = if (isEdit) {
                    "Edit schedule"
                } else {
                    "Add schedule"
                },
                fontSize = 24.sp,
                fontWeight = FontWeight.ExtraBold
            )
            Spacer(Modifier.height(16.dp))
            OutlinedTextField(
                value = lessonName,
                onValueChange = { value ->
                    lessonName = value
                },
                label = {
                    Text("Lesson name")
                },
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = lessonMentor,
                onValueChange = { value ->
                    lessonMentor = value
                },
                label = {
                    Text("Lesson mentor")
                },
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = date,
                onValueChange = { value ->
                    date = value
                },
                label = {
                    Text("Date")
                },
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = time,
                onValueChange = { value ->
                    time = value
                },
                label = {
                    Text("Time")
                },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(Modifier.height(24.dp))
            Button(
                onClick = {
                    val newSchedule = Schedule(
                        id = if (isEdit) schedule.id else 0,
                        lesson = Lesson(
                            name = lessonName,
                            mentor = lessonMentor
                        ),
                        date = date,
                        time = time
                    )
                    if (isEdit) {
                        onEdit(newSchedule)
                    } else {
                        onAdd(newSchedule)
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Save")
            }
        }
    }
}

@Composable
@Preview
private fun AddEditScheduleDialogPreview() {
    MotionAppTheme {
        AddEditScheduleDialog(
            onAdd = {},
            onEdit = {},
            onDismissRequest = {}
        )
    }
}