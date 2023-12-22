package com.example.alleychaggar_comp304lab4

class HospitalApplication {
    // Using by lazy so the database and the repository are only created when they're needed
    // rather than when the application starts
    val database by lazy { HospitalRepository.getDatabase(this) }
    val repository by lazy { HospitalRepository(database.patientDao(), database.testDao(), database.nurseDao()) }
}
