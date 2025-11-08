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
import app.motion.android.common.model.Schedule
import app.motion.android.common.model.Subject
import app.motion.android.ui.theme.MotionAppTheme

@Composable
fun AddScheduleDialog(
    onSave: (Schedule) -> Unit,
    onDismissRequest: () -> Unit
) {
    Dialog(
        onDismissRequest = onDismissRequest
    ) {
        Column(
            modifier = Modifier
                .clip(RoundedCornerShape(24.dp))
                .background(Color.White)
                .padding(24.dp)
        ) {
            var subjectName by remember { mutableStateOf("") }
            var subjectTeacher by remember { mutableStateOf("") }
            var day by remember { mutableStateOf("") }
            var time by remember { mutableStateOf("") }

            Text(
                text = "Add schedule",
                fontSize = 24.sp,
                fontWeight = FontWeight.ExtraBold
            )
            Spacer(Modifier.height(16.dp))
            OutlinedTextField(
                value = subjectName,
                onValueChange = { value ->
                    subjectName = value
                },
                label = {
                    Text("Subject name")
                },
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = subjectTeacher,
                onValueChange = { value ->
                    subjectTeacher = value
                },
                label = {
                    Text("Subject teacher")
                },
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = day,
                onValueChange = { value ->
                    day = value
                },
                label = {
                    Text("Day")
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
                    val schedule = Schedule(
                        subject = Subject(
                            name = subjectName,
                            teacher = subjectTeacher
                        ),
                        day = day,
                        time = time
                    )
                    onSave(schedule)
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
private fun AddScheduleDialogPreview() {
    MotionAppTheme {
        AddScheduleDialog(
            onSave = {},
            onDismissRequest = {}
        )
    }
}