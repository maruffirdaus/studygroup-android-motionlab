package app.motion.android.data.repository

import app.motion.android.common.model.Schedule

class ScheduleRepository {
    private val schedules: MutableList<Schedule> = mutableListOf()

    fun addSchedule(schedule: Schedule) {
        schedules.add(0, schedule)
    }

    fun getSchedules(): List<Schedule> {
        return schedules.toList()
    }

    fun editSchedule(schedule: Schedule) {
        val oldSchedule = schedules.find { scheduleToFind ->
            scheduleToFind.id == schedule.id
        }
        val index = schedules.indexOf(oldSchedule)
        schedules[index] = schedule
    }

    fun deleteSchedule(id: String) {
        schedules.removeIf { schedule ->
            schedule.id == id
        }
    }
}