package com.example.minutes_lived_calculator

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    //only accessible in this class
    private var tvSelectedDate : TextView? = null
    private var tvTotalMinutes : TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnDatePicker: Button = findViewById(R.id.dateBtn)
        tvSelectedDate = findViewById(R.id.dateView)
        tvTotalMinutes = findViewById(R.id.minutesView)
        btnDatePicker.setOnClickListener {
            clickDatePicker()
        }
    }


    fun clickDatePicker(){

        val myCalendar = Calendar.getInstance() //part of Java.util
        val year = myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MONTH)
        val day = myCalendar.get(Calendar.DAY_OF_MONTH)

        //This will bring up the calender in the application and when you click on it, it will
        //execute what is onDateSetListener
        DatePickerDialog(this,
            DatePickerDialog.OnDateSetListener{view,selectedYear,selectedMonth,selectedDayOfMOnth ->
                Toast.makeText(this,"Year: $selectedYear, Month: ${selectedMonth+1}, Day: $selectedDayOfMOnth", Toast.LENGTH_LONG).show()
                val selectedDate = "$selectedDayOfMOnth/${selectedMonth+1}/$selectedYear"
                tvSelectedDate?.text = selectedDate

                //This will create a SimpleDateFormat with the pattern and locale provide
                //With this SDF we can then get a date
                val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
                val theDate = sdf.parse(selectedDate)


            },
            year, month,day).show()


    }
}