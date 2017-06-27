package com.example.ominext.memoryleaksample

import android.app.Application
import com.squareup.leakcanary.LeakCanary

/**
 * Created by Ominext on 6/27/2017.
 */
class LeakApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        LeakCanary.install(this)
    }
}