package com.example.quanlythoikhoabieu

import kotlinx.coroutines.flow.Flow

class ScheduleRepository(private val scheduleDao: ScheduleDao) {

    val allScheduleItems: Flow<List<ScheduleItem>> = scheduleDao.getAllScheduleItems()

    suspend fun insert(item: ScheduleItem) {
        scheduleDao.insert(item)
    }

    suspend fun delete(item: ScheduleItem) {
        scheduleDao.delete(item)
    }
}