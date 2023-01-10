package com.example.minutes_lived_calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnDatePicker: Button = findViewById(R.id.dateBtn)

        btnDatePicker.setOnClickListener {
            Toast.makeText(this,"Date picker button pressed", Toast.LENGTH_LONG).show()
        }
    }
}