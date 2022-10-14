package com.example.calculator_kotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {

    lateinit var loginEmail: EditText
    lateinit var loginPassword: EditText
    lateinit var btnLogin: Button
    lateinit var btnLogInAsGuest: Button
    lateinit var newUser: TextView

    private lateinit var mAuth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        supportActionBar?.hide()

        mAuth = FirebaseAuth.getInstance()

        loginEmail = findViewById(R.id.edt_email)
        loginPassword = findViewById(R.id.edt_password)
        btnLogin = findViewById(R.id.btnLogin)
        btnLogInAsGuest = findViewById(R.id.btnLoginAsGuest)
        newUser = findViewById(R.id.newUser)

        newUser.setOnClickListener {
            val intent: Intent = Intent(this@LoginActivity,SignUpActivity::class.java)
            startActivity(intent)
        }

        btnLogInAsGuest.setOnClickListener {
            val intent : Intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }

        btnLogin.setOnClickListener {
            val email = loginEmail.text.toString()
            val password = loginPassword.text.toString()

            login(email,password)
        }
    }

    private fun login(email: String, password: String) {
        if (email.isEmpty()){
            Toast.makeText(this, "Please Provide Email", Toast.LENGTH_SHORT).show()
        }else if (password.isEmpty()){
            Toast.makeText(this, "Please Provide Password", Toast.LENGTH_SHORT).show()
        }else{
            mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(this){
                if (it.isSuccessful){
                    //code for logging in user
                    val intent = Intent(this@LoginActivity,MainActivity::class.java)
                    finish()
                    startActivity(intent)
                }else{
                    Toast.makeText(this@LoginActivity, "User Does not Exist", Toast.LENGTH_SHORT).show()
                }
            }
        }

    }
}