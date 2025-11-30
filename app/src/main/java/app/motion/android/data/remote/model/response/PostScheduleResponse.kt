package app.motion.android.data.remote.model.response

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

@Serializable
data class PostScheduleResponse(

	@SerialName("schedule")
	val schedule: Schedule,

	@SerialName("message")
	val message: String
)

@Serializable
data class Schedule(

	@SerialName("date")
	val date: String,

	@SerialName("mentor")
	val mentor: String,

	@SerialName("updated_at")
	val updatedAt: String,

	@SerialName("name")
	val name: String,

	@SerialName("created_at")
	val createdAt: String,

	@SerialName("time")
	val time: String,

	@SerialName("id")
	val id: Int
)
