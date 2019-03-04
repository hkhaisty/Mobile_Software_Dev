package com.example.mdinh.instantmessengertesting

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView

class ProfileActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        val qr_code_generator = findViewById<ImageView>(R.id.qrcodegeneratorbutton)
        qr_code_generator.setOnClickListener{
            val intent = Intent(this, MyQRActivity::class.java)
            startActivity(intent)
        }

        val qr_code_reader = findViewById<ImageView>(R.id.searchqrcodebutton)
        qr_code_reader.setOnClickListener {
            val intent = Intent(this, ScanQRActivity::class.java)
            startActivity(intent)
        }

        val chat_button = findViewById<ImageView>(R.id.chatimagebutton)
        chat_button.setOnClickListener {
            val intent = Intent(this, ChatActivity::class.java)
            startActivity(intent)
        }

        val friends_button = findViewById<ImageView>(R.id.friendsimagebutton)
        friends_button.setOnClickListener {
            val intent = Intent(this, FriendsListActivity::class.java)
            startActivity(intent)
        }

    }
}
