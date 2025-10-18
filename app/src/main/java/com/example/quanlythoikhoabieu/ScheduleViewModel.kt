package com.example.quanlythoikhoabieu

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class ScheduleViewModel(private val repository: ScheduleRepository) : ViewModel() {

    val allScheduleItems = repository.allScheduleItems.asLiveData()

    fun insert(item: ScheduleItem) = viewModelScope.launch {
        repository.insert(item)
    }

    fun delete(item: ScheduleItem) = viewModelScope.launch {
        repository.delete(item)
    }
}

class ScheduleViewModelFactory(private val repository: ScheduleRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ScheduleViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ScheduleViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}