package com.example.mdinh.instantmessengertesting

import android.os.Bundle
import android.support.v7.app.AppCompatActivity;
import android.view.View
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_searched_profile_page.*

class SearchedProfilePageActivity : AppCompatActivity() {
    var user_receiver: UserAccount? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_searched_profile_page)

        //val sender_id = FirebaseAuth.getInstance().uid

        user_receiver = intent.getParcelableExtra<UserAccount>(EmailSearchActivity.USER_KEY)

        usernamedisplaySearchedProfile_textview.text = user_receiver?.username
        emaildisplaySearchedProfile_textview.text = user_receiver?.email_address
        Picasso.get().load(user_receiver?.profileImageUrl).into(photodisplaySearchedProfile_circleview)

        addfriendSearchedProfile_button.setOnClickListener(View.OnClickListener {
            addFriend()
        })
    }

    private fun addFriend() {
        val sender_id = FirebaseAuth.getInstance().uid

        val receiver = user_receiver
        val receiver_id = receiver?.user_id

        if (sender_id == null) return

        val sending_reference = FirebaseDatabase.getInstance().getReference("/user-friends/$sender_id").push()
        val receiving_reference = FirebaseDatabase.getInstance().getReference("/user-friends/$receiver_id").push()

        sending_reference.setValue(receiver!!)
        receiving_reference.setValue(RecentMessagesActivity.logged_user!!)

        finish()
    }
}


