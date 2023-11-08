package com.example.leaseorrenthomeinc

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlin.random.Random

class AvailableHomesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_available_homes)

        //val homeListLayout = findViewById<LinearLayout>(R.id.homeListLayout)
        val backButton = findViewById<Button>(R.id.backButton)
        val paymentButton = findViewById<Button>(R.id.paymentButton)

        // Generate and display 3 randomly generated homes
        for (i in 1..3) {
            val homeAddress = generateRandomAddress()
            val homePrice = generateRandomPrice()
            val homeInfo = "$homeAddress\nPrice: $${homePrice.format(2)}"

            val textView = TextView(this)
            textView.text = homeInfo
            textView.textSize = 18f
           // homeListLayout.addView(textView)
        }

        backButton.setOnClickListener {
            val intent = Intent(this, HomeTypesActivity::class.java)
            startActivity(intent)
        }

        paymentButton.setOnClickListener {
            val intent = Intent(this, PaymentActivity::class.java)
            startActivity(intent)
        }
    }

    private fun generateRandomAddress(): String {
        val addresses = listOf(
            "123 Maple Avenue",
            "123 Oakwood Drive",
            "123 Cedar Street",
            "123 Riverside Drive",
            "123 Valley Drive"
        )
        return addresses.random()
    }

    private fun generateRandomPrice(): Double {
        // Generate a random price between $250,000 and $600,000
        return Random.nextDouble(250000.0, 600000.0)
    }

    private fun generateRandomImage(): Double {
        // Generate a random price between $250,000 and $600,000
        return Random.nextDouble(250000.0, 600000.0)
    }

    private fun Double.format(digits: Int) = "%.${digits}f".format(this)
}
