package com.example.mdinh.instantmessengertesting

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.google.firebase.auth.FirebaseAuth

class RecentMessagesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recent_messages)

        loginVerification()
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

    //Display navigational options
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.navigational_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }
}