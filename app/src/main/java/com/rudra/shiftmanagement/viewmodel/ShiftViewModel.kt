package com.rudra.shiftmanagement.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rudra.shiftmanagement.model.Shift
import com.rudra.shiftmanagement.repository.ShiftRepository
import kotlinx.coroutines.launch
import androidx.lifecycle.ViewModelProvider


class ShiftViewModelFactory(private val repository: ShiftRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ShiftViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ShiftViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
class ShiftViewModel(private val repository: ShiftRepository) : ViewModel() {
    val allShifts: LiveData<List<Shift>> = repository.allShifts

    fun insert(shift: Shift) = viewModelScope.launch {
        repository.insert(shift)
    }

    fun update(shift: Shift) = viewModelScope.launch {
        repository.update(shift)
    }

    fun delete(shift: Shift) = viewModelScope.launch {
        repository.delete(shift)
    }

    fun getShiftById(shiftId: Int): LiveData<Shift> {
        return repository.getShiftById(shiftId)
    }
}