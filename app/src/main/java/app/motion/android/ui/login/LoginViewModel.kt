package app.motion.android.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.motion.android.data.repository.AuthRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LoginViewModel(
    private val authRepository: AuthRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState = _uiState.asStateFlow()

    fun changeUsername(username: String) {
        _uiState.update { state ->
            state.copy(username = username)
        }
    }

    fun changePassword(password: String) {
        _uiState.update { state ->
            state.copy(password = password)
        }
    }

    fun togglePasswordVisibility() {
        _uiState.update { state ->
            state.copy(isPasswordVisible = !state.isPasswordVisible)
        }
    }

    fun login(onSuccess: () -> Unit) {
        viewModelScope.launch {
            _uiState.update { state ->
                state.copy(isLoading = true)
            }
            try {
                authRepository.login(uiState.value.username, uiState.value.password)
                onSuccess()
            } catch (e: Exception) {
                _uiState.update { state ->
                    state.copy(errorMessage = e.message)
                }
            } finally {
                _uiState.update { state ->
                    state.copy(isLoading = false)
                }
            }
        }
    }

    fun register(onSuccess: () -> Unit) {
        viewModelScope.launch {
            _uiState.update { state ->
                state.copy(isLoading = true)
            }
            try {
                authRepository.register(uiState.value.username, uiState.value.password)
                onSuccess()
            } catch (e: Exception) {
                _uiState.update { state ->
                    state.copy(errorMessage = e.message)
                }
            } finally {
                _uiState.update { state ->
                    state.copy(isLoading = false)
                }
            }
        }
    }

    fun getCurrentUserEmail(): String? {
        return authRepository.getCurrentUserEmail()
    }

    fun clearErrorMessage() {
        _uiState.update { state ->
            state.copy(errorMessage = null)
        }
    }
}