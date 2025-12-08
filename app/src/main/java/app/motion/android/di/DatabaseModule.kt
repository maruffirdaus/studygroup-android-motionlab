package app.motion.android.di

import androidx.room.Room
import app.motion.android.data.local.MotionAppDatabase
import app.motion.android.data.local.ScheduleDao
import org.koin.dsl.module

val databaseModule = module {
    single<MotionAppDatabase> {
        Room.databaseBuilder(
            get(),
            MotionAppDatabase::class.java,
            "motion_app.db"
        ).build()
    }
    single<ScheduleDao> {
        get<MotionAppDatabase>().scheduleDao()
    }
}