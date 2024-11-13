package com.example.stemelevate.ui.UserPreferencesScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.stemelevate.StemScreen
import com.example.stemelevate.data.UserPreferenceDatabase
import com.example.stemelevate.data.UserPreferenceRepository
import com.example.stemelevate.data.model.UserPreferences

data class FormStep(
    val title: String,
    val content: @Composable () -> Unit
)

val formSteps = listOf(
    FormStep("Step 1: User Goals") { UserGoalsScreen() },
    FormStep("Step 2: Skills Information") { SkillsInformationScreen() },
    FormStep("Step 3: Areas Of Interest") { AreasOfInterestScreen() },
    FormStep("Step 4: Mentorship Preferences") { MentorshipPreferencesScreen() }
)

@Composable
fun UserPreferencesScreen(navController: NavHostController) {
    var currentStep by remember { mutableStateOf(0) }
    val context = LocalContext.current
    val database = UserPreferenceDatabase.getDatabase(context)
    val userPreferenceDAO = database.userPreferenceDAO()
    val userPreferenceRepository = UserPreferenceRepository(userPreferenceDAO)
    val userPreferenceViewModel = UserPreferencesViewModel(userPreferenceRepository)
    val sharedViewModel: UserPreferencesSharedViewModel = viewModel()

    // Add a ScrollState for scrolling
    val scrollState = rememberScrollState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF3E39AF))
            .padding(24.dp)
    ) {
        Column(
            modifier = Modifier
                .verticalScroll(scrollState)
                .size(width = 1000.dp, height = 1500.dp),
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                ProgressBar(currentStep, formSteps.size)
                Text("Step ${currentStep + 1} of ${formSteps.size}", color = Color.White)
            }

            // Display current step content
            formSteps[currentStep].content()

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                if (currentStep > 0) {
                    Button(
                        onClick = { currentStep-- },
                        modifier = Modifier.size(width = 120.dp, height = 60.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF6599FE))
                    ) {
                        Text("Previous")
                    }
                }

                Button(
                    onClick = {
                        if (currentStep < formSteps.size - 1) {
                            currentStep++
                        } else {
                            // Create the user preferences object
                            val userPreferences = UserPreferences(
                                careerGoals = sharedViewModel.careerGoals,
                                learningGoals = sharedViewModel.learningGoals.toList(),
                                professionalGrowthArea = sharedViewModel.professionalGrowthArea,
                                technicalSkills = sharedViewModel.technicalSkills.toList(),
                                softSkills = sharedViewModel.softSkills.toList(),
                                stemInterests = sharedViewModel.stemInterests.toList(),
                                preferredTopics = sharedViewModel.preferredTopics,
                                mentorshipTopics = sharedViewModel.mentorshipTopics.toList(),
                                mentorshipPreference = sharedViewModel.mentorshipPreference,
                            )

                            // Insert user preferences into the database
                            userPreferenceViewModel.insertUserPreferences(userPreferences)

                            // Sync data to Firestore
                            userPreferenceViewModel.syncDataToFirestore()

                            // Create the formatted summary string
                            val summary = getFormattedUserPreferences(sharedViewModel)

                            // Navigate to GeminiFeedbackScreen with the summary
                            navController.navigate("${StemScreen.GeminiFeedbackScreen.name}/$summary")

                            // Clear shared data after submission
                            sharedViewModel.clearData()
                        }
                    },
                    modifier = Modifier.size(width = 100.dp, height = 60.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF6599FE))
                ) {
                    Text(if (currentStep < formSteps.size - 1) "Next" else "Finish Setup")
                }
            }
        }
    }
}

@Composable
fun ProgressBar(currentStep: Int, totalSteps: Int) {
    LinearProgressIndicator(
        progress = (currentStep + 1).toFloat() / totalSteps,
        modifier = Modifier.fillMaxWidth()
    )
}

fun getFormattedUserPreferences(sharedViewModel: UserPreferencesSharedViewModel): String {
    return """
        Career Goals: ${sharedViewModel.careerGoals}
        Learning Goals: ${sharedViewModel.learningGoals.joinToString(", ")}
        Professional Growth Area: ${sharedViewModel.professionalGrowthArea}
        Technical Skills: ${sharedViewModel.technicalSkills.joinToString(", ")}
        Soft Skills: ${sharedViewModel.softSkills.joinToString(", ")}
        STEM Interests: ${sharedViewModel.stemInterests.joinToString(", ")}
        Preferred Topics: ${sharedViewModel.preferredTopics}
        Mentorship Topics: ${sharedViewModel.mentorshipTopics.joinToString(", ")}
        Mentorship Preference: ${sharedViewModel.mentorshipPreference}
    """.trimIndent()
}



@Preview(showBackground = true)
@Composable
fun UserPreferencesScreenPreview() {

    UserPreferencesScreen(rememberNavController())

}