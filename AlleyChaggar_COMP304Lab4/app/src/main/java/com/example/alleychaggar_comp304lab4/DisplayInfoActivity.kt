package com.example.alleychaggar_comp304lab4

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

class DisplayInfoActivity: AppCompatActivity()  {
    private lateinit var listView: ListView
    private lateinit var viewModel: HospitalViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_test_info)

        listView = findViewById(R.id.patientListView)
        val repository = (application as HospitalApplication).repository
        viewModel = HospitalViewModelFactory(repository).create(HospitalViewModel::class.java)

        val adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, ArrayList())
        listView.adapter = adapter

        viewModel.allPatients.observe(this) { patients ->
            adapter.clear()
            adapter.addAll(patients.map { "${it.firstname} ${it.lastname}" })
        }

        listView.setOnItemClickListener { _, _, position, _ ->
            val selectedPatient = viewModel.allPatients.value?.get(position)
            selectedPatient?.let { showUpdatePatientDialog(it) }
        }
    }

    private fun showUpdatePatientDialog(patient: Patient) {
        val dialogView = layoutInflater.inflate(R.layout.update_patient, null)
        val firstNameEditText = dialogView.findViewById<EditText>(R.id.editTextUpdateFirstName)
        val lastNameEditText = dialogView.findViewById<EditText>(R.id.editTextUpdateLastName)
        val departmentEditText = dialogView.findViewById<EditText>(R.id.editTextUpdateDepartment)
        val roomEditText = dialogView.findViewById<EditText>(R.id.editTextUpdateRoom)
        val nurseIdEditText = dialogView.findViewById<EditText>(R.id.editTextUpdateNurseId)

        firstNameEditText.setText(patient.firstname)
        lastNameEditText.setText(patient.lastname)
        departmentEditText.setText(patient.department)
        roomEditText.setText(patient.room)
        nurseIdEditText.setText(patient.nurseId.toString())

        AlertDialog.Builder(this)
            .setView(dialogView)
            .setTitle("Update Patient Info")
            .setNegativeButton("Cancel", null)
            .setPositiveButton("Update") { _, _ ->
                // Validate input
                if (firstNameEditText.text.isEmpty() || lastNameEditText.text.isEmpty() ||
                    departmentEditText.text.isEmpty() || roomEditText.text.isEmpty() ||
                    nurseIdEditText.text.isEmpty()
                ) {
                    Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
                    return@setPositiveButton
                }

                // Update patient information in ViewModel
                val updatedPatient = patient.copy(
                    firstname = firstNameEditText.text.toString(),
                    lastname = lastNameEditText.text.toString(),
                    department = departmentEditText.text.toString(),
                    room = roomEditText.text.toString(),
                    nurseId = nurseIdEditText.text.toString().toLong()
                )
                viewModel.updatePatient(updatedPatient)
            }
            .show()
    }
}