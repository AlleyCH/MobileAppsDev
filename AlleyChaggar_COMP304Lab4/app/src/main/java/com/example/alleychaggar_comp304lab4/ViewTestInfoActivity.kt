package com.example.alleychaggar_comp304lab4

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider

class ViewTestInfoActivity: AppCompatActivity()  {
    private lateinit var listView: ListView
    private lateinit var viewModel: HospitalViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_test_info)

        listView = findViewById(R.id.patientListView)
        val repository = (application as HospitalApplication).repository
        viewModel = HospitalViewModelFactory(repository).create(HospitalViewModel::class.java)

        listView.setOnItemClickListener { _, _, position, _ ->
            val selectedPatient = viewModel.allPatients.value?.get(position)
            selectedPatient?.let { showTestInfoDialog(it.patientId) }
        }
    }

    private fun showTestInfoDialog(patientId: Long) {
        // Fetch test info for the selected patient
        viewModel.getTestsForPatient(patientId).observe(this) { tests ->
            if (tests.isEmpty()) {
                // No test info found
                // This is possible
                AlertDialog.Builder(this)
                    .setTitle("No Test Information Found")
                    .setMessage("No test information found for patient $patientId")
                    .setPositiveButton("OK", null)
                    .show()
                return@observe
            }

            val testInfo = tests.joinToString("\n") {
                "Test ID: ${it.testId}, BPL: ${it.bpl}, BPH: ${it.bph}, Temp: ${it.temperature}"
            }
            // Show test info in a dialog
            AlertDialog.Builder(this)
                .setTitle("Test Information for Patient $patientId")
                .setMessage(testInfo)
                .setPositiveButton("OK", null)
                .show()
        }
    }
}
