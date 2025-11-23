package app.motion.android.di

import app.motion.android.data.repository.ScheduleRepository
import org.koin.dsl.module

val repositoryModule = module {
    single { ScheduleRepository() }
}