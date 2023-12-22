package com.example.alleychaggar_comp304lab4

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface TestDao {
    @Insert
    fun insertTest(test: Test): Long

    @Query("SELECT * FROM test_table")
    fun getAllTests(): LiveData<List<Test>>

    @Query("SELECT * FROM test_table WHERE patientId = :patientId")
    fun getTestsForPatient(patientId: Long): LiveData<List<Test>>

    @Update
    fun updateTest(test: Test): Int

    @Delete
    fun deleteTest(test: Test): Int
}
