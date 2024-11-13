package com.example.stemelevate.ui.UserPreferencesScreen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun MentorshipPreferencesScreen() {
    val sharedViewModel: UserPreferencesSharedViewModel = viewModel()
    var mentorshipPreference by remember { mutableStateOf(sharedViewModel.mentorshipPreference) }

    val mentorshipTopicOptions = listOf(
        "Career Development", "Project Collaboration", "Leadership Development",
        "Technical Guidance", "Learning & Skill Development", "Soft Skills & Personal Growth",
        "Diversity, Equity, and Inclusion (DEI)", "Entrepreneurship & Innovation",
        "Research & Academia", "Innovation & Emerging Technologies",
        "Community Building & Social Impact", "Transitioning & Re-Skilling"
    )
    val mentorshipPreferenceOptions = listOf("1-on-1 Mentorship", "Group Sessions", "Event-Based Guidance")

    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(16.dp)) {

        // Title with Icon
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(Icons.Filled.Person, contentDescription = null, tint = Color.White)
            Spacer(modifier = Modifier.width(8.dp))
            Text("What would you like mentorship in?", style = TextStyle(color = Color.White, fontSize = 20.sp))
        }
        Spacer(modifier = Modifier.height(16.dp))

        // Mentorship Topics (Multi-choice)
        Text("1. Mentorship Topics", color = Color.White)
        mentorshipTopicOptions.forEach { topic ->
            Row(verticalAlignment = Alignment.CenterVertically) {
                Checkbox(
                    checked = sharedViewModel.mentorshipTopics.contains(topic),
                    onCheckedChange = { checked ->
                        if (checked) {
//                            mentorshipTopics.add(topic)
                            sharedViewModel.mentorshipTopics.add(topic)
                        } else {
//                            mentorshipTopics.remove(topic)
                            sharedViewModel.mentorshipTopics.remove(topic)
                        }
                    },
                    colors = CheckboxDefaults.colors(
                        checkedColor = Color.White,
                        uncheckedColor = Color.White
                    )
                )
                Text(topic, color = Color.White)
            }
        }
        Spacer(modifier = Modifier.height(16.dp))

        // Mentorship Preferences (Toggle)
        Text("2. Mentorship Preferences", color = Color.White)
        mentorshipPreferenceOptions.forEach { preference ->
            Row(verticalAlignment = Alignment.CenterVertically) {
                RadioButton(
                    selected = mentorshipPreference == preference,
                    onClick = {
                        mentorshipPreference = preference
                        sharedViewModel.mentorshipPreference = preference
                    },
                    colors = RadioButtonDefaults.colors(
                        selectedColor = Color.White,
                        unselectedColor = Color.White
                    )
                )
                Text(preference, color = Color.White)
            }
        }
    }
}

@Preview
@Composable
fun MentorshipPreferencesScreenPreview() {
    MentorshipPreferencesScreen()
}