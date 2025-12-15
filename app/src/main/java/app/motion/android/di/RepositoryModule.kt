package app.motion.android.di

import app.motion.android.data.repository.AuthRepository
import app.motion.android.data.repository.ScheduleRepository
import org.koin.dsl.module

val repositoryModule = module {
    single { AuthRepository(get()) }
    single { ScheduleRepository(get(), get()) }
}