package app.motion.android.data.remote.model

import com.google.gson.annotations.SerializedName

data class PostSchedulesResponse(

	@field:SerializedName("schedule")
	val schedule: Schedule,

	@field:SerializedName("message")
	val message: String
)

data class Schedule(

	@field:SerializedName("date")
	val date: String,

	@field:SerializedName("mentor")
	val mentor: String,

	@field:SerializedName("updated_at")
	val updatedAt: String,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("created_at")
	val createdAt: String,

	@field:SerializedName("time")
	val time: String,

	@field:SerializedName("id")
	val id: Int
)
