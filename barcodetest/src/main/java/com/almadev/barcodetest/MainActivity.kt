package com.almadev.barcodetest

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.zxing.client.android.CaptureActivity
import com.google.zxing.client.android.Intents
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    companion object {

        private const val REQUEST_CODE = 101


    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        requestPermissions()

        start.setOnClickListener {
            start()
        }
    }

    private fun requestPermissions() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(arrayOf(Manifest.permission.CAMERA),
                    1001)
        }
    }

    private fun start() {

        val intentScan = Intent(this, CaptureActivity::class.java)
        intentScan.action = Intents.Scan.ACTION
        intentScan.addCategory(Intent.CATEGORY_DEFAULT)

        intentScan.putExtra(Intents.Scan.CAMERA_ID, 0)
        intentScan.putExtra(Intents.Scan.SHOW_FLIP_CAMERA_BUTTON, false)
        intentScan.putExtra(Intents.Scan.SHOW_TORCH_BUTTON, false)
        intentScan.putExtra(Intents.Scan.TORCH_ON, false)
        intentScan.putExtra(Intents.Scan.SAVE_HISTORY, false)
        intentScan.putExtra(Intents.Scan.BEEP_ON_SCAN, false)

        // avoid calling other phonegap apps
//        intentScan.setPackage(that.cordova.getActivity().getApplicationContext().getPackageName());

        startActivityForResult(intentScan, REQUEST_CODE);
    }
}
