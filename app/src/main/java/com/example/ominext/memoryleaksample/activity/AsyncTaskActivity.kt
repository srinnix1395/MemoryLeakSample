package com.example.ominext.memoryleaksample.activity

import android.util.Log
import com.example.ominext.memoryleaksample.retrofit.ApiService
import com.example.ominext.memoryleaksample.retrofit.RetrofitClient
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_asynctask.*

/**
 * Created by Ominext on 6/27/2017.
 */
class AsyncTaskActivity : android.support.v7.app.AppCompatActivity() {
    lateinit var task: com.example.ominext.memoryleaksample.activity.AsyncTaskActivity.LeakAsynctask
    lateinit var apiService: ApiService

    override fun onCreate(savedInstanceState: android.os.Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.example.ominext.memoryleaksample.R.layout.activity_asynctask)

        task = LeakAsynctask(tvResult)

        btnStart.setOnClickListener {
            task.execute()
        }

        apiService = RetrofitClient.getApiService()
        btnStartRetrofit.setOnClickListener {
            callAPITest()
        }
    }

    private fun callAPITest() {
        apiService.callApiTest("abc")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ t: String? ->
                    Log.i("success", t ?: "null")
                }, { t: Throwable? ->
                    Log.e("error", t?.message ?: "null")
                })
    }

//    override fun onDestroy() {
//        super.onDestroy()
//        task.cancel(true)
//    }

    class LeakAsynctask(var tv: android.widget.TextView) : android.os.AsyncTask<Void, Void, Unit>() {
//        var weakTextView: WeakReference<TextView>
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