package com.example.alleychaggar_comp304lab4

import androidx.lifecycle.LiveData

class HospitalRepository (private val patientDao: PatientDao, private val testDao: TestDao, private val nurseDao: NurseDao ){
    // Patient operations
    fun insertPatient(patient: Patient) {
        patientDao.insertPatient(patient)
    }

    fun getAllPatients(): LiveData<List<Patient>> {
        return patientDao.getAllPatients()
    }

    fun getPatientById(patientId: Long): LiveData<Patient> {
        return patientDao.getPatientById(patientId)
    }

    fun updatePatient(patient: Patient) {
        patientDao.updatePatient(patient)
    }

    fun deletePatient(patient: Patient) {
        patientDao.deletePatient(patient)
    }

    // Test operations
    fun insertTest(test: Test) {
        testDao.insertTest(test)
    }

    fun getAllTests(): LiveData<List<Test>> {
        return testDao.getAllTests()
    }

    fun getTestsForPatient(patientId: Long): LiveData<List<Test>> {
        return testDao.getTestsForPatient(patientId)
    }

    fun updateTest(test: Test) {
        testDao.updateTest(test)
    }

    fun deleteTest(test: Test) {
        testDao.deleteTest(test)
    }

    // Nurse operations
    fun insertNurse(nurse: Nurse) {
        nurseDao.insertNurse(nurse)
    }

    fun getAllNurses(): LiveData<List<Nurse>> {
        return nurseDao.getAllNurses()
    }

    fun getNurseById(nurseId: Long): LiveData<Nurse> {
        return nurseDao.getNurseById(nurseId)
    }

    fun updateNurse(nurse: Nurse) {
        nurseDao.updateNurse(nurse)
    }

    fun deleteNurse(nurse: Nurse) {
        nurseDao.deleteNurse(nurse)
    }

    companion object {
        fun getDatabase(hospitalApplication: HospitalApplication) {

        }
    }
}