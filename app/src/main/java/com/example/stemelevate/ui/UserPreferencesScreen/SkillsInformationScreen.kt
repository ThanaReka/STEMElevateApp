package com.example.stemelevate.ui.UserPreferencesScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun SkillsInformationScreen() {
    val sharedViewModel: UserPreferencesSharedViewModel = viewModel()
    val technicalSkills = remember { mutableStateListOf<String>() }
    val softSkills = remember { mutableStateListOf<String>() }

    val technicalSkillOptions = listOf(
        // Programming Languages
        "Python", "Java", "JavaScript", "Kotlin", "Swift", "C++", "C#", "Ruby", "R", "SQL",
        "MATLAB", "Go", "Rust", "TypeScript",
//        // Data & AI/ML Skills
        "Data Analysis", "Machine Learning", "Deep Learning", "Natural Language Processing (NLP)",
        "Computer Vision", "Big Data", "Data Mining", "Data Visualization", "Predictive Modeling",
        "Statistical Analysis", "Neural Networks", "Reinforcement Learning", "Time Series Analysis",
        "Anomaly Detection",
        // Software & Application Development
        "Web Development", "Mobile Development (iOS/Android)", "Full Stack Development",
        "Backend Development", "Frontend Development", "Game Development", "API Development",
        "Microservices Architecture", "Serverless Computing", "Cloud Computing (AWS, Azure, GCP)",
        "UX/UI Design",
        // Databases
        "SQL/NoSQL Databases", "Database Design", "Data Warehousing", "MySQL", "PostgreSQL",
        "MongoDB", "Redis", "Oracle", "Firebase", "Cassandra",
        // DevOps & Automation
        "CI/CD Pipelines", "Docker", "Kubernetes", "Jenkins", "Terraform", "Ansible", "Puppet",
        "Git/GitHub/GitLab", "Bash/Shell Scripting",
        // Security & Networking
        "Cybersecurity", "Network Security", "Ethical Hacking", "Penetration Testing",
        "Firewall Management", "Cloud Security", "Cryptography", "Security Protocols",
        // Engineering & Robotics
        "Robotics", "IoT (Internet of Things)", "CAD (Computer-Aided Design)",
        "PLC (Programmable Logic Controller)", "Automation Engineering", "Mechanical Design",
        "Systems Engineering", "Electrical Engineering", "Embedded Systems",
        // Blockchain & Emerging Tech
        "Blockchain Development", "Smart Contracts", "Cryptocurrencies",
        "Decentralized Applications (dApps)", "Web3",
        // Miscellaneous Technical Skills
        "Agile/Scrum Methodologies", "Project Management", "Digital Marketing", "SEO Optimization",
        "Technical Writing", "Research and Development (R&D)"
    )
    val softSkillOptions = listOf(
        "Problem-Solving", "Communication", "Teamwork", "Creativity",
        "Communication Skills", "Interpersonal Skills", "Leadership Skills",
        "Problem-Solving and Analytical Skills", "Adaptability and Time Management",
        "Teamwork and Collaboration", "Organizational and Planning Skills",
        "Self-Management and Work Ethic", "Creativity and Innovation",
        "Learning and Development", "Technical Collaboration Skills",
        "Diversity and Inclusion Skills", "Conflict Resolution and Mediation",
        "Personal Growth and Development Skills"
    )

    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {

        item {
            // Title with Icon
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Filled.Build, contentDescription = null, tint = Color.White)
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    "What skills do you have?",
                    style = TextStyle(color = Color.White, fontSize = 20.sp)
                )
            }
            Spacer(modifier = Modifier.height(16.dp))

            // Technical Skills (Multi-select tags)
            Text("1. Technical Skills", color = Color.White)
            LazyRow(modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(technicalSkillOptions) { skill ->
                    FilterChip(
                        selected = technicalSkills.contains(skill),
                        onClick = {
                            if (technicalSkills.contains(skill)) {
                                technicalSkills.remove(skill)
                                sharedViewModel.technicalSkills.remove(skill)
                            } else {
                                technicalSkills.add(skill)
                                sharedViewModel.technicalSkills.add(skill)
                            }
                        },
                        label = { Text(skill, color = Color.White) },
                        modifier = Modifier.padding(4.dp),
                        colors = FilterChipDefaults.filterChipColors(
                            selectedContainerColor = Color(0xFF3E39AF), // Customize selected color
                            selectedLabelColor = Color.White,
                            containerColor = Color(0xFF3E39AF),
                            labelColor = Color.White
                        )
                    )
                }
            }
            Spacer(modifier = Modifier.height(16.dp))

        }

        item {
            // Soft Skills (Checkbox options)
            Text("2. Soft Skills", color = Color.White)
            softSkillOptions.forEach { skill ->
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Checkbox(
                        checked = softSkills.contains(skill),
                        onCheckedChange = { checked ->
                            if (checked) {
                                softSkills.add(skill)
                                sharedViewModel.softSkills.add(skill)
                            } else {
                                softSkills.remove(skill)
                                sharedViewModel.softSkills.remove(skill)
                            }
                        },
                        colors = CheckboxDefaults.colors(
                            checkedColor = Color.White,
                            uncheckedColor = Color.White
                        )
                    )
                    Text(skill, color = Color.White)
                }
            }
        }
    }
}

@Preview
@Composable
fun SkillsInformationScreenPreview() {
    SkillsInformationScreen()
}