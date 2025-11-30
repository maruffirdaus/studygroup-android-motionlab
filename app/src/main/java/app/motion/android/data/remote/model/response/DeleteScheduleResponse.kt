package app.motion.android.data.remote.model.response

import com.google.gson.annotations.SerializedName

data class DeleteScheduleResponse(

	@field:SerializedName("message")
	val message: String
)
