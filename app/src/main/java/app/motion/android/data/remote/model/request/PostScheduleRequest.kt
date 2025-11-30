package app.motion.android.data.remote.model.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PostScheduleRequest(

	@SerialName("date")
	val date: String,

	@SerialName("mentor")
	val mentor: String,

	@SerialName("name")
	val name: String,

	@SerialName("time")
	val time: String
)