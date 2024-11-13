package com.example.stemelevate.ui.UserPreferencesScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.stemelevate.data.model.ProfessionalGrowthOption

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserGoalsScreen() {
    val sharedViewModel: UserPreferencesSharedViewModel = viewModel()
    var careerGoals by remember { mutableStateOf(sharedViewModel.careerGoals) }
    val learningGoals = remember { mutableStateListOf<String>() }
    var professionalGrowthArea by remember { mutableStateOf("") }
    val professionalGrowthOptions = listOf(
        "Management",
        "Research",
        "Development",
        "Soft Skills",
        "Technical Skills",
        "Leadership",
        "Communication",
        "Personal Development",
        "Business",
        "Emerging Technologies",
        "Diversity",
        "Mentorship"
    )

    var expanded by remember { mutableStateOf(false) } // Add expanded state

    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
    ) {
        item {
            Spacer(modifier = Modifier.height(16.dp))

            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                Text("Tell us about your goals.", color = Color.White)
            }

            // Title with Icon
            Spacer(modifier = Modifier.height(16.dp))

            Text("1. Career Goals", color = Color.White)

            // Career Goals Input
            Spacer(modifier = Modifier.height(16.dp))

            TextField(
                value = careerGoals,
                onValueChange = {
                    careerGoals = it
                    sharedViewModel.careerGoals = it
                },
                label = { Text("Career Goals", color = Color.Black) },
                modifier = Modifier.fillMaxWidth(),
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Color.White, // Background color
                    focusedIndicatorColor = Color.Transparent,
                )
            )
            Spacer(modifier = Modifier.height(24.dp))
        }

        item {
            // Learning Goals (Multi-choice)
            Text("2. Learning Goals", color = Color.White)
//        val learningGoalOptions = listOf("Technical Skills", "Soft Skills", "Leadership")
            professionalGrowthOptions.forEach { goal ->
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Checkbox(
                        checked = learningGoals.contains(goal),
                        onCheckedChange = { checked ->
//                            if (checked) learningGoals.add(goal) else learningGoals.remove(goal)
                            if (checked) {
                                learningGoals.add(goal)
                                sharedViewModel.learningGoals.add(goal)
                            } else {
                                learningGoals.remove(goal)
                                sharedViewModel.learningGoals.remove(goal)
                            }
                        },
                        colors = CheckboxDefaults.colors(
                            checkedColor = Color.White,
                            uncheckedColor = Color.White
                        )
                    )
                    Text(goal, color = Color.White)
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
        }

        item {

            // Professional Growth Areas (Dropdown)
            ExposedDropdownMenuBox(
                expanded = expanded, // Use expanded state
                onExpandedChange = { expanded = !expanded } // Toggle expanded state
            ) {
                TextField(
                    value = professionalGrowthArea,
                    onValueChange = {}, // Read-only
                    readOnly = true,
                    label = { Text("3. Professional Growth Areas", color = Color.White) },
                    textStyle = TextStyle(color = Color.White),
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = false) },
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        //                    textColor = Color.White,
                        focusedBorderColor = Color.White,
                        unfocusedBorderColor = Color.White,
                        disabledTextColor = Color.White,
                        disabledLabelColor = Color.White
                    ),
                    modifier = Modifier.menuAnchor()
                )
                ExposedDropdownMenu(
                    expanded = expanded, // Use expanded state
                    onDismissRequest = { expanded = false } // Collapse on dismiss
                ) {
                    professionalGrowthOptions.forEach { option ->
                        DropdownMenuItem(
                            text = { Text(option, color = Color.Black) },
                            onClick = {
                                professionalGrowthArea = option
                                sharedViewModel.professionalGrowthArea = option // Update sharedViewModel // Trigger database insertion
                                expanded = false // Close the dropdown after selection
                            }
                        )
                    }
                }
            }
        }
    }
}

@Preview()
@Composable
fun UserGoalsScreenPreview() {
    UserGoalsScreen()
}
