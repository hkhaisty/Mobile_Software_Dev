package com.example.mdinh.instantmessengertesting

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
<<<<<<< HEAD
<<<<<<< HEAD
import kotlinx.android.synthetic.main.activity_qrhome.*
import kotlinx.android.synthetic.main.loading_screen.view.*
import android.widget.Button
=======
=======
>>>>>>> 3ccaf4941388fb8c276d2f824421cb6514761da6
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.widget.Button
import java.util.jar.Manifest
<<<<<<< HEAD
>>>>>>> 3ccaf4941388fb8c276d2f824421cb6514761da6
=======
>>>>>>> 3ccaf4941388fb8c276d2f824421cb6514761da6

class QRHomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_qrhome)

<<<<<<< HEAD
<<<<<<< HEAD
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
=======
=======
>>>>>>> 3ccaf4941388fb8c276d2f824421cb6514761da6
        val REQUEST_CODE = 100
        val PERMISSION_REQUEST = 200

        val scanBtn = findViewById<Button>(R.id.scanbtn)

        scanBtn.setOnClickListener {
            val intent = Intent(this@QRHomeActivity, QRReaderActivity::class.java)
            startActivity(intent)
<<<<<<< HEAD
>>>>>>> 3ccaf4941388fb8c276d2f824421cb6514761da6
=======
>>>>>>> 3ccaf4941388fb8c276d2f824421cb6514761da6
        }
    }
}
