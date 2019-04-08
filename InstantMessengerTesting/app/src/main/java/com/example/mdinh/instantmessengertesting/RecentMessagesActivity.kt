package com.example.mdinh.instantmessengertesting

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBar
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.Item
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.activity_recent_messages.*
import kotlinx.android.synthetic.main.lastmessage_recentmessages.view.*
import kotlinx.android.synthetic.main.nav_header.*

class RecentMessagesActivity : AppCompatActivity() {
    companion object {
        var logged_user: UserAccount? = null
    }

    private lateinit var drawerLayout: DrawerLayout

    val adapter = GroupAdapter<ViewHolder>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recent_messages)

        recentmessages_recyclerview.adapter = adapter

        supportActionBar?.title = "CMail"


        recentmessagesListener()
        fetchLoggeduser()
        loginVerification()


        val toolbar: Toolbar = findViewById(R.id.sidemenu_toolbar)
        setSupportActionBar(toolbar)
        val actionbar: ActionBar? = supportActionBar
        actionbar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setHomeAsUpIndicator(R.drawable.ic_menu)
        }
        drawerLayout = findViewById(R.id.drawer_layout)

        val navigationView: NavigationView = this.findViewById(R.id.nav_view)
        navigationView.setNavigationItemSelectedListener { menuItem ->
            // set item as selected to persist highlight
            menuItem.isChecked = true
            // close drawer when item is tapped
            drawerLayout.closeDrawers()

            //Navigation buttons
            when(menuItem.itemId) {
                R.id.nav_search -> {
                    val intent = Intent(this, EmailSearchActivity::class.java)
                    Log.d("RecentMessagesActivity", "navigating...")
                    startActivity(intent)
                }
                R.id.nav_profile -> {
                    //val intent = Intent(this, )
                    Log.d("RecentMessagesActivity", "navigating...")
                    //startActivity(intent)
                }
                R.id.nav_friends -> {
                    //val intent = Intent(this, )
                    Log.d("RecentMessagesActivity", "navigating...")
                    //startActivity(intent)
                }
            }
            true
        }
    }

    val recentmessage_map = HashMap<String, ChatLogActivity.ChatMessage>()

    private fun recentmessagesListener() {
        val sender_id = FirebaseAuth.getInstance().uid
        val reference = FirebaseDatabase.getInstance().getReference("/last-message/$sender_id")

        reference.addChildEventListener(object: ChildEventListener {
            override fun onChildAdded(p0: DataSnapshot, p1: String?) {
                val message = p0.getValue(ChatLogActivity.ChatMessage::class.java)?: return

                recentmessage_map[p0.key!!] = message
                refreshRecyclerView()
            }

            override fun onChildChanged(p0: DataSnapshot, p1: String?) {
                val message = p0.getValue(ChatLogActivity.ChatMessage::class.java)?: return

                recentmessage_map[p0.key!!] = message
                refreshRecyclerView()
            }

            override fun onChildMoved(p0: DataSnapshot, p1: String?) {
            }
            override fun onChildRemoved(p0: DataSnapshot) {
            }
            override fun onCancelled(p0: DatabaseError) {
            }
        })
    }

    private fun refreshRecyclerView() {
        adapter.clear()
        recentmessage_map.values.forEach {
            adapter.add(RecentMessageRow(it))
        }
    }

    private fun fetchLoggeduser() {
        val user_id = FirebaseAuth.getInstance().uid
        val reference = FirebaseDatabase.getInstance().getReference("/users/$user_id")

        reference.addListenerForSingleValueEvent(object: ValueEventListener {
            override fun onDataChange(p0: DataSnapshot) {
                logged_user = p0.getValue(UserAccount::class.java)
                Log.d("RecentMessagesActivity", "Current user: ${logged_user?.username}")
                sidemenuheader_textview.setText("Hello, " + logged_user?.username)
            }

            override fun onCancelled(p0: DatabaseError) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
        })
    }

    //Checks if user is logged in. If not, user will be taken back to the registration activity
    private fun loginVerification() {
        val user_id = FirebaseAuth.getInstance().uid

        if(user_id == null) {
            val intent = Intent(this, RegistrationActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
        }
    }

    //Display navigational options
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.navigational_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId) {
            //Drawer Button
            android.R.id.home -> {
                drawerLayout.openDrawer(GravityCompat.START)
            }
            ///////////////////////////////////////////////
            R.id.newmessage_menu -> {
                //CHANGES MADE THAT HASN'T BEEN UPDATED TO GIT 3/4
                val intent = Intent(this, NewMessageActivity::class.java)
                startActivity(intent)
                //CHANGES MADE THAT HASN'T BEEN UPDATED TO GIT 3/4
            }
            //When user clicks on SIGN OUT from the navigation task bar, the user will be logged out and returned to registration screen.
            //May need to be changed to login screen.
            R.id.signout_menu -> {
                FirebaseAuth.getInstance().signOut()

                val intent = Intent(this, RegistrationActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(intent)
            }
        }

        return super.onOptionsItemSelected(item)
    }

    class RecentMessageRow(val message: ChatLogActivity.ChatMessage): Item<ViewHolder>() {
        override fun getLayout(): Int {
            return R.layout.lastmessage_recentmessages
        }

        override fun bind(viewHolder: ViewHolder, position: Int) {
            viewHolder.itemView.usernamedisplay_textview.text = message.receiver_id
            viewHolder.itemView.lastmessagedisplay_textview.text = message.message
        }
    }
}