package com.example.stemelevate.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.stemelevate.data.model.UserPreferences

@Database(entities = [UserPreferences::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class UserPreferenceDatabase : RoomDatabase() {
    abstract fun userPreferenceDAO(): UserPreferenceDAO

    companion object {
        @Volatile
        private var INSTANCE: UserPreferenceDatabase? = null

        fun getDatabase(context: Context): UserPreferenceDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    UserPreferenceDatabase::class.java,
                    "user_preference_data"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
