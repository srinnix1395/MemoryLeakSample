package com.example.ominext.memoryleaksample

import android.os.AsyncTask
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_asynctask.*

/**
 * Created by Ominext on 6/27/2017.
 */
class AsyncTaskActivity : AppCompatActivity() {
    lateinit var task: LeakAsynctask

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_asynctask)

        task = LeakAsynctask(tvResult)

        btnStart.setOnClickListener {
            task.execute()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        task.cancel(true)
    }

    class LeakAsynctask(var tv: TextView) : AsyncTask<Void, Void, Unit>() {
//        var weakTextView: TextView>
//
//        init {
//            weakTextView = WeakReference<TextView>(tv)
//        }

        override fun doInBackground(vararg params: Void?): Unit? {
            Thread.sleep(10000)
            return null
        }
    }
}