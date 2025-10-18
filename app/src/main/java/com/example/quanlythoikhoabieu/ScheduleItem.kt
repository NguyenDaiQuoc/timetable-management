package com.example.quanlythoikhoabieu

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "schedule_table")
data class ScheduleItem(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val subjectName: String,
    val dayOfWeek: String,
    val startTime: String,
    val endTime: String,
    val room: String? = null
)