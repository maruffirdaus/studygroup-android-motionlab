package app.motion.android.data.remote.model.response

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

@Serializable
data class PutSchedulesResponse(

	@SerialName("message")
	val message: String
)
