package com.example.alleychaggar_comp304lab4

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "patient_table", foreignKeys = [ForeignKey(entity = Nurse::class, parentColumns = ["nurseId"], childColumns = ["nurseId"], onDelete = ForeignKey.CASCADE)])
class Patient(
    @PrimaryKey(autoGenerate = true) val patientId: Long = 0,
    @ColumnInfo val firstname: String,
    @ColumnInfo val lastname: String,
    @ColumnInfo val department: String,
    @ColumnInfo val nurseId: Long,
    @ColumnInfo val room: String
)