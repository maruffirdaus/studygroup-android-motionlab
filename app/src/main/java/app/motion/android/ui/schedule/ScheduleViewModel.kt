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
        viewModelScope.launch {
            _uiState.update { state ->
                state.copy(isLoading = true)
            }
            _uiState.update { state ->
                state.copy(schedules = repository.getSchedules())
            }
            _uiState.update { state ->
                state.copy(isLoading = false)
            }
        }
    }

    fun addSchedule(schedule: Schedule) {
        viewModelScope.launch {
            _uiState.update { state ->
                state.copy(isLoading = true)
            }
            repository.addSchedule(schedule)
            _uiState.update { state ->
                state.copy(schedules = repository.getSchedules())
            }
            _uiState.update { state ->
                state.copy(isLoading = false)
            }
        }
    }

    fun editSchedule(schedule: Schedule) {
        viewModelScope.launch {
            _uiState.update { state ->
                state.copy(isLoading = true)
            }
            repository.editSchedule(schedule)
            _uiState.update { state ->
                state.copy(schedules = repository.getSchedules())
            }
            _uiState.update { state ->
                state.copy(isLoading = false)
            }
        }
    }

    fun deleteSchedule(id: String) {
        viewModelScope.launch {
            _uiState.update { state ->
                state.copy(isLoading = true)
            }
            repository.deleteSchedule(id)
            _uiState.update { state ->
                state.copy(schedules = repository.getSchedules())
            }
            _uiState.update { state ->
                state.copy(isLoading = false)
            }
        }
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