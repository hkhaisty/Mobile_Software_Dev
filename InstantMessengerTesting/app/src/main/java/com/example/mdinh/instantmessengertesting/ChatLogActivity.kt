package com.example.mdinh.instantmessengertesting

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.mdinh.instantmessengertesting.NewMessageActivity
import com.example.mdinh.instantmessengertesting.R
import com.example.mdinh.instantmessengertesting.UserAccount
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.squareup.picasso.Picasso
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.Item
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.activity_chat_log.*
import kotlinx.android.synthetic.main.received_messages_display.view.*
import kotlinx.android.synthetic.main.sent_messages_display.view.*

class ChatLogActivity : AppCompatActivity() {

    val chatlog_adapter = GroupAdapter<ViewHolder>()
    var user_receiver: UserAccount? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat_log)

        user_receiver = intent.getParcelableExtra<UserAccount>(NewMessageActivity.USER_KEY)
        supportActionBar?.title = user_receiver?.username

        chatdisplayChatLog_recyclerview.adapter = chatlog_adapter

        messageListener()

        sendChatLog_button.setOnClickListener {
            Log.d("ChatLogActivity", "send button clicked")
            sendMessage()
        }
    }

    //message class
    class ChatMessage(val message_id: String, val message: String, val sender_id: String, val receiver_id: String, val timestamp: Long) {
        constructor() : this("", "", "", "", -1)
    }

    private fun messageListener() {
        val reference = FirebaseDatabase.getInstance().getReference("/messages")

        reference.addChildEventListener(object: ChildEventListener {
            override fun onChildAdded(p0: DataSnapshot, p1: String?) {
                val chatMessage = p0.getValue(ChatMessage::class.java)

                if(chatMessage != null) {
                    Log.d("ChatLogActivity", chatMessage.message)

                    if(chatMessage.sender_id == FirebaseAuth.getInstance().uid) {
                        val logged_user = RecentMessagesActivity.logged_user
                        chatlog_adapter.add(SenderChatItem(chatMessage.message, logged_user!!))
                    }
                    else {
                        chatlog_adapter.add(ReceiverChatItem(chatMessage.message, user_receiver!!))
                    }
                }
            }

            override fun onCancelled(p0: DatabaseError) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onChildChanged(p0: DataSnapshot, p1: String?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onChildMoved(p0: DataSnapshot, p1: String?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onChildRemoved(p0: DataSnapshot) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
        })
    }

    private fun sendMessage() {
        val message = messageinputChatLog_edittext.text.toString()

        val user_data = intent.getParcelableExtra<UserAccount>(NewMessageActivity.USER_KEY)
        //the sender's user ID
        val sender_id = FirebaseAuth.getInstance().uid
        //the receiver's id
        val receiver_id = user_data.user_id

        if(sender_id == null) return

        val reference = FirebaseDatabase.getInstance().getReference("/messages").push()

        val chatMessage = ChatMessage(reference.key!!, message, sender_id, receiver_id, System.currentTimeMillis()/1000)
        //14:56 ep6
        reference.setValue(chatMessage)
            .addOnSuccessListener {
                Log.d("ChatLogActivity", "Saved message: ${reference.key}")
                Log.d("ChatLogActivity", "Sender ID: ${FirebaseAuth.getInstance().uid}, Receiver ID: ${user_data.user_id}")
            }
    }
}

class ReceiverChatItem(val text: String, val user_data: UserAccount): Item<ViewHolder>() {
    override fun bind(viewHolder: ViewHolder, position: Int) {
        viewHolder.itemView.received_message_textview.text = text

        val uri = user_data.profileImageUrl
        val imageview = viewHolder.itemView.receiver_image_imageview

        Picasso.get().load(uri).into(imageview)
    }

    override fun getLayout(): Int {
        return R.layout.received_messages_display
    }
}

class SenderChatItem(val text: String, val user_data: UserAccount): Item<ViewHolder>() {
    override fun bind(viewHolder: ViewHolder, position: Int) {
        viewHolder.itemView.sent_message_textview.text = text

        val uri = user_data.profileImageUrl
        val imageview = viewHolder.itemView.sender_image_imageview

        Picasso.get().load(uri).into(imageview)
    }

    override fun getLayout(): Int {
        return R.layout.sent_messages_display
    }
}