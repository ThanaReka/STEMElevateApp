package com.example.stemelevate.data

import android.content.Context
import android.util.Log
import com.example.stemelevate.data.model.UserPreferences
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UserPreferenceRepository(private val userPreferenceDAO: UserPreferenceDAO) {

    private val firestore = FirebaseFirestore.getInstance()

    // Insert user preferences into Room database
    suspend fun insertUserPreferences(userPreferences: UserPreferences) {
        userPreferenceDAO.insertUserPreferences(userPreferences)
    }

    // Fetch the latest user preferences from Room and sync to Firestore
    suspend fun syncUserPreferencesToFirestore() = withContext(Dispatchers.IO) {
        try {
            // Fetch the latest user preferences from Room
            val userPreferences = userPreferenceDAO.getLatestUserPreferenceData()

            if (userPreferences != null) {
                // Convert UserPreferences to a map for Firestore
                val userPreferencesMap = userPreferencesToMap(userPreferences)

                // Upload data to Firestore under the collection "user_preferences"
                firestore.collection("user_preferences")
                    .document("latest_user_preferences")
                    .set(userPreferencesMap)
                    .addOnSuccessListener {
                        Log.d("FirestoreSync", "User preferences synced successfully.")
                    }
                    .addOnFailureListener { e ->
                        Log.e("FirestoreSync", "Error syncing user preferences", e)
                    }
            } else {
                Log.d("FirestoreSync", "No user preferences found in Room database.")
            }
        } catch (e: Exception) {
            Log.e("FirestoreSync", "Error during sync", e)
        }
    }

    // Helper function to convert UserPreferences entity to a Firestore-compatible map
    private fun userPreferencesToMap(userPreferences: UserPreferences): Map<String, Any> {
        return mapOf(
            "careerGoals" to userPreferences.careerGoals,
            "learningGoals" to userPreferences.learningGoals,
            "professionalGrowthArea" to userPreferences.professionalGrowthArea,
            "technicalSkills" to userPreferences.technicalSkills,
            "softSkills" to userPreferences.softSkills,
            "stemInterests" to userPreferences.stemInterests,
            "preferredTopics" to userPreferences.preferredTopics,
            "mentorshipTopics" to userPreferences.mentorshipTopics,
            "mentorshipPreference" to userPreferences.mentorshipPreference
        )
    }
}


//class UserPreferenceRepository(private val dao: UserPreferenceDAO) {
//
//    suspend fun saveUserPreferenceData(
//        userGoals: String,
//        technicalSkills: String,
//        softSkills: String,
//        areasOfInterest: String,
//        mentorshipTopics: String,
//        mentorshipPreference: String
//    ) {
//        val userPreferences = UserPreferences(
//            userGoals = userGoals,
//            technicalSkills = technicalSkills,
//            softSkills = softSkills,
//            areasOfInterest = areasOfInterest,
//            mentorshipTopics = mentorshipTopics,
//            mentorshipPreference = mentorshipPreference
//        )
//        dao.insert(userPreferencesData)
//    }
//
//    suspend fun getLatestFormData() = dao.getLatestUserPreferenceData()
//}