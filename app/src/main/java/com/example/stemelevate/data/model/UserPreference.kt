package com.example.stemelevate.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "user_preferences")
data class UserPreferences(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val careerGoals: String,
    val learningGoals: List<String>,
    val professionalGrowthArea: String,
    val technicalSkills: List<String>,
    val softSkills: List<String>,
    val stemInterests: List<String>,
    val preferredTopics: String,
    val mentorshipTopics: List<String>,
    val mentorshipPreference: String
): Serializable
