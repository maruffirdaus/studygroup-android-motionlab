package app.motion.android.common.model

data class Schedule(
    val id: String,
    val lesson: Lesson,
    val date: String,
    val time: String
)
