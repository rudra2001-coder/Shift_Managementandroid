package com.rudra.shiftmanagement.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.rudra.shiftmanagement.model.Shift

@Dao
interface ShiftDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertShift(shift: Shift)

    @Update
    suspend fun updateShift(shift: Shift)

    @Delete
    suspend fun deleteShift(shift: Shift)

    @Query("SELECT * FROM shifts ORDER BY shiftDate DESC, startTime ASC")
    fun getAllShifts(): LiveData<List<Shift>>

    @Query("SELECT * FROM shifts WHERE id = :shiftId")
    fun getShiftById(shiftId: Int): LiveData<Shift>
}