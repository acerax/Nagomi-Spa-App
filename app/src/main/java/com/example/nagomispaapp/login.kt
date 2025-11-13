package com.example.nagomispaapp

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText

class Login : AppCompatActivity() {

    // Hardcoded credentials
    private val VALID_EMAIL = "admin@nagomispa.com"
    private val VALID_PASSWORD = "admin123"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // Apply window insets
        val rootLayout = findViewById<ConstraintLayout>(R.id.contentContainer)
        ViewCompat.setOnApplyWindowInsetsListener(rootLayout) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(
                v.paddingLeft,
                systemBars.top,
                v.paddingRight,
                v.paddingBottom
            )
            insets
        }

        // Get references to input fields
        val emailInput = findViewById<TextInputEditText>(R.id.emailInput)
        val passwordInput = findViewById<TextInputEditText>(R.id.passwordInput)
        val loginButton = findViewById<MaterialButton>(R.id.loginButton)

        // Sign In button click listener
        loginButton.setOnClickListener {
            val email = emailInput.text.toString().trim()
            val password = passwordInput.text.toString().trim()

            // Validate inputs
            if (email.isEmpty()) {
                emailInput.error = "Email is required"
                emailInput.requestFocus()
                return@setOnClickListener
            }

            if (password.isEmpty()) {
                passwordInput.error = "Password is required"
                passwordInput.requestFocus()
                return@setOnClickListener
            }

            // Validate against hardcoded credentials
            if (email == VALID_EMAIL && password == VALID_PASSWORD) {
                // Show success message
                Toast.makeText(
                    this,
                    "Login successful! Welcome back!",
                    Toast.LENGTH_SHORT
                ).show()

                // Navigate to HomeActivity
                val intent = Intent(this, HomeActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
                finish()
            } else {
                // Show error message for invalid credentials
                Toast.makeText(
                    this,
                    "Invalid email or password. Please try again.",
                    Toast.LENGTH_LONG
                ).show()

                // Clear password field for security
                passwordInput.text?.clear()
                passwordInput.requestFocus()
            }
        }

        // Forgot Password click listener
        findViewById<TextView>(R.id.forgotPasswordText)?.setOnClickListener {
            Toast.makeText(
                this,
                "Password recovery feature coming soon!",
                Toast.LENGTH_SHORT
            ).show()
        }

        // Sign Up click listener
        findViewById<TextView>(R.id.signUpText)?.setOnClickListener {
            Toast.makeText(
                this,
                "Sign up feature coming soon!",
                Toast.LENGTH_SHORT
            ).show()
            // TODO: Navigate to SignUp activity
            // val intent = Intent(this, signup::class.java)
            // startActivity(intent)
        }
    }
}