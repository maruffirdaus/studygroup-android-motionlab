package app.motion.android.data.repository

import app.motion.android.common.model.Lesson
import app.motion.android.common.model.Schedule
import app.motion.android.data.local.ScheduleDao
import app.motion.android.data.local.ScheduleEntity

class ScheduleRepository(val dao: ScheduleDao) {
    suspend fun addSchedule(schedule: Schedule) {
        dao.insert(
            ScheduleEntity(
                id = schedule.id,
                name = schedule.lesson.name,
                mentor = schedule.lesson.mentor,
                date = schedule.date,
                time = schedule.time
            )
        )
    }

    suspend fun getSchedules(): List<Schedule> {
        return dao.getSchedules().map { schedule ->
            Schedule(
                id = schedule.id,
                lesson = Lesson(
                    name = schedule.name,
                    mentor = schedule.mentor
                ),
                date = schedule.date,
                time = schedule.time
            )
        }
    }

    suspend fun editSchedule(schedule: Schedule) {
        dao.update(
            ScheduleEntity(
                id = schedule.id,
                name = schedule.lesson.name,
                mentor = schedule.lesson.mentor,
                date = schedule.date,
                time = schedule.time
            )
        )
    }

    suspend fun deleteSchedule(id: String) {
        dao.delete(id)
    }
}