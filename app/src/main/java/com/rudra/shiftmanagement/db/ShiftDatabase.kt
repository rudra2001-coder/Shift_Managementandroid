package com.rudra.shiftmanagement.db


import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.rudra.shiftmanagement.model.Shift

@Database(entities = [Shift::class], version = 1, exportSchema = false)
abstract class ShiftDatabase : RoomDatabase() {
    abstract fun shiftDao(): ShiftDao

    companion object {
        @Volatile
        private var INSTANCE: ShiftDatabase? = null

        fun getDatabase(context: Context): ShiftDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ShiftDatabase::class.java,
                    "shift_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}