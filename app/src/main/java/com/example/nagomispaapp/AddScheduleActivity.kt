package com.example.nagomispaapp

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import java.io.BufferedReader
import java.io.InputStreamReader
import java.text.SimpleDateFormat
import java.util.*

class AddScheduleActivity : AppCompatActivity() {

    private lateinit var staffSpinner: Spinner
    private lateinit var clientNameInput: TextInputEditText
    private lateinit var staffNumberInput: TextInputEditText
    private lateinit var dateInput: TextInputEditText
    private lateinit var timeInput: TextInputEditText
    private lateinit var serviceSpinner: Spinner
    private lateinit var saveScheduleButton: MaterialButton
    private lateinit var cancelButton: MaterialButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_schedule)

        // Initialize views
        staffSpinner = findViewById(R.id.staffSpinner)
        clientNameInput = findViewById(R.id.clientNameInput)
        staffNumberInput = findViewById(R.id.staffNumberInput)
        dateInput = findViewById(R.id.dateInput)
        timeInput = findViewById(R.id.timeInput)
        serviceSpinner = findViewById(R.id.serviceSpinner)
        saveScheduleButton = findViewById(R.id.saveScheduleButton)
        cancelButton = findViewById(R.id.cancelButton)

        // Load staff list from CSV
        loadStaffList()

        // Setup service spinner
        setupServiceSpinner()

        // Date picker
        dateInput.setOnClickListener {
            showDatePicker()
        }

        // Time picker
        timeInput.setOnClickListener {
            showTimePicker()
        }

        // Save button
        saveScheduleButton.setOnClickListener {
            saveSchedule()
        }

        // Cancel button
        cancelButton.setOnClickListener {
            finish()
        }
    }

    private fun loadStaffList() {
        try {
            val inputStream = assets.open("staff_list.csv")
            val reader = BufferedReader(InputStreamReader(inputStream))

            val staffList = mutableListOf<String>()
            reader.forEachLine { line ->
                if (!line.startsWith("Name")) { // Skip header
                    val columns = line.split(",")
                    if (columns.isNotEmpty()) {
                        staffList.add(columns[0].trim())
                    }
                }
            }

            val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, staffList)
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            staffSpinner.adapter = adapter

        } catch (e: Exception) {
            Toast.makeText(this, "Error loading staff list: ${e.message}", Toast.LENGTH_SHORT).show()
        }
    }

    private fun setupServiceSpinner() {
        val services = arrayOf(
            "Massage Therapy",
            "Nail Care",
            "Facial Treatment",
            "Body Scrub",
            "Aromatherapy",
            "Hot Stone Massage",
            "Manicure",
            "Pedicure"
        )

        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, services)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        serviceSpinner.adapter = adapter
    }

    private fun showDatePicker() {
        val calendar = Calendar.getInstance()
        val datePickerDialog = DatePickerDialog(
            this,
            { _, year, month, dayOfMonth ->
                val selectedDate = String.format("%02d/%02d/%d", month + 1, dayOfMonth, year)
                dateInput.setText(selectedDate)
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )
        datePickerDialog.show()
    }

    private fun showTimePicker() {
        val calendar = Calendar.getInstance()
        val timePickerDialog = TimePickerDialog(
            this,
            { _, hourOfDay, minute ->
                val selectedTime = String.format("%02d:%02d", hourOfDay, minute)
                timeInput.setText(selectedTime)
            },
            calendar.get(Calendar.HOUR_OF_DAY),
            calendar.get(Calendar.MINUTE),
            false
        )
        timePickerDialog.show()
    }

    private fun saveSchedule() {
        val staffName = staffSpinner.selectedItem?.toString() ?: ""
        val clientName = clientNameInput.text.toString()
        val staffNumber = staffNumberInput.text.toString()
        val date = dateInput.text.toString()
        val time = timeInput.text.toString()
        val service = serviceSpinner.selectedItem?.toString() ?: ""

        // Validation
        if (staffName.isEmpty() || clientName.isEmpty() || staffNumber.isEmpty() ||
            date.isEmpty() || time.isEmpty() || service.isEmpty()) {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
            return
        }

        // TODO: Save schedule to database or storage
        Toast.makeText(this, "Schedule saved successfully!", Toast.LENGTH_SHORT).show()
        finish()
    }
}