package app.motion.android.data.remote.model.request

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

@Serializable
data class PutScheduleRequest(

	@SerialName("date")
	val date: String,

	@SerialName("mentor")
	val mentor: String,

	@SerialName("name")
	val name: String,

	@SerialName("time")
	val time: String
)
