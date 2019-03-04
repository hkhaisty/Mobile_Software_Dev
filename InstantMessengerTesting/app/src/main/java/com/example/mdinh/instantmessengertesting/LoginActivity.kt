package com.example.mdinh.instantmessengertesting

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        loginLogin_button.setOnClickListener{
            val email = emailLogin_edittext.text.toString();
            val password = passwordLogin_edittext.text.toString();

            Log.d("LoginActivity", "Email: " + email);
            Log.d("LoginActivity", "Password: + $password");

            val intent = Intent(this, ProfileActivity::class.java)
            startActivity(intent)
        }

        donthaveaccountLogin_textview.setOnClickListener{
            Log.d("MainActivity", "Accessing registration screen for new users.")
            finish();
        }
    }
}