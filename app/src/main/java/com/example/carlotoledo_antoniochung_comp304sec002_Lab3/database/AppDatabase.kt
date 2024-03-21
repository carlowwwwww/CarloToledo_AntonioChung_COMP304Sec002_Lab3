package com.example.carlotoledo_antoniochung_comp304sec002_Lab3.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.carlotoledo_antoniochung_comp304sec002_Lab3.database.schedule.Flights
import com.example.carlotoledo_antoniochung_comp304sec002_Lab3.database.schedule.ScheduleDao

@Database(entities = [Flights::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun scheduleDao(): ScheduleDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,  // Use applicationContext to prevent memory leaks
                    AppDatabase::class.java,
                    "app_database"
                )
                    .createFromAsset("database/ArrivalTimes.db") // Create database from asset
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}

