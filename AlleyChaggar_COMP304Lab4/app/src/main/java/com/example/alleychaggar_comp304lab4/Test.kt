package com.example.alleychaggar_comp304lab4

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey


@Entity(tableName = "test_table", foreignKeys = [ForeignKey(entity = Nurse::class, parentColumns = ["nurseId"], childColumns = ["nurseId"], onDelete = ForeignKey.CASCADE),
    ForeignKey(entity = Patient::class, parentColumns = ["patientId"], childColumns = ["patientId"], onDelete = ForeignKey.CASCADE)])
class Test(
    @PrimaryKey(autoGenerate = true) val testId: Int,
    @ColumnInfo val patientId: Long?,
    @ColumnInfo val nurseId: Long?,
    @ColumnInfo val bpl: Int?,
    @ColumnInfo val bph: Int?,
    @ColumnInfo val temperature: Float?
)