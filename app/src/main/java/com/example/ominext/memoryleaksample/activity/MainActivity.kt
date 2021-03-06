package com.example.ominext.memoryleaksample.activity

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.ominext.memoryleaksample.R
import kotlinx.android.synthetic.main.activity_main.*

/**
 * Created by Ominext on 6/27/2017.
 */
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnListener.setOnClickListener{
            startActivity(ListenerActivity::class.java)
        }

        btnAsynctask.setOnClickListener {
            startActivity(AsyncTaskActivity::class.java)
        }

        btnHandler.setOnClickListener {
            startActivity(HandlerActivity::class.java)
        }
    }

    fun <T> startActivity(clazz: Class<T>) {
        val intent = Intent(this, clazz)
        startActivity(intent)
    }
}