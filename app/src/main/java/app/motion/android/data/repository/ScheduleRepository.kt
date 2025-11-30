package app.motion.android.data.repository

import app.motion.android.common.model.Lesson
import app.motion.android.common.model.Schedule
import app.motion.android.data.remote.model.request.PostScheduleRequest
import app.motion.android.data.remote.model.request.PutScheduleRequest
import app.motion.android.data.remote.service.ScheduleService

class ScheduleRepository(
    private val scheduleService: ScheduleService
) {
    suspend fun addSchedule(schedule: Schedule) {
        val response = scheduleService.postSchedule(
            PostScheduleRequest(
                name = schedule.lesson.name,
                mentor = schedule.lesson.mentor,
                date = schedule.date,
                time = schedule.time
            )
        )
        if (!response.isSuccessful) {
            throw Exception("Failed to add schedule")
        }
    }

    suspend fun getSchedules(): List<Schedule> {
        val response = scheduleService.getSchedules()
        if (!response.isSuccessful) {
            throw Exception("Failed to get schedules")
        }
        return response.body()?.schedule?.map { schedule ->
            Schedule(
                id = schedule.id,
                lesson = Lesson(
                    name = schedule.name,
                    mentor = schedule.mentor
                ),
                date = schedule.date,
                time = schedule.time
            )
        } ?: emptyList()
    }

    suspend fun editSchedule(schedule: Schedule) {
        val response = scheduleService.putSchedule(
            id = schedule.id,
            request = PutScheduleRequest(
                name = schedule.lesson.name,
                mentor = schedule.lesson.mentor,
                date = schedule.date,
                time = schedule.time
            )
        )
        if (!response.isSuccessful) {
            throw Exception("Failed to edit schedule")
        }
    }

    suspend fun deleteSchedule(id: Int) {
        val response = scheduleService.deleteSchedule(id)
        if (!response.isSuccessful) {
            throw Exception("Failed to delete schedule")
        }
    }
}