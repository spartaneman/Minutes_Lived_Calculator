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


    private fun clickDatePicker(){

        val myCalendar = Calendar.getInstance() //part of Java.util
        val year = myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MONTH)
        val day = myCalendar.get(Calendar.DAY_OF_MONTH)

        //We can assign a val as the datePickerDialog
        val dpd = //This will bring up the calender in the application and when you click on it, it will
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
                    //This makes sure the date is safe and not empty
                    //helps prevent crashing
                    theDate?.let {
                        //This will return the number of milliseconds that have passed since 1970
                        //To get it into minutes we must divide by 60000
                        val selectedDateInMinutes = theDate.time/60000

                        //get the current date in milliseconds
                        val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))
                        //still must get time
                        currentDate?.let {
                            val currentMinutes = currentDate.time/60000

                            //calculate different in minutes
                            val minutesPassed = currentMinutes - selectedDateInMinutes

                            tvTotalMinutes?.text = minutesPassed.toString()
                        }

                    }



                },
                year, month,day)

        //By make the date picker a variable, we can use methods on it.
        //In this case I limit the max Date to be the current date.
        dpd.datePicker.maxDate = System.currentTimeMillis()
        dpd.show()

    }
}