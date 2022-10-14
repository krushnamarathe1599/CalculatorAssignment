package com.example.calculator_kotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class SignUpActivity : AppCompatActivity() {

    lateinit var signEmail: EditText
    lateinit var signPassword: EditText
    lateinit var btnSignUp: Button
    lateinit var btnLogInAsGuest: Button
    lateinit var alreadyRegistered: TextView

    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        supportActionBar?.hide()

        mAuth = FirebaseAuth.getInstance()

        signEmail = findViewById(R.id.edt_email)
        signPassword = findViewById(R.id.edt_password)
        btnSignUp = findViewById(R.id.btnSignUp)
        btnLogInAsGuest = findViewById(R.id.btnLoginAsGuest)
        alreadyRegistered = findViewById(R.id.alreadyRegistered)

        alreadyRegistered.setOnClickListener {
            val intent : Intent = Intent(this@SignUpActivity,LoginActivity::class.java)
            startActivity(intent)
        }


        btnSignUp.setOnClickListener {
            val email = signEmail.text.toString()
            val password = signPassword.text.toString()

            signUp(email,password)
        }

        btnLogInAsGuest.setOnClickListener {
            val intent: Intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }


    }

    private fun signUp(email: String, password: String) {
        if (email.isEmpty()){
            Toast.makeText(this, "Please Provide Email", Toast.LENGTH_SHORT).show()
        }else if (password.isEmpty()){
            Toast.makeText(this, "Please Provide Password", Toast.LENGTH_SHORT).show()
        }else{
            mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(this){
                if (it.isSuccessful){
                    //code for logging in user
                    val intent = Intent(this@SignUpActivity,MainActivity::class.java)
                    finish()
                    startActivity(intent)
                }else{
                    Toast.makeText(this@SignUpActivity, "User already Exist", Toast.LENGTH_SHORT).show()
                }
            }
        }

    }
}