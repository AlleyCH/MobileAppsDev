package com.example.leaseorrenthomeinc

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun enterBtnClicked(view: View?) {
        if (view == null) return
        startActivity(Intent(this, HomeTypesActivity::class.java))
    }
}