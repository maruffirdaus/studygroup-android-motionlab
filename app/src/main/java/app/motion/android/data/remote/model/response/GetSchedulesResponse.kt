package app.motion.android.data.remote.model.response

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

@Serializable
data class GetSchedulesResponse(

	@SerialName("schedule")
	val schedule: List<ScheduleItem>,

	@SerialName("message")
	val message: String
)

@Serializable
data class ScheduleItem(

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

	@SerialName("id")
	val id: Int,

	@SerialName("time")
	val time: String
)
