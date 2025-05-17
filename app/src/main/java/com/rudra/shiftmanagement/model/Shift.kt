package com.rudra.shiftmanagement.model


import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "shifts")
data class Shift(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val employeeName: String,
    val shiftDate: String,
    val startTime: String,
    val endTime: String,
    val position: String,
    val notes: String = ""
)