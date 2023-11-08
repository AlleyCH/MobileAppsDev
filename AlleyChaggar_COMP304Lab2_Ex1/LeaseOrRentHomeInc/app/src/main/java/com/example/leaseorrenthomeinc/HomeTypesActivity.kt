package com.example.leaseorrenthomeinc

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.RadioGroup
import androidx.appcompat.app.AppCompatActivity

class HomeTypesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_types)

        val homeTypeRadioGroup = findViewById<RadioGroup>(R.id.homeTypeRadioGroup)



        homeTypeRadioGroup.setOnCheckedChangeListener { _, checkedId ->
            // Determine which radio button is selected
            when (checkedId) {
                R.id.radioApartment -> navigateToAvailableHomes("Apartment")
                R.id.radioDetachedHome -> navigateToAvailableHomes("Detached Home")
                R.id.radioSemiDetachedHome -> navigateToAvailableHomes("Semi-detached Home")
                R.id.radioCondominium -> navigateToAvailableHomes("Condominium")
                R.id.radioTownhouse -> navigateToAvailableHomes("Townhouse")
            }
        }
    }

    private fun navigateToAvailableHomes(homeType: String) {
        val intent = Intent(this, AvailableHomesActivity::class.java)
        intent.putExtra("homeType", homeType)
        startActivity(intent)
    }
}
