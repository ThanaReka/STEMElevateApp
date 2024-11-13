package com.example.stemelevate.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.stemelevate.data.model.UserPreferences

@Dao
interface UserPreferenceDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUserPreferences(userPreferences: UserPreferences)

    @Query("SELECT * FROM user_preferences ORDER BY id DESC LIMIT 1")
    suspend fun getLatestUserPreferenceData(): UserPreferences?

//    @Insert
//    suspend fun insertUserPreferences(userPreferences: UserPreferences)
}

