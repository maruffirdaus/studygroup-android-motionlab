package app.motion.android.di

import app.motion.android.ui.login.LoginViewModel
import app.motion.android.ui.main.MainViewModel
import app.motion.android.ui.schedule.ScheduleViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { LoginViewModel() }
    viewModel { MainViewModel() }
    viewModel { ScheduleViewModel(get()) }
}