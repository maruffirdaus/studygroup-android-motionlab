package app.motion.android.data.remote.service

import app.motion.android.data.remote.model.DeleteSchedulesResponse
import app.motion.android.data.remote.model.GetSchedulesResponse
import app.motion.android.data.remote.model.PostSchedulesResponse
import app.motion.android.data.remote.model.PutSchedulesResponse
import retrofit2.Response
import retrofit2.http.DELETE
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface ScheduleService {
    @GET("schedules")
    suspend fun getSchedules(): Response<GetSchedulesResponse>

    @FormUrlEncoded
    @POST("schedules")
    suspend fun addSchedule(
        @Field("name") name: String,
        @Field("mentor") mentor: String,
        @Field("date") date: String,
        @Field("time") time: String
    ): Response<PostSchedulesResponse>

    @PUT("schedules/{id}")
    suspend fun updateSchedule(
        @Path("id") id: Int, @Field("name") name: String,
        @Field("mentor") mentor: String,
        @Field("date") date: String,
        @Field("time") time: String
    ): Response<PutSchedulesResponse>


    @DELETE("schedules/{id}")
    suspend fun deleteSchedule(@Path("id") id: Int): Response<DeleteSchedulesResponse>
}