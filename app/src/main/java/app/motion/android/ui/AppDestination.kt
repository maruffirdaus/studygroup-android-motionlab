package app.motion.android.ui

import kotlinx.serialization.Serializable

@Serializable
object Login

@Serializable
data class Main(
    val username: String
)