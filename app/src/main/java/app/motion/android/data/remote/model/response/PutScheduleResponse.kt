package app.motion.android.data.remote.model.response

import com.google.gson.annotations.SerializedName

data class PutScheduleResponse(

	@field:SerializedName("message")
	val message: String
)
