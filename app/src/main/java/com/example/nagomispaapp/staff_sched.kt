package com.example.nagomispaapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton

class staff_sched : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_staff_sched)

        // Initialize RecyclerView
        val recyclerView = findViewById<RecyclerView>(R.id.staffScheduleRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Sample schedule data
        val scheduleData = listOf(
            StaffModel(
                initials = "CS",
                name = "Clea Santos",
                role = "Massage Therapist",
                clientName = "Sarah Lopez",
                monday = "9:00 AM - 5:00 PM",
                tuesday = "9:00 AM - 5:00 PM",
                weekend = "Off",
                totalHours = "40 hrs/week",
                status = "Active"
            ),
            StaffModel(
                initials = "JM",
                name = "Jenna Mae Cruz",
                role = "Nail Specialist",
                clientName = "Rina Dela Vega",
                monday = "10:00 AM - 6:00 PM",
                tuesday = "Off",
                weekend = "11:00 AM - 4:00 PM",
                totalHours = "30 hrs/week",
                status = "On Leave"
            )
        )

        recyclerView.adapter = StaffScheduleAdapter(scheduleData)

        // Add Schedule button - Navigate to AddScheduleActivity
        findViewById<MaterialButton>(R.id.addScheduleButton).setOnClickListener {
            startActivity(Intent(this, AddScheduleActivity::class.java))
        }
    }
}