package com.example.nagomispaapp

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class staff_sched : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_staff_sched)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Initialize RecyclerView
        val recyclerView = findViewById<RecyclerView>(R.id.staffScheduleRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // TWO DIFFERENT STAFF EXAMPLES
        val staffData = listOf(
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

        recyclerView.adapter = StaffScheduleAdapter(staffData)
    }
}
