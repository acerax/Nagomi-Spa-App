package com.example.nagomispaapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        // Notification Icon Button (Upper Left)
        val notificationIconBtn = findViewById<ImageButton>(R.id.notificationIconBtn)
        notificationIconBtn.setOnClickListener {
            try {
                val intent = Intent(this, notification::class.java)
                startActivity(intent)
            } catch (e: Exception) {
                Toast.makeText(
                    this,
                    "Error opening Notifications: ${e.message}",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        // Profile Icon Button (Upper Right)
        val profileIconBtn = findViewById<ImageButton>(R.id.profileIconBtn)
        profileIconBtn.setOnClickListener {
            try {
                val intent = Intent(this, profile::class.java)
                startActivity(intent)
            } catch (e: Exception) {
                Toast.makeText(
                    this,
                    "Error opening Profile: ${e.message}",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        // Staff Schedule Button
        val staffScheduleBtn = findViewById<Button>(R.id.staffScheduleBtn)
        staffScheduleBtn.setOnClickListener {
            try {
                val intent = Intent(this, staff_sched::class.java)
                startActivity(intent)
            } catch (e: Exception) {
                Toast.makeText(
                    this,
                    "Error opening Staff Schedule: ${e.message}",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}