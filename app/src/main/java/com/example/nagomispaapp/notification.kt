package com.example.nagomispaapp

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.text.SimpleDateFormat
import java.util.*
import kotlin.apply
import kotlin.collections.forEach
import kotlin.collections.isNotEmpty
import kotlin.text.contains
import kotlin.text.isNullOrBlank

class notification : AppCompatActivity() {

    private val NOTIFICATION_PERMISSION_CODE = 100

    private lateinit var staffName1: EditText
    private lateinit var phone1: EditText
    private lateinit var date1: EditText
    private lateinit var time1: EditText

    private lateinit var staffName2: EditText
    private lateinit var phone2: EditText
    private lateinit var date2: EditText
    private lateinit var time2: EditText

    private lateinit var staffName3: EditText
    private lateinit var phone3: EditText
    private lateinit var date3: EditText
    private lateinit var time3: EditText

    private lateinit var btnEdit1: Button
    private lateinit var btnEdit2: Button
    private lateinit var btnEdit3: Button
    private lateinit var btnRemind1: Button
    private lateinit var btnRemind2: Button
    private lateinit var btnRemind3: Button
    private lateinit var btnRemindAll: Button
    private lateinit var btnScheduleDaily: Button

    private var editEnabled1 = false
    private var editEnabled2 = false
    private var editEnabled3 = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notification)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        bindViews()
        setTomorrowDates()
        wireClicks()

        // Ask notification permission for Android 13+
        requestPostNotificationsIfNeeded()
    }

    private fun bindViews() {
        staffName1 = findViewById(R.id.staffName1)
        phone1 = findViewById(R.id.phone1)
        date1 = findViewById(R.id.date1)
        time1 = findViewById(R.id.time1)

        staffName2 = findViewById(R.id.staffName2)
        phone2 = findViewById(R.id.phone2)
        date2 = findViewById(R.id.date2)
        time2 = findViewById(R.id.time2)

        staffName3 = findViewById(R.id.staffName3)
        phone3 = findViewById(R.id.phone3)
        date3 = findViewById(R.id.date3)
        time3 = findViewById(R.id.time3)

        btnEdit1 = findViewById(R.id.btnEdit1)
        btnEdit2 = findViewById(R.id.btnEdit2)
        btnEdit3 = findViewById(R.id.btnEdit3)
        btnRemind1 = findViewById(R.id.btnRemind1)
        btnRemind2 = findViewById(R.id.btnRemind2)
        btnRemind3 = findViewById(R.id.btnRemind3)
        btnRemindAll = findViewById(R.id.btnRemindAll)
        btnScheduleDaily = findViewById(R.id.btnScheduleDaily)
    }

    private fun setTomorrowDates() {
        val calendar = Calendar.getInstance().apply { add(Calendar.DATE, 1) }
        val dateFormat = SimpleDateFormat("MMMM dd, yyyy", Locale.getDefault())
        val tomorrow = dateFormat.format(calendar.time)

        // Pre-fill tomorrow's date if placeholders look like a date
        if (date1.text.isNullOrBlank() || date1.text.toString().contains("November")) date1.setText(tomorrow)
        if (date2.text.isNullOrBlank() || date2.text.toString().contains("November")) date2.setText(tomorrow)
        if (date3.text.isNullOrBlank() || date3.text.toString().contains("November")) date3.setText(tomorrow)
    }

    private fun wireClicks() {
        // Individual Edit Buttons for each card
        btnEdit1.setOnClickListener {
            editEnabled1 = !editEnabled1
            setCardEditable(staffName1, phone1, date1, time1, editEnabled1)
            Toast.makeText(
                this,
                if (editEnabled1) "Card 1 edit mode enabled" else "Card 1 edit mode disabled",
                Toast.LENGTH_SHORT
            ).show()
        }

        btnEdit2.setOnClickListener {
            editEnabled2 = !editEnabled2
            setCardEditable(staffName2, phone2, date2, time2, editEnabled2)
            Toast.makeText(
                this,
                if (editEnabled2) "Card 2 edit mode enabled" else "Card 2 edit mode disabled",
                Toast.LENGTH_SHORT
            ).show()
        }

        btnEdit3.setOnClickListener {
            editEnabled3 = !editEnabled3
            setCardEditable(staffName3, phone3, date3, time3, editEnabled3)
            Toast.makeText(
                this,
                if (editEnabled3) "Card 3 edit mode enabled" else "Card 3 edit mode disabled",
                Toast.LENGTH_SHORT
            ).show()
        }

        // Individual Remind buttons
        btnRemind1.setOnClickListener {
            sendReminder(
                staffName1.text.toString(),
                phone1.text.toString(),
                date1.text.toString(),
                time1.text.toString()
            )
        }

        btnRemind2.setOnClickListener {
            sendReminder(
                staffName2.text.toString(),
                phone2.text.toString(),
                date2.text.toString(),
                time2.text.toString()
            )
        }

        btnRemind3.setOnClickListener {
            sendReminder(
                staffName3.text.toString(),
                phone3.text.toString(),
                date3.text.toString(),
                time3.text.toString()
            )
        }

        // Remind All
        btnRemindAll.setOnClickListener {
            val items = listOf(
                Quartet(
                    staffName1.text.toString(),
                    phone1.text.toString(),
                    date1.text.toString(),
                    time1.text.toString()
                ),
                Quartet(
                    staffName2.text.toString(),
                    phone2.text.toString(),
                    date2.text.toString(),
                    time2.text.toString()
                ),
                Quartet(
                    staffName3.text.toString(),
                    phone3.text.toString(),
                    date3.text.toString(),
                    time3.text.toString()
                )
            )
            items.forEach {
                sendReminder(it.first, it.second, it.third, it.fourth)
            }
            Toast.makeText(this, "Reminders sent to all listed staff.", Toast.LENGTH_SHORT).show()
        }

        // Auto 6:00 PM scheduling
        btnScheduleDaily.setOnClickListener {
            AlarmScheduler.scheduleDailyReminder(this)
            Toast.makeText(this, "Auto reminder scheduled daily at 6:00 PM", Toast.LENGTH_SHORT).show()
        }
    }

    private fun setCardEditable(
        staffName: EditText,
        phone: EditText,
        date: EditText,
        time: EditText,
        enabled: Boolean
    ) {
        staffName.isEnabled = enabled
        phone.isEnabled = enabled
        date.isEnabled = enabled
        time.isEnabled = enabled
    }

    private fun requestPostNotificationsIfNeeded() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(
                    this,
                    Manifest.permission.POST_NOTIFICATIONS
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(Manifest.permission.POST_NOTIFICATIONS),
                    NOTIFICATION_PERMISSION_CODE
                )
            }
        }
    }

    // Sends notification to staff (admin sends, staff receives)
    private fun sendReminder(staff: String, phone: String, date: String, time: String) {
        NotificationUtils.showStaffReminder(
            context = this,
            title = "Reminder Sent to $staff",
            message = "Phone: $phone\nDate: $date\nTime: $time"
        )
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == NOTIFICATION_PERMISSION_CODE) {
            val granted = grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED
            Toast.makeText(
                this,
                if (granted) "Notification permission granted" else "Notification permission denied",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    // Helper data class for four values
    data class Quartet<A, B, C, D>(val first: A, val second: B, val third: C, val fourth: D)
}