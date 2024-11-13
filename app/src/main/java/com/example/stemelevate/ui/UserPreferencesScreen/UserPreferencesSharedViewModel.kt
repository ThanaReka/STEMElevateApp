package com.example.stemelevate.ui.UserPreferencesScreen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import kotlin.text.clear

class UserPreferencesSharedViewModel : ViewModel() {
    // Data from UserGoalsScreen
    var careerGoals by mutableStateOf("")
    val learningGoals = mutableStateListOf<String>()
    var professionalGrowthArea by mutableStateOf("")

    // Data from SkillsInformationScreen
    val technicalSkills = mutableStateListOf<String>()
    val softSkills = mutableStateListOf<String>()

    // Data from AreasOfInterestScreen
    val stemInterests = mutableStateListOf<String>()
    var preferredTopics by mutableStateOf("")

    // Data from MentorshipPreferencesScreen
    val mentorshipTopics = mutableStateListOf<String>()
    var mentorshipPreference by mutableStateOf("")

    fun clearData() {
        // Reset all data fields to their initial values
        careerGoals = ""
        learningGoals.clear()
        professionalGrowthArea = ""
        technicalSkills.clear()
        softSkills.clear()
        stemInterests.clear()
        preferredTopics = ""
        mentorshipTopics.clear()
        mentorshipPreference = ""
    }
}