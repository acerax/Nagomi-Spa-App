package com.example.nagomispaapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Immediately navigate to Login screen
        val intent = Intent(this, Login::class.java)
        startActivity(intent)
        finish()
    }
}