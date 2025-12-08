package app.motion.android.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [ScheduleEntity::class],
    version = 1,
    exportSchema = false
)
abstract class MotionAppDatabase : RoomDatabase() {
    abstract fun scheduleDao(): ScheduleDao
}