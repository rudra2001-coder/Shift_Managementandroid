package com.rudra.shiftmanagement.repository


import androidx.lifecycle.LiveData
import com.rudra.shiftmanagement.db.ShiftDao
import com.rudra.shiftmanagement.model.Shift

class ShiftRepository(private val shiftDao: ShiftDao) {
    val allShifts: LiveData<List<Shift>> = shiftDao.getAllShifts()

    suspend fun insert(shift: Shift) {
        shiftDao.insertShift(shift)
    }

    suspend fun update(shift: Shift) {
        shiftDao.updateShift(shift)
    }

    suspend fun delete(shift: Shift) {
        shiftDao.deleteShift(shift)
    }

    fun getShiftById(shiftId: Int): LiveData<Shift> {
        return shiftDao.getShiftById(shiftId)
    }
}