package com.example.alleychaggar_comp304lab4

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Patient::class, Test::class, Nurse::class], version = 1, exportSchema = false)
public abstract class HospitalRoomDatabase : RoomDatabase() {
    abstract fun patientDao(): PatientDao
    abstract fun testDao(): TestDao
    abstract fun nurseDao(): NurseDao

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: HospitalRoomDatabase? = null

    fun getDatabase(context: Context): HospitalRoomDatabase {
        // if the INSTANCE is not null, then return it,
        // if it is, then create the database
        return INSTANCE ?: synchronized(this) {
            val instance = Room.databaseBuilder(
                context.applicationContext,
                HospitalRoomDatabase::class.java,
                "hospital_database"
            ).build()
            INSTANCE = instance
            // return instance
            instance
        }
    }
}

}