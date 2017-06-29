package com.example.ominext.memoryleaksample.activity

import android.widget.TextView
import android.widget.Toast
import com.example.ominext.memoryleaksample.R
import kotlinx.android.synthetic.main.activity_handler.*
import java.lang.ref.WeakReference

/**
 * Created by Ominext on 6/29/2017.
 */
class HandlerActivity : android.support.v7.app.AppCompatActivity() {

    val handler = android.os.Handler {
        return@Handler true
    }

    val runnable = Runnable { Toast.makeText(this@HandlerActivity, "leak", android.widget.Toast.LENGTH_SHORT).show() }

    override fun onCreate(savedInstanceState: android.os.Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_handler)

        btnCreateHandler.setOnClickListener {
            createHandler()
        }

        btnRemoveCallback.setOnClickListener {
            removeCallback()
        }
        btnStartThread.setOnClickListener {
            startThread()
        }

        btnStopThread.setOnClickListener {
            stopThread()
        }
    }

    lateinit var thread: Thread

    private fun startThread() {
        thread = MyThread(tvResult)
        thread.start()
    }

    private fun stopThread() {
        thread.interrupt()
    }

    private fun createHandler() {
        handler.postDelayed(runnable, 10000)
    }

    private fun removeCallback() {
        handler.removeCallbacks(runnable)
    }

    class MyThread(tv: TextView) : Thread() {
        val weak = WeakReference<TextView>(tv)

        override fun run() {
            super.run()
            while (true) {

            }
            weak.get()?.text = "xong"
        }
    }
}