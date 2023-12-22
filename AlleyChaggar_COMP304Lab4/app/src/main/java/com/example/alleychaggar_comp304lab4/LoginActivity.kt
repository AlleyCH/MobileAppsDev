package com.example.alleychaggar_comp304lab4

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.*

class LoginActivity : AppCompatActivity() {

    private lateinit var nurseIdEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var loginButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        nurseIdEditText = findViewById(R.id.nurseIdEditText)
        passwordEditText = findViewById(R.id.passwordEditText)
        loginButton = findViewById(R.id.loginButton)

        loginButton.setOnClickListener {
            val nurseId = nurseIdEditText.text.toString().toLong()
            val password = passwordEditText.text.toString()
            login(nurseId, password)
        }
    }

    private fun login(nurseId: Long, password: String) {
        // Assuming you have a viewModel to handle login logic
        // Replace with your actual login logic
        CoroutineScope(Dispatchers.IO).launch {
            val isValid = checkCredentials(nurseId, password)
            withContext(Dispatchers.Main) {
                if (isValid) {
                    saveNurseId(nurseId)
                    Toast.makeText(applicationContext, "Login successful", Toast.LENGTH_LONG).show()
                    finish()
                } else {
                    Toast.makeText(applicationContext, "Invalid credentials", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    private fun checkCredentials(nurseId: Long, password: String): Boolean {
        val database = HospitalRoomDatabase.getDatabase(applicationContext)
        val nurse = database.nurseDao().getNurseByCredentials(nurseId, password)
        return nurse != null
    }

    private fun saveNurseId(nurseId: Long) {
        val sharedPref = getSharedPreferences("nurseId", MODE_PRIVATE)
        with (sharedPref.edit()) {
            putLong("nurseId", nurseId)
            apply()
        }
    }
}

