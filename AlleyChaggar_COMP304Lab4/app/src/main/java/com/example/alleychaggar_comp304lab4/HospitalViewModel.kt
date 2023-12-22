package com.example.alleychaggar_comp304lab4

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HospitalViewModel(private val repository: HospitalRepository) : ViewModel() {
    // Using LiveData and caching what allWords returns has several benefits:
    // - We can put an observer on the data (instead of polling for changes) and only update the
    //   the UI when the data actually changes.
    // - Repository is completely separated from the UI through the ViewModel.
    val allPatients: LiveData<List<Patient>> = repository.getAllPatients()

    fun insertPatient(patient: Patient) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertPatient(patient)
        }
    }

    fun getPatientById(patientId: Long): LiveData<Patient> {
        return repository.getPatientById(patientId)
    }

    fun updatePatient(patient: Patient) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updatePatient(patient)
        }
    }

    fun deletePatient(patient: Patient) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deletePatient(patient)
        }
    }

    // Test operations
    val allTests: LiveData<List<Test>> = repository.getAllTests()

    fun insertTest(test: Test) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertTest(test)
        }
    }

    fun getTestsForPatient(patientId: Long): LiveData<List<Test>> {
        return repository.getTestsForPatient(patientId)
    }

    fun updateTest(test: Test) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateTest(test)
        }
    }

    fun deleteTest(test: Test) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteTest(test)
        }
    }

    // Nurse operations
    val allNurses: LiveData<List<Nurse>> = repository.getAllNurses()

    fun insertNurse(nurse: Nurse) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertNurse(nurse)
        }
    }

    fun getNurseById(nurseId: Long): LiveData<Nurse> {
        return repository.getNurseById(nurseId)
    }

    fun updateNurse(nurse: Nurse) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateNurse(nurse)
        }
    }

    fun deleteNurse(nurse: Nurse) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteNurse(nurse)
        }
    }
}


class HospitalViewModelFactory(private val repository: HospitalRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HospitalViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return HospitalViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
