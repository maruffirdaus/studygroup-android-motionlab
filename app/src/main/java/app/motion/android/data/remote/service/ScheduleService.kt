package app.motion.android.data.remote.service

import app.motion.android.data.remote.model.request.PostScheduleRequest
import app.motion.android.data.remote.model.request.PutScheduleRequest
import app.motion.android.data.remote.model.response.DeleteScheduleResponse
import app.motion.android.data.remote.model.response.GetSchedulesResponse
import app.motion.android.data.remote.model.response.PostScheduleResponse
import app.motion.android.data.remote.model.response.PutScheduleResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface ScheduleService {
    @GET("schedules")
    suspend fun getSchedules(): Response<GetSchedulesResponse>

    @POST("schedules")
    suspend fun postSchedule(
        @Body request: PostScheduleRequest
    ): Response<PostScheduleResponse>

    @PUT("schedules/{id}")
    suspend fun putSchedule(
        @Path("id") id: Int,
        @Body request: PutScheduleRequest
    ): Response<PutScheduleResponse>


    @DELETE("schedules/{id}")
    suspend fun deleteSchedule(
        @Path("id") id: Int
    ): Response<DeleteScheduleResponse>
}