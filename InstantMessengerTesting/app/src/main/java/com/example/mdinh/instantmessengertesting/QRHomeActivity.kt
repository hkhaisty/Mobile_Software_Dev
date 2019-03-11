package com.example.mdinh.instantmessengertesting

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.widget.Button
import java.util.jar.Manifest

class QRHomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_qrhome)

        val REQUEST_CODE = 100
        val PERMISSION_REQUEST = 200

        val scanBtn = findViewById<Button>(R.id.scanbtn)

        scanBtn.setOnClickListener {
            val intent = Intent(this@QRHomeActivity, QRReaderActivity::class.java)
            startActivity(intent)
        }
    }
}
