package com.example.carlotoledo_antoniochung_comp304sec002_Lab3.database.schedule

import androidx.room.Dao
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ScheduleDao {

    @Query("SELECT *  FROM schedule ORDER BY id ASC")
    fun getAll(): Flow<List<Schedule>>

    // Change later
    @Query("SELECT * FROM schedule WHERE airline = :airline ORDER BY arrivalTime ASC")
    fun getByStopName(airline: String): Flow<List<Schedule>>
}