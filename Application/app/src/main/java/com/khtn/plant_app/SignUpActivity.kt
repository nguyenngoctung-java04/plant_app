package com.khtn.plant_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import com.google.firebase.auth.FirebaseAuth
import com.khtn.plant_app.databinding.ActivitySignUpBinding
import java.util.regex.Pattern

class SignUpActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignUpBinding
    private lateinit var firebaseAuth: FirebaseAuth

    private val PASSWORD_PATTERN = Pattern.compile(
        "^" + "(?=.*[0-9])" +         //at least 1 digit
                "(?=.*[a-z])" +         //at least 1 lower case letter
                "(?=.*[A-Z])" +         //at least 1 upper case letter
                "(?=.*[a-zA-Z])" +  //any letter
                "(?=.*[@#$%^&+=])" +  //at least 1 special character
                ".{11,}" +  //at least 11 characters
                "$"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()

        val tfEmail = binding.textfieldEmailSignup
        val tfPassword = binding.textfieldPasswordSignup
        val tfFullName = binding.textfieldFullnameSignup

        tfEmail.editText?.doOnTextChanged { _, _, _, _ ->
            tfEmail.error = null
        }

        tfPassword.editText?.doOnTextChanged { _, _, _, _ ->
            tfPassword.error = null
        }

        tfFullName.editText?.doOnTextChanged { _, _, _, _ ->
            tfFullName.error = null
        }

        // Initialize user variable
        var email: String
        var password: String
        var fullName = ""

        binding.buttonSignup.setOnClickListener {
            email = tfEmail.editText?.text.toString().trim()
            password = tfPassword.editText?.text.toString().trim()
            fullName = tfFullName.editText?.text.toString().trim()

            if (email.isEmpty()) {
                tfEmail.error = "Email cannot be empty"
                return@setOnClickListener
            }

            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                tfEmail.error = "Invalid email address"
                return@setOnClickListener
            }

            if (password.isEmpty()) {
                tfPassword.error = "Password cannot be empty"
                return@setOnClickListener
            }

            if (!PASSWORD_PATTERN.matcher(password).matches()) {
                tfPassword.error = "Password must contain at least 11 characters, " +
                        "lowercase letters, uppercase letters, numbers and special characters"
                return@setOnClickListener
            }

            if (fullName.isEmpty()) {
                tfFullName.error = "FullName cannot be empty"
                return@setOnClickListener
            }

            firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {
                if (it.isSuccessful) {
                    Toast.makeText(this, "Account successfully created!", Toast.LENGTH_SHORT).show()
                    finish()
                } else {
                    Toast.makeText(this, it.exception.toString(), Toast.LENGTH_SHORT).show()
                    finish()
                }

            }

        }
    }
}