package com.example.alleychaggar_comp304lab4

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.ComponentActivity

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    // Define methods to open other activities
    fun openLoginActivity(view: View) {
        startActivity(Intent(this, LoginActivity::class.java))
    }

    fun openPatientActivity(view: View) {
        if (getNurseId() < 0) {
            Toast.makeText(applicationContext, "Please login first", Toast.LENGTH_LONG).show()
            return
        }
        startActivity(Intent(this, PatientActivity::class.java))
    }

    fun openTestActivity(view: View) {
        if (getNurseId() < 0) {
            Toast.makeText(applicationContext, "Please login first", Toast.LENGTH_LONG).show()
            return
        }
        startActivity(Intent(this, TestActivity::class.java))
    }

    fun openViewTestInfoActivity(view: View) {
        if (getNurseId() < 0) {
            Toast.makeText(applicationContext, "Please login first", Toast.LENGTH_LONG).show()
            return
        }
        startActivity(Intent(this, ViewTestInfoActivity::class.java))
    }

    fun openUpdateInfoActivity(view: View) {
        if (getNurseId() < 0) {
            Toast.makeText(applicationContext, "Please login first", Toast.LENGTH_LONG).show()
            return
        }
        startActivity(Intent(this, DisplayInfoActivity::class.java))
    }

    private fun getNurseId(): Long {
        val sharedPref = getSharedPreferences("nurseId", MODE_PRIVATE)
        return sharedPref.getLong("nurseId", -1)
    }
}