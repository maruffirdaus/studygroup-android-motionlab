package app.motion.android.data.remote.service

import app.motion.android.data.remote.model.request.PostScheduleRequest
import app.motion.android.data.remote.model.request.PutScheduleRequest
import app.motion.android.data.remote.model.response.DeleteSchedulesResponse
import app.motion.android.data.remote.model.response.GetSchedulesResponse
import app.motion.android.data.remote.model.response.PostScheduleResponse
import app.motion.android.data.remote.model.response.PutSchedulesResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.delete
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.put
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.http.isSuccess

class ScheduleService(
    private val client: HttpClient
) {
    private val baseUrl = "https://api.selesainst.id/api"

    suspend fun getSchedules(): GetSchedulesResponse {
        val response = client.get("$baseUrl/schedules")
        if (!response.status.isSuccess()) {
            throw Exception("Failed to get schedules")
        }
        return response.body<GetSchedulesResponse>()
    }

    suspend fun postSchedule(request: PostScheduleRequest): PostScheduleResponse {
        val response = client.post("$baseUrl/schedules") {
            contentType(ContentType.Application.Json)
            setBody(request)
        }
        if (!response.status.isSuccess()) {
            throw Exception("Failed to post schedule")
        }
        return response.body<PostScheduleResponse>()
    }

    suspend fun putSchedule(id: Int, request: PutScheduleRequest): PutSchedulesResponse {
        val response = client.put("$baseUrl/schedules/$id") {
            contentType(ContentType.Application.Json)
            setBody(request)
        }
        if (!response.status.isSuccess()) {
            throw Exception("Failed to put schedule")
        }
        return response.body<PutSchedulesResponse>()
    }

    suspend fun deleteSchedule(id: Int): DeleteSchedulesResponse {
        val response = client.delete("$baseUrl/schedules/$id")
        if (!response.status.isSuccess()) {
            throw Exception("Failed to delete schedule")
        }
        return response.body<DeleteSchedulesResponse>()
    }
}