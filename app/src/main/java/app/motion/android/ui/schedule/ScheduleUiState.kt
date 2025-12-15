package app.motion.android.ui.schedule

import app.motion.android.common.model.Schedule

data class ScheduleUiState(
    val schedules: List<Schedule> = listOf(),
    val scheduleToEdit: Schedule? = null,
    val isDialogOpen: Boolean = false,
    val isLoading: Boolean = false
)
