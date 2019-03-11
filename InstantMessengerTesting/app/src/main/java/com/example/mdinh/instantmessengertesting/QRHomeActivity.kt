package com.example.mdinh.instantmessengertesting

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_qrhome.*
import kotlinx.android.synthetic.main.loading_screen.view.*

class QRHomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_qrhome)

        scanQRCode.imageView;

        scanQRCode.setOnClickListener{
            val intent = Intent(this@QRHomeActivity, ScanQRCodeActivity::class.java)
            startActivity(intent)
        }
    }
}
