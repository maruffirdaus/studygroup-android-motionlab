package app.motion.android.ui.about

import androidx.lifecycle.ViewModel
import app.motion.android.data.repository.AuthRepository

class AboutViewModel(
    private val authRepository: AuthRepository
) : ViewModel() {
    fun logout() {
        authRepository.logout()
    }
}