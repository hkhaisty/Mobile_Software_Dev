//CHANGES MADE THAT HASN'T BEEN UPDATED TO GIT 3/4
package com.example.mdinh.instantmessengertesting

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.mdinh.instantmessengertesting.ChatLogActivity
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.squareup.picasso.Picasso
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.Item
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.activity_new_message.*
import kotlinx.android.synthetic.main.user_row_newmessage.view.*

class NewMessageActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_message)

        //Set the title of the action bar
        supportActionBar?.title = "Select User"

        /*val adapter = GroupAdapter<ViewHolder>()

        adapter.add(UserItem())
        adapter.add(UserItem())
        adapter.add(UserItem())

        newmessage_recyclerview.adapter = adapter*/

        fetchUsersFromDataBase()
    }

    //Retrieves user data from the database and displays them in the recycler view
    private fun fetchUsersFromDataBase() {
        val reference = FirebaseDatabase.getInstance().getReference("/users")
        reference.addListenerForSingleValueEvent(object: ValueEventListener {
            override fun onDataChange(p0: DataSnapshot) {
                val group_adapter = GroupAdapter<ViewHolder>()

                p0.children.forEach {
                    Log.d("NewMessage Activity", it.toString())
                    val user_data = it.getValue(UserAccount::class.java)
                    if(user_data != null) {
                        group_adapter.add(UserItem(user_data))
                    }
                }
                //when a user is selected from the group adapter, ChatLogActivity will start
                group_adapter.setOnItemClickListener { item, view ->
                    val user_item = item as UserItem

                    val intent = Intent(view.context, ChatLogActivity::class.java)
                    //intent.putExtra(USER_KEY, user_item.user_data.username)
                    intent.putExtra(USER_KEY, user_item.user_data)
                    startActivity(intent)

                    finish()
                }

                newmessage_recyclerview.adapter = group_adapter
            }

            override fun onCancelled(p0: DatabaseError) {

            }
        })
    }

    companion object {
        val USER_KEY = "USER KEY"
    }
}

class UserItem(val user_data: UserAccount): Item<ViewHolder>() {
    override fun bind(viewHolder: ViewHolder, position: Int) {
        viewHolder.itemView.usernameNewMessage_textview.text = user_data.username

        Picasso.get().load(user_data.profileImageUrl).into(viewHolder.itemView.profilepictureNewMessage_imageview)
    }

    override fun getLayout(): Int {
        return R.layout.user_row_newmessage
    }
}
//CHANGES MADE THAT HASN'T BEEN UPDATED TO GIT 3/4