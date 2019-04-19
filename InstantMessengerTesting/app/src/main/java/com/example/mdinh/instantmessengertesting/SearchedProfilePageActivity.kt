package com.example.mdinh.instantmessengertesting

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity;
import android.view.View
import com.squareup.picasso.Picasso

import kotlinx.android.synthetic.main.activity_searched_profile_page.*

class SearchedProfilePageActivity : AppCompatActivity() {
    var user_receiver: UserAccount? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_searched_profile_page)

        user_receiver = intent.getParcelableExtra<UserAccount>(EmailSearchActivity.USER_KEY)

        usernamedisplaySearchedProfile_textview.text = user_receiver?.username
        emaildisplaySearchedProfile_textview.text = user_receiver?.email_address
        Picasso.get().load(user_receiver?.profileImageUrl).into(photodisplaySearchedProfile_circleview)

        addfriendSearchedProfile_button.setOnClickListener(View.OnClickListener {

        })
    }
}
