package com.movers.dobcalc

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    private var tvSelectedDate : TextView? = null
    private var timeinMin : TextView? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val btnDatePicker = findViewById<Button>(R.id.buttonDatePicker)

        tvSelectedDate = findViewById(R.id.tvSelectedDate)
        timeinMin = findViewById(R.id.timeinMin)

        btnDatePicker.setOnClickListener {
           clickDatePicker()
        }

    }

    private fun clickDatePicker() {

        val myCalender = Calendar.getInstance()
        val year = myCalender.get(Calendar.YEAR)
        val month = myCalender.get(Calendar.MONTH)
        val day = myCalender.get(Calendar.DAY_OF_MONTH)



        val dpd = DatePickerDialog(this,
             {
                view ,year, month, dayOfMonth ->
                Toast.makeText(this,"Clicked $year",Toast.LENGTH_SHORT).show()

                 val selectedDate = "$dayOfMonth/${month+1}/$year"

                 tvSelectedDate?.text = selectedDate

                 val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
                 val theDate = sdf.parse(selectedDate)

                 theDate?.let {
                     val selectedDateinMin = theDate.time / 60000

                     val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))

                     val currentDateinMin = currentDate.time / 60000

                     val differenceinMin = currentDateinMin - selectedDateinMin

                     timeinMin?.text = differenceinMin.toString()
                 }


            }, year, month, day
            )

        dpd.datePicker.maxDate = System.currentTimeMillis()
        dpd.show()


    }
}

