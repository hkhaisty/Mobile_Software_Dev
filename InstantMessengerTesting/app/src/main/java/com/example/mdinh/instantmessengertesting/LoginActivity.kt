package com.example.mdinh.instantmessengertesting

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        loginLogin_button.setOnClickListener{
            val email = emailLogin_edittext.text.toString()
            val password = passwordLogin_edittext.text.toString()

<<<<<<< HEAD
            Log.d("LoginActivity", "Email: " + email);
            Log.d("LoginActivity", "Password: + $password");

            val intent = Intent(this, ProfileActivity::class.java)
            startActivity(intent)
=======
            Log.d("LoginActivity", "Email: " + email)
            Log.d("LoginActivity", "Password: + $password")

            FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
                .addOnCompleteListener {
                    if(it.isSuccessful) {
                        Log.d("LoginActivity", "Login Successful: ${it.result?.user?.uid}") //What does ?. mean?
                    }
                    else return@addOnCompleteListener
                }
                .addOnFailureListener{
                    Log.d("LoginActivity", "Failed to login. ${it.message}")
                }
>>>>>>> 7fb1d61f87f19204818affc1b1c86971f00cf714
        }

        donthaveaccountLogin_textview.setOnClickListener{
            Log.d("MainActivity", "Accessing registration screen for new users.")
            finish();
        }
    }
}