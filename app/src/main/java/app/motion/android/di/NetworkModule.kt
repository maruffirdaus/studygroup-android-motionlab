package app.motion.android.di

import app.motion.android.data.remote.service.ScheduleConfig
import org.koin.dsl.module

val networkModule = module {
    single { ScheduleConfig.getScheduleService() }
}