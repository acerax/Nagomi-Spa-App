package com.example.nagomispaapp

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class NotificationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notification)

        setupCard1()
        setupCard2()
        setupCard3()
        setupGlobalActions()
    }

    private fun setupCard1() {
        val staffName1 = findViewById<EditText>(R.id.staffName1)
        val phone1 = findViewById<EditText>(R.id.phone1)
        val date1 = findViewById<EditText>(R.id.date1)
        val time1 = findViewById<EditText>(R.id.time1)
        val btnEdit1 = findViewById<Button>(R.id.btnEdit1)
        val btnRemind1 = findViewById<Button>(R.id.btnRemind1)

        btnEdit1.setOnClickListener {
            toggleEditMode(staffName1, phone1, date1, time1, btnEdit1)
        }

        btnRemind1.setOnClickListener {
            sendReminder(
                staffName1.text.toString(),
                phone1.text.toString(),
                date1.text.toString(),
                time1.text.toString()
            )
        }
    }

    private fun setupCard2() {
        val staffName2 = findViewById<EditText>(R.id.staffName2)
        val phone2 = findViewById<EditText>(R.id.phone2)
        val date2 = findViewById<EditText>(R.id.date2)
        val time2 = findViewById<EditText>(R.id.time2)
        val btnEdit2 = findViewById<Button>(R.id.btnEdit2)
        val btnRemind2 = findViewById<Button>(R.id.btnRemind2)

        btnEdit2.setOnClickListener {
            toggleEditMode(staffName2, phone2, date2, time2, btnEdit2)
        }

        btnRemind2.setOnClickListener {
            sendReminder(
                staffName2.text.toString(),
                phone2.text.toString(),
                date2.text.toString(),
                time2.text.toString()
            )
        }
    }

    private fun setupCard3() {
        val staffName3 = findViewById<EditText>(R.id.staffName3)
        val phone3 = findViewById<EditText>(R.id.phone3)
        val date3 = findViewById<EditText>(R.id.date3)
        val time3 = findViewById<EditText>(R.id.time3)
        val btnEdit3 = findViewById<Button>(R.id.btnEdit3)
        val btnRemind3 = findViewById<Button>(R.id.btnRemind3)

        btnEdit3.setOnClickListener {
            toggleEditMode(staffName3, phone3, date3, time3, btnEdit3)
        }

        btnRemind3.setOnClickListener {
            sendReminder(
                staffName3.text.toString(),
                phone3.text.toString(),
                date3.text.toString(),
                time3.text.toString()
            )
        }
    }

    private fun setupGlobalActions() {
        val btnRemindAll = findViewById<Button>(R.id.btnRemindAll)
        val btnScheduleDaily = findViewById<Button>(R.id.btnScheduleDaily)

        btnRemindAll.setOnClickListener {
            sendAllReminders()
        }

        btnScheduleDaily.setOnClickListener {
            scheduleDailyReminders()
        }
    }

    private fun toggleEditMode(
        staffName: EditText,
        phone: EditText,
        date: EditText,
        time: EditText,
        btnEdit: Button
    ) {
        val isCurrentlyEnabled = staffName.isEnabled

        if (isCurrentlyEnabled) {
            // Save mode - disable editing
            staffName.isEnabled = false
            phone.isEnabled = false
            date.isEnabled = false
            time.isEnabled = false
            btnEdit.text = "Edit"
            Toast.makeText(this, "Changes saved", Toast.LENGTH_SHORT).show()
        } else {
            // Edit mode - enable editing
            staffName.isEnabled = true
            phone.isEnabled = true
            date.isEnabled = true
            time.isEnabled = true
            btnEdit.text = "Save"
        }
    }

    private fun sendReminder(staffName: String, phone: String, date: String, time: String) {
        val message = """
            Tomorrow's Shift Reminder
            
            Staff: $staffName
            Phone: $phone
            Date: $date
            Time: $time
        """.trimIndent()

        NotificationUtils.showStaffReminder(
            context = this,
            title = "Reminder for $staffName",
            message = message
        )

        Toast.makeText(this, "Reminder sent to $staffName", Toast.LENGTH_SHORT).show()
    }

    private fun sendAllReminders() {
        val staffName1 = findViewById<EditText>(R.id.staffName1).text.toString()
        val phone1 = findViewById<EditText>(R.id.phone1).text.toString()
        val date1 = findViewById<EditText>(R.id.date1).text.toString()
        val time1 = findViewById<EditText>(R.id.time1).text.toString()

        val staffName2 = findViewById<EditText>(R.id.staffName2).text.toString()
        val phone2 = findViewById<EditText>(R.id.phone2).text.toString()
        val date2 = findViewById<EditText>(R.id.date2).text.toString()
        val time2 = findViewById<EditText>(R.id.time2).text.toString()

        val staffName3 = findViewById<EditText>(R.id.staffName3).text.toString()
        val phone3 = findViewById<EditText>(R.id.phone3).text.toString()
        val date3 = findViewById<EditText>(R.id.date3).text.toString()
        val time3 = findViewById<EditText>(R.id.time3).text.toString()

        sendReminder(staffName1, phone1, date1, time1)
        sendReminder(staffName2, phone2, date2, time2)
        sendReminder(staffName3, phone3, date3, time3)

        Toast.makeText(this, "All reminders sent!", Toast.LENGTH_LONG).show()
    }

    private fun scheduleDailyReminders() {
        // TODO: Implement AlarmManager to schedule daily reminders at 6:00 PM
        Toast.makeText(
            this,
            "Auto reminders will be sent daily at 6:00 PM",
            Toast.LENGTH_LONG
        ).show()
    }
}