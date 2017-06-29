package com.example.ominext.memoryleaksample.activity

import kotlinx.android.synthetic.main.activity_listener.*

/**
 * Created by Ominext on 6/27/2017.
 */
class ListenerActivity : android.support.v7.app.AppCompatActivity(), android.location.LocationListener {
    var receiver: android.content.BroadcastReceiver = object : android.content.BroadcastReceiver() {
        override fun onReceive(context: android.content.Context?, intent: android.content.Intent?) {
            android.widget.Toast.makeText(context, "screen on", android.widget.Toast.LENGTH_LONG).show()
        }
    }
    var isRegister: Boolean = false


    var mManager: android.location.LocationManager? = null
    var isListening: Boolean = false

    override fun onCreate(savedInstanceState: android.os.Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.example.ominext.memoryleaksample.R.layout.activity_listener)
        btnRegister.setOnClickListener {
            registerReceiver()
        }
        btnUnregister.setOnClickListener {
            unregisterReceiver()
        }

        btnStartListen.setOnClickListener {
            startListen()
        }

        btnStopListen.setOnClickListener {
            stopListen()
        }
    }


    @android.annotation.SuppressLint("MissingPermission")
    private fun startListen() {
        if (!isListening) {
            mManager = getSystemService(LOCATION_SERVICE) as android.location.LocationManager
            mManager?.requestLocationUpdates(android.location.LocationManager.GPS_PROVIDER,
                    java.util.concurrent.TimeUnit.MINUTES.toMillis(1),
                    100F,
                    this)
            isListening = true
        }
    }

    private fun stopListen() {
        if (isListening) {
            mManager?.removeUpdates(this)
//            mManager = null
            isListening = false
        }
    }

    override fun onLocationChanged(location: android.location.Location?) {

    }

    override fun onStatusChanged(provider: String?, status: Int, extras: android.os.Bundle?) {


    }

    override fun onProviderEnabled(provider: String?) {

    }

    override fun onProviderDisabled(provider: String?) {

    }

    private fun registerReceiver() {
        if (!isRegister) {
            val filter: android.content.IntentFilter = android.content.IntentFilter()
            filter.addAction(android.content.Intent.ACTION_SCREEN_ON)
            registerReceiver(receiver, filter)
            isRegister = true
        }
    }

    private fun unregisterReceiver() {
        if (isRegister) {
            unregisterReceiver(receiver)
            isRegister = false
        }
    }
}