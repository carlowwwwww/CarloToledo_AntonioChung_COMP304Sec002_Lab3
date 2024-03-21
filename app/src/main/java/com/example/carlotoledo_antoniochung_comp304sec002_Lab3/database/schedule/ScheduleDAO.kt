package com.example.carlotoledo_antoniochung_comp304sec002_Lab3.database.schedule

import androidx.room.Dao
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ScheduleDao {

    @Query("SELECT *  FROM Flights ORDER BY arrivalTime ASC")
    fun getAll(): Flow<List<Flights>>

    // Change later
    @Query("SELECT * FROM Flights WHERE airline = :airline ORDER BY arrivalTime ASC")
    fun getByAirlineName(airline: String): Flow<List<Flights>>
}