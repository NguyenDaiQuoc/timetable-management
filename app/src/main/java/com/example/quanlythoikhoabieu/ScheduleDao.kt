package com.example.quanlythoikhoabieu

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface ScheduleDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(item: ScheduleItem)

    @Update
    suspend fun update(item: ScheduleItem)

    @Delete
    suspend fun delete(item: ScheduleItem)

    @Query("SELECT * FROM schedule_table ORDER BY dayOfWeek, startTime")
    fun getAllScheduleItems(): Flow<List<ScheduleItem>>

    // Có thể dùng hàm này để lọc theo ngày trong tuần sau này
    @Query("SELECT * FROM schedule_table WHERE dayOfWeek = :day ORDER BY startTime")
    fun getScheduleForDay(day: String): Flow<List<ScheduleItem>>
}