package com.example.stemelevate.ui.UserPreferencesScreen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.stemelevate.data.UserPreferenceRepository
import com.example.stemelevate.data.model.UserPreferences
import kotlinx.coroutines.launch
import kotlin.text.clear

class UserPreferencesViewModel(private val repository: UserPreferenceRepository) : ViewModel() {

    // In your UserPreferencesViewModel
    fun insertUserPreferences(userPreferences: UserPreferences) {
        viewModelScope.launch { // Change to lifecycleScope
            repository.insertUserPreferences(userPreferences)
        }
    }

        fun syncDataToFirestore() {
            viewModelScope.launch {
                repository.syncUserPreferencesToFirestore()
            }
        }


    }
