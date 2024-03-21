package com.example.carlotoledo_antoniochung_comp304sec002_Lab3.database.schedule

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity(tableName = "Flights")
data class Flights(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "Airline") val airline: String, // Nullable string type
    @ColumnInfo(name = "ArrivalTime") val arrivalTime: String,
    @ColumnInfo(name = "TerminalNumber") val terminal: String,
    @ColumnInfo(name = "Status") val status: String
)

