package com.example.alleychaggar_comp304lab4

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class TestActivity: AppCompatActivity() {
    private lateinit var editTextPatientId: EditText
    private lateinit var editTextNurseId: EditText
    private lateinit var editTextBPL: EditText
    private lateinit var editTextBPH: EditText
    private lateinit var editTextTemperature: EditText
    private lateinit var buttonSaveTest: Button
    private lateinit var viewModel: HospitalViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)

        editTextPatientId = findViewById(R.id.editTextPatientId)
        editTextNurseId = findViewById(R.id.editTextNurseId)
        editTextBPL = findViewById(R.id.editTextBPL)
        editTextBPH = findViewById(R.id.editTextBPH)
        editTextTemperature = findViewById(R.id.editTextTemperature)
        buttonSaveTest = findViewById(R.id.buttonSaveTest)

        val repository = (application as HospitalApplication).repository
        viewModel = HospitalViewModelFactory(repository).create(HospitalViewModel::class.java)

        buttonSaveTest.setOnClickListener {
            saveTestData()
        }
    }

    private fun saveTestData() {
        val patientId = editTextPatientId.text.toString().toLongOrNull()
        val nurseId = editTextNurseId.text.toString().toLongOrNull()
        val bpl = editTextBPL.text.toString().toIntOrNull()
        val bph = editTextBPH.text.toString().toIntOrNull()
        val temperature = editTextTemperature.text.toString().toFloatOrNull()

        // Validate input here
        if (patientId == null || nurseId == null || bpl == null || bph == null || temperature == null) {
            Toast.makeText(this, "Invalid input", Toast.LENGTH_SHORT).show()
            return
        }

        // TODO: Add more validation here

        val test = Test(0, patientId, nurseId, bpl, bph, temperature)
        viewModel.insertTest(test)
        Toast.makeText(this, "Test saved", Toast.LENGTH_SHORT).show()

        // Back to previous activity
        finish()
    }
}

