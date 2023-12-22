package com.example.alleychaggar_comp304lab4

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "nurse_table")
data class Nurse(
    @PrimaryKey(autoGenerate = true) val nurseId: Long = 0,
    @ColumnInfo val firstname: String,
    @ColumnInfo val lastname: String,
    @ColumnInfo val department: String,
    @ColumnInfo val password: String
)