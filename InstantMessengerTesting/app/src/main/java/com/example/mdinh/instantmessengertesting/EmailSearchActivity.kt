package com.example.mdinh.instantmessengertesting

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity;
import android.util.Log
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder

import kotlinx.android.synthetic.main.activity_email_search.*
import kotlinx.android.synthetic.main.activity_new_message.*

class EmailSearchActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_email_search)

        searchbutton_button.setOnClickListener {
            if(!searchbar_edittext.text.isEmpty()) {
                SearchEmailFromDatabase()
            }
            else {
                Log.d("EmailSearchActivity", "search bar empty")
            }
            Log.d("EmailSearchActivity", "search button clicked")
        }
    }

    private fun SearchEmailFromDatabase() {
        val reference = FirebaseDatabase.getInstance().getReference("/users")

        reference.addListenerForSingleValueEvent(object: ValueEventListener {
            override fun onDataChange(p0: DataSnapshot) {
                val group_adapter = GroupAdapter<ViewHolder>()

                p0.children.forEach {
                    //Log.d("EmailSearchActivity", it.toString())
                    val user_data = it.getValue(UserAccount::class.java)

                    if(user_data != null && searchbar_edittext.text.toString().equals(user_data.email_address)) {
                        //Log.d("EmailSearchActivity", "email match")
                        Log.d("EmailSearchActivity", user_data.username + " has email: " + searchbar_edittext.text.toString())
                        group_adapter.add(UserItem(user_data))
                    }
                    else {
                        Log.d("EmailSearchActivity", "no match")
                    }
                }

                group_adapter.setOnItemClickListener { item, view ->
                    val user_item = item as UserItem

                    val intent = Intent(view.context, ChatLogActivity::class.java)
                    //intent.putExtra(USER_KEY, user_item.user_data.username)
                    intent.putExtra(NewMessageActivity.USER_KEY, user_item.user_data)
                    startActivity(intent)

                    finish()
                }
                searchlist_recyclerview.adapter = group_adapter
            }

            override fun onCancelled(p0: DatabaseError) {

            }
        })
    }
}

