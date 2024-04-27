package com.example.taskandroid

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.DatePickerDialog
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.ImageView
import android.widget.RadioButton
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import java.util.Calendar

class TaskIshan : AppCompatActivity() {

    private lateinit var imageView: ImageView
    private lateinit var buttonDate: Button

    private lateinit var textViewDate: TextView

    private lateinit var autoComplete: AutoCompleteTextView

    private lateinit var radioButtonMale : RadioButton
    private lateinit var radioButtonFemale : RadioButton

    private lateinit var saveButton : Button

    var states = arrayOf("Province.1", "Province.2", "Province.3", "Province.4", "Province.5", "Province.6", "Province.7")


    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main2)

        imageView= findViewById(R.id.imageView)

        radioButtonMale = findViewById(R.id.rButtonMale)
        radioButtonFemale = findViewById(R.id.rButtonFemale)

        buttonDate = findViewById(R.id.tVDateShow)
        textViewDate = findViewById(R.id.btnDatePick)
        autoComplete = findViewById(R.id.autoComplete)

        //radio button functionality
        radioButtonMale.setOnClickListener {
            imageView.setImageResource(R.drawable.ic_launcher_background)  //TODO: change the image
        }

        radioButtonFemale.setOnClickListener {
            imageView.setImageResource(R.drawable.dut)
        }

        /*SPINNER*/
        /*access the items of the list*/
        val languages = resources.getStringArray(R.array.Languages)

        // access the spinner
        val spinner = findViewById<Spinner>(R.id.spinner)
        if (spinner != null) {
            val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, languages)
            spinner.adapter = adapter

/*            spinner.onItemSelectedListener = object :
                AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>,
                    view: View,
                    position: Int,
                    id: Long
                ) {
                    Toast.makeText(
                        this@TaskIshan,
                        getString(R.string.selected_item) + " " +
                                "" + languages[position], Toast.LENGTH_SHORT
                    ).show()
                }
            }*/
        }
        // date picker functionality implementation
        buttonDate.setOnClickListener {
            loadCalendar()
        }

        //autocomplete
        autoComplete = findViewById(R.id.autoComplete)

        val autoCompleteAdapter = ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, states)
        autoComplete.setAdapter(autoCompleteAdapter)
        autoComplete.threshold = 1   //todo: add a functionality to show toast message of selected autocomplete option

        //save button
        saveButton = findViewById(R.id.btnSave)

        saveButton.setOnClickListener {
            val alertDialog = AlertDialog.Builder(this)
            alertDialog.setTitle("Save Changes")
            alertDialog.setMessage("Are you sure you want to save changes?")

            alertDialog.setPositiveButton("Yes") { _, _ ->
                // Code to save changes
                Toast.makeText(this, "Changes saved successfully", Toast.LENGTH_SHORT).show()
            }

            alertDialog.setNegativeButton("No") { _, _ ->
                // Code to cancel changes
                Toast.makeText(this, "Changes cancelled", Toast.LENGTH_SHORT).show()
            }

            alertDialog.show()
        }

    }

    private fun loadCalendar() {
        var c = Calendar.getInstance()
        var year = c.get(Calendar.YEAR)
        var month = c.get(Calendar.MONTH)
        var day = c.get(Calendar.DAY_OF_MONTH)
        var dateDialog = DatePickerDialog(this,
            DatePickerDialog.OnDateSetListener { datePicker, yy, mm, dd -> textViewDate.text = "$yy/$mm/$dd" },
            year, month,day)

        dateDialog.show()
    }

}