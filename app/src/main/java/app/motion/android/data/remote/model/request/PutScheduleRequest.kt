package app.motion.android.data.remote.model.request

import com.google.gson.annotations.SerializedName

data class PutScheduleRequest(

	@field:SerializedName("date")
	val date: String,

	@field:SerializedName("mentor")
	val mentor: String,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("time")
	val time: String
)
