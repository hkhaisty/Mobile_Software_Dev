package com.example.mdinh.instantmessengertesting

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class RecentMessagesActivity : AppCompatActivity() {
    companion object {
        var logged_user: UserAccount? = null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recent_messages)

        supportActionBar?.title = "CMail"

        fetchLoggeduser()
        loginVerification()
    }

    private fun fetchLoggeduser() {
        val user_id = FirebaseAuth.getInstance().uid
        val reference = FirebaseDatabase.getInstance().getReference("/users/$user_id")

        reference.addListenerForSingleValueEvent(object: ValueEventListener {
            override fun onDataChange(p0: DataSnapshot) {
                logged_user = p0.getValue(UserAccount::class.java)
                Log.d("RecentMessagesActivity", "Current user: ${logged_user?.username}")
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

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId) {
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

            R.id.clickQRCode -> {
                val intent = Intent(this, QRHomeActivity::class.java)
                startActivity(intent)
            }
        }

        return super.onOptionsItemSelected(item)
    }

    //Display navigational options
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.navigational_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }
}