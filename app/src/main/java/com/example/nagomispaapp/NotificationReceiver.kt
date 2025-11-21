package com.example.nagomispaapp

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class NotificationReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        // In real app → fetch shifts from DB
        val appointments = getTomorrowShifts()

        if (appointments.isNotEmpty()) {
            appointments.forEach { s ->
                val msg = "Date: ${s.date}\nTime: ${s.time}"
                NotificationUtils.showStaffReminder(
                    context = context,
                    title = "Reminder for ${s.staffName}",
                    message = "Phone: ${s.phone}\n$msg"
                )
            }
        }
    }

    // temporary demo data
    private fun getTomorrowShifts(): List<Shift> {
        return listOf(
            Shift("Santos, Maria", "+63 912 345 6789", "November 10, 2025", "10:00 AM - 11:30 AM"),
            Shift("Reyes, Ana", "+63 923 456 7890", "November 10, 2025", "2:00 PM - 3:00 PM"),
            Shift("Garcia, Sofia", "+63 934 567 8901", "November 10, 2025", "4:00 PM - 5:00 PM")
        )
    }

    data class Shift(
        val staffName: String,
        val phone: String,
        val date: String,
        val time: String
    )
}