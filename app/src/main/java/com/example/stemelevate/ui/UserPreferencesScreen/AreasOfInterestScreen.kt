package com.example.stemelevate.ui.UserPreferencesScreen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
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


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AreasOfInterestScreen() {
    val sharedViewModel: UserPreferencesSharedViewModel = viewModel()
    val stemInterests = remember { mutableStateListOf<String>() }
    var preferredTopics by remember { mutableStateOf(sharedViewModel.preferredTopics) }

    val stemInterestOptions = listOf(
        // Technology
        "Software Development", "Data Science", "Engineering", "Biotechnology", "Robotics",
        "Artificial Intelligence", "Cybersecurity", "Game Development", "Mobile App Development",
        "Web Development", "Artificial Intelligence (AI) & Machine Learning (ML)",
        "DevOps & Site Reliability Engineering (SRE)", "Blockchain Technology",
        "Augmented Reality (AR) & Virtual Reality (VR)", "Internet of Things (IoT)",
        "Robotics & Automation", "UI/UX Design", "Product Management",
        "Quality Assurance & Testing", "Quantum Computing", "Database Management & Big Data",
        "Software Architecture", "Full Stack Development",
        // 🧪 Science
        "Biotechnology", "Astrophysics & Astronomy", "Environmental Science",
        "Genomics & Bioinformatics", "Pharmaceutical Science", "Marine Biology",
        "Neuroscience", "Physics", "Chemistry", "Geology & Earth Science",
        "Microbiology", "Ecology & Conservation", "Cognitive Science",
        "Materials Science", "Climate Science", "Nanotechnology", "Space Science",
        "Forensic Science", "Food Science & Nutrition", "Social Science & Behavioral Science",
        // ⚙️ Engineering
        "Mechanical Engineering", "Civil Engineering", "Electrical Engineering",
        "Computer Engineering", "Aerospace Engineering", "Biomedical Engineering",
        "Chemical Engineering", "Industrial Engineering", "Environmental Engineering",
        "Automotive Engineering", "Systems Engineering", "Structural Engineering",
        "Nuclear Engineering", "Marine & Ocean Engineering", "Petroleum Engineering",
        "Energy Engineering (Renewable & Non-Renewable)", "Software Engineering",
        "Hardware Engineering", "Mechatronics Engineering", "Robotics Engineering",
        // 📊 Mathematics
        "Statistics & Probability", "Data Analytics", "Applied Mathematics",
        "Pure Mathematics", "Financial Mathematics", "Cryptography", "Actuarial Science",
        "Operations Research", "Theoretical Mathematics", "Computational Mathematics",
        "Game Theory", "Mathematical Modeling", "Discrete Mathematics", "Algebra & Geometry",
        "Differential Equations", "Topology", "Linear Algebra", "Number Theory",
        "Mathematical Physics", "Mathematical Biology",
        // 🔍 Interdisciplinary Fields
        "Bioinformatics", "Cognitive Computing", "Health Informatics",
        "Smart Cities & Urban Planning", "Digital Health & Telemedicine",
        "Environmental Informatics", "Human-Computer Interaction (HCI)",
        "Sustainability & Renewable Energy", "Scientific Research & Development",
        "STEM Education & Outreach", "Ethical AI & Responsible Tech", "Digital Transformation",
        "E-commerce & Tech Entrepreneurship", "Computational Neuroscience", "Climate Tech",
        "Agritech & Food Tech", "Fintech", "Space Exploration & Aerospace Tech",
        "EdTech (Educational Technology)", "Assistive Technology & Accessibility",
        // 💼 Emerging Fields
        "Synthetic Biology", "Neurotechnology", "3D Printing & Additive Manufacturing",
        "Digital Twins", "Edge Computing", "Smart Wearables", "Extended Reality (XR)",
        "Climate Engineering", "Bioengineering & Tissue Engineering", "Metaverse Development"
    )

    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(16.dp)) {

        // Title with Icon
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(Icons.Filled.Favorite, contentDescription = null, tint = Color.White)
            Spacer(modifier = Modifier.width(8.dp))
            Text("Tell us about your interests.", style = TextStyle(color = Color.White, fontSize = 20.sp))
        }
        Spacer(modifier = Modifier.height(16.dp))

        // STEM Areas of Interest (Multi-select)
        Text("STEM Areas of Interest", color = Color.White)
        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp) // Add spacing between chips
        ) {
            items(stemInterestOptions) { interest ->
                FilterChip(
                    selected = stemInterests.contains(interest),
                    onClick = {
                        if (stemInterests.contains(interest)) {
                            stemInterests.remove(interest)
                            sharedViewModel.stemInterests.remove(interest)
                        } else {
                            stemInterests.add(interest)
                            sharedViewModel.stemInterests.add(interest)
                        }
                    },
                    label = { Text(interest, color = Color.White) },
                    // ... (other FilterChip styling if needed)
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
//
//        stemInterestOptions.forEach { interest ->
//            Row(verticalAlignment = Alignment.CenterVertically) {
//                Checkbox(
//                    checked = stemInterests.contains(interest),
//                    onCheckedChange = { checked ->
//                        if (checked) {
//                            stemInterests.add(interest)
//                            sharedViewModel.stemInterests.add(interest)
//                        } else {
//                            stemInterests.remove(interest)
//                            sharedViewModel.stemInterests.remove(interest)
//                        }
//                    },
//                    colors = CheckboxDefaults.colors(
//                        checkedColor = Color.White,
//                        uncheckedColor = Color.White
//                    )
//                )
//                Text(interest, color = Color.White)
//            }
//        }
//        Spacer(modifier = Modifier.height(16.dp))

        // Preferred Topics Input
        OutlinedTextField(
            value = preferredTopics,
            onValueChange = {
                preferredTopics = it
                sharedViewModel.preferredTopics = it
            },
            label = { Text("Preferred Topics", color = Color.Black) },
            modifier = Modifier.fillMaxWidth(),
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color.White, // Background color
                focusedIndicatorColor = Color.Transparent,
            )
        )
    }
}

@Preview
@Composable
fun AreasOfInterestScreenPreview() {
    AreasOfInterestScreen()
}
