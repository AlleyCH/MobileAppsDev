package com.example.leaseorrenthomeinc

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class PaymentActivity: AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_payment)

    val creditRadioButton = findViewById<RadioButton>(R.id.creditRadioButton)
    val debitRadioButton = findViewById<RadioButton>(R.id.debitRadioButton)
    val cashRadioButton = findViewById<RadioButton>(R.id.cashRadioButton)

    val creditDebitFormLayout = findViewById<View>(R.id.creditDebitFormLayout)
    val cashMessage = findViewById<View>(R.id.cashMessage)
    val backButton = findViewById<Button>(R.id.backButton)

    creditRadioButton.setOnClickListener {
        // Show credit/debit card form
        creditDebitFormLayout.visibility = View.VISIBLE
        cashMessage.visibility = View.GONE
    }

    cashRadioButton.setOnClickListener {
        // Show cash message
        cashMessage.visibility = View.VISIBLE
        creditDebitFormLayout.visibility = View.GONE
    }

    backButton.setOnClickListener {
        finish() // Finish this activity and return to AvailableHomesActivity
    }
  }
}