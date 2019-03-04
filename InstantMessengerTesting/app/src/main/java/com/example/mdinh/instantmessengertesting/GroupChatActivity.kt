package com.example.mdinh.instantmessengertesting

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class GroupChatActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_group_chat)

        val groupChatMessage = findViewById<TextView>(R.id.group1View)
        groupChatMessage.setOnClickListener{
            val intent = Intent(this, GroupChatActivity::class.java)
            startActivity(intent)
        }
    }
}
