package com.example.mdinh.instantmessengertesting

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_qrhome.*
import kotlinx.android.synthetic.main.loading_screen.view.*
import android.widget.Button

class QRHomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_qrhome)

        scanQRCode.imageView;

        scanQRCode.setOnClickListener {
            val intent = Intent(this@QRHomeActivity, ScanQRCodeActivity::class.java)
            val REQUEST_CODE = 100
            val PERMISSION_REQUEST = 200

            val scanBtn = findViewById<Button>(R.id.scanbtn)

            scanBtn.setOnClickListener {
                val intent = Intent(this@QRHomeActivity, QRReaderActivity::class.java)
                startActivity(intent)
            }
        }
    }
}
