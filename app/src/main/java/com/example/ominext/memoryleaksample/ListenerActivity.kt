package com.example.ominext.memoryleaksample

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_listener.*

/**
 * Created by Ominext on 6/27/2017.
 */
class ListenerActivity : AppCompatActivity() {
    var receiver: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            Toast.makeText(context, "screen on", Toast.LENGTH_LONG).show()
        }
    }
    var isRegister: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_listener)
        btnRegister.setOnClickListener {
            registerReceiver()
        }
        btnUnregister.setOnClickListener{
            unregisterReceiver()
        }
    }

    private fun registerReceiver() {
        if (!isRegister) {
            val filter: IntentFilter = IntentFilter()
            filter.addAction(Intent.ACTION_SCREEN_ON)
            registerReceiver(receiver, filter)
            isRegister = true
        }
    }

    private fun unregisterReceiver(){
        if (isRegister) {
            unregisterReceiver(receiver)
            isRegister = false
        }
    }
}