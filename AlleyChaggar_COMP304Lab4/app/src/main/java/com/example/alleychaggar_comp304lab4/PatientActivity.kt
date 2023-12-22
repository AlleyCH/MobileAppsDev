package com.example.alleychaggar_comp304lab4

import android.os.Bundle
import android.view.LayoutInflater
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PatientActivity : AppCompatActivity() {
    private lateinit var listView: ListView
    private lateinit var addPatientButton: Button
    private lateinit var viewModel: HospitalViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_patient)

        listView = findViewById(R.id.patientListView)
        addPatientButton = findViewById(R.id.addPatientButton)

        val repository = (application as HospitalApplication).repository
        viewModel = ViewModelProvider(this, HospitalViewModelFactory(repository))[HospitalViewModel::class.java]

        val adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, ArrayList())
        listView.adapter = adapter

        viewModel.allPatients.observe(this) { patients ->
            adapter.clear()
            adapter.addAll(patients.map { "${it.patientId}, ${it.firstname} ${it.lastname}, ${it.department}" })
        }

        addPatientButton.setOnClickListener {
            CoroutineScope(Dispatchers.Main).launch {
                showAddPatientDialog()
            }
        }
    }

    private fun showAddPatientDialog() {
        val dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_add_patient, null)
        val builder = AlertDialog.Builder(this)
            .setView(dialogView)
            .setTitle("Add New Patient")
            .setNegativeButton("Cancel", null)
            .setPositiveButton("Add", null)

        val alertDialog = builder.show()

        alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener {
            // Retrieve input data from dialogView
            val firstName = dialogView.findViewById<EditText>(R.id.editTextFirstName).text.toString()
            val lastName = dialogView.findViewById<EditText>(R.id.editTextLastName).text.toString()
            val department = dialogView.findViewById<EditText>(R.id.editTextDepartment).text.toString()
            val nurseId = dialogView.findViewById<EditText>(R.id.editTextNurseId).text.toString().toLongOrNull() ?: -1
            val room = dialogView.findViewById<EditText>(R.id.editTextRoom).text.toString()

            // Do nothing if basic validation fails
            if (!validateInput(firstName, lastName, department, nurseId, room)) {
                return@setOnClickListener
            }

            // Check if nurse exists
            viewModel.getNurseById(nurseId).observe(this) { nurse ->
                if (nurse == null) {
                    Toast.makeText(this, "Nurse with ID $nurseId does not exist", Toast.LENGTH_SHORT).show()
                    return@observe
                }

                val newPatient = Patient(
                    firstname = firstName,
                    lastname = lastName,
                    department = department,
                    nurseId = nurseId,
                    room = room
                )
                viewModel.insertPatient(newPatient)
                alertDialog.dismiss()
            }
        }
    }

    private fun validateInput(firstName: String, lastName: String, department: String, nurseId: Long, room: String): Boolean {
        if (firstName.isBlank()) {
            Toast.makeText(this, "First name cannot be blank", Toast.LENGTH_SHORT).show()
            return false
        }

        if (lastName.isBlank()) {
            Toast.makeText(this, "Last name cannot be blank", Toast.LENGTH_SHORT).show()
            return false
        }

        if (department.isBlank()) {
            Toast.makeText(this, "Department cannot be blank", Toast.LENGTH_SHORT).show()
            return false
        }

        if (nurseId.toLong() == -1L) {
            Toast.makeText(this, "Nurse ID cannot be blank", Toast.LENGTH_SHORT).show()
            return false
        }

        if (room.isBlank()) {
            Toast.makeText(this, "Room cannot be blank", Toast.LENGTH_SHORT).show()
            return false
        }

        return true
    }
}
