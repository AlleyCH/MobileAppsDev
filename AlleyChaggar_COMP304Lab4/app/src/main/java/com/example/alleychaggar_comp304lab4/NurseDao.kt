package com.example.alleychaggar_comp304lab4

import androidx.lifecycle.LiveData
import androidx.room.Insert
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Update

@Dao
interface NurseDao {
    @Insert
    fun insertNurse(nurse: Nurse): Long

    @Query("SELECT * FROM nurse_table")
    fun getAllNurses(): LiveData<List<Nurse>>

    @Query("SELECT * FROM nurse_table WHERE nurseId = :nurseId")
    fun getNurseById(nurseId: Long): LiveData<Nurse>

    @Query("SELECT * FROM nurse_table WHERE nurseId = :nurseId AND password = :password")
    fun getNurseByCredentials(nurseId: Long, password: String): Nurse?

    @Update
    fun updateNurse(nurse: Nurse): Int

    @Delete
    fun deleteNurse(nurse: Nurse): Int

    @Query("DELETE FROM nurse_table")
    fun deleteAllNurses()
}
