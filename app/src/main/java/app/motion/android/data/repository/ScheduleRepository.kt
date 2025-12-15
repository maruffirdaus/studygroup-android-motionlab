package app.motion.android.data.repository

import app.motion.android.common.model.Lesson
import app.motion.android.common.model.Schedule
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class ScheduleRepository(
    private val auth: FirebaseAuth,
    private val firestore: FirebaseFirestore
) {
    suspend fun addSchedule(schedule: Schedule) {
        val userId = auth.currentUser?.uid ?: throw Exception("User not logged in")
        val collection = firestore
            .collection("users")
            .document(userId)
            .collection("schedules")
        collection.document(schedule.id).set(schedule).await()
    }

    suspend fun getSchedules(): List<Schedule> {
        val userId = auth.currentUser?.uid ?: throw Exception("User not logged in")
        val collection = firestore
            .collection("users")
            .document(userId)
            .collection("schedules")
        val snapshot = collection.get().await()
        return snapshot.documents.mapNotNull { document ->
            val id = document.id
            val lessonMap = document.get("lesson") as? Map<*, *> ?: return@mapNotNull null
            val lesson = Lesson(
                name = lessonMap["name"] as? String ?: return@mapNotNull null,
                mentor = lessonMap["mentor"] as? String ?: return@mapNotNull null
            )
            val date = document.getString("date") ?: return@mapNotNull null
            val time = document.getString("time") ?: return@mapNotNull null

            Schedule(id, lesson, date, time)
        }
    }

    suspend fun editSchedule(schedule: Schedule) {
        val userId = auth.currentUser?.uid ?: throw Exception("User not logged in")
        val collection = firestore
            .collection("users")
            .document(userId)
            .collection("schedules")
        collection.document(schedule.id).set(schedule).await()
    }

    suspend fun deleteSchedule(id: String) {
        val userId = auth.currentUser?.uid ?: throw Exception("User not logged in")
        val collection = firestore
            .collection("users")
            .document(userId)
            .collection("schedules")
        collection.document(id).delete().await()
    }
}