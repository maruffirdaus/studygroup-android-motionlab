package app.motion.android.ui.schedule

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.motion.android.common.model.Schedule
import app.motion.android.data.repository.ScheduleRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ScheduleViewModel(
    private val repository: ScheduleRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(ScheduleUiState())
    val uiState = _uiState.asStateFlow()

    fun refreshSchedules() {
        _uiState.update { state ->
            state.copy(schedules = repository.getSchedules())
        }
    }

    fun addSchedule(schedule: Schedule) {
        viewModelScope.launch {
            repository.addSchedule(schedule)
            refreshSchedules()
        }
    }

    fun editSchedule(schedule: Schedule) {
        repository.editSchedule(schedule)
        refreshSchedules()
    }

    fun deleteSchedule(id: String) {
        repository.deleteSchedule(id)
        refreshSchedules()
    }

    fun openDialog(scheduleToEdit: Schedule? = null) {
        _uiState.update { state ->
            state.copy(
                scheduleToEdit = scheduleToEdit,
                isDialogOpen = true
            )
        }
    }

    fun closeDialog() {
        _uiState.update { state ->
            state.copy(
                scheduleToEdit = null,
                isDialogOpen = false
            )
        }
    }
}