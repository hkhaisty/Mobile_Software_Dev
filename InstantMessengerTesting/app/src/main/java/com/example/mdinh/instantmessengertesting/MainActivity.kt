package com.example.mdinh.instantmessengertesting

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*

//Registration screen
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //BEGIN: ClickListener for registration button
        registerRegistration_button.setOnClickListener {
            performRegistration()
        }
        //END: ClickListener for registration button.

        //BEGIN: Accessing the login screen for existing users.
        alreadyhaveaccountRegistration_textview.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)

            Log.d("MainActivity", "Accessing login screen for existing accounts.")
        }
        //END: Accessing the login screen for existing users.
    }

    //BEGIN: Registration function.
    private fun performRegistration() {
        //Converts email and password to string.
        val email = emailRegistration_edittext.text.toString()
        val password = passwordRegistration_edittext.text.toString()

        //If email or password is email check to avoid crash.
        if(email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "ERROR: Missing entry for email/password.", Toast.LENGTH_SHORT).show()
            return
        }

        //Logcat message to display email/password entry
        Log.d("MainActivity", "Email: " + email)
        Log.d("MainActivity", "Password: $password")

        //BEGIN: Firebase Authentication
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password).addOnCompleteListener {
            if(it.isSuccessful) {
                Log.d("MainActivity", "Registration Successful: ${it.result?.user?.uid}") //What does ?. mean?
            }
            else return@addOnCompleteListener
        }
            .addOnFailureListener{
                Log.d("MainActivity", "Failed to register user. ${it.message}")
            }
        //END: Firebase Authentication
    }
    //END: Registration function.
}
